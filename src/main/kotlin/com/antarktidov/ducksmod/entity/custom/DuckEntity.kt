package com.antarktidov.ducksmod.entity.custom

import com.antarktidov.ducksmod.entity.ModEntities
import net.minecraft.Util
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraftforge.fml.ModList

class DuckEntity(entityType: EntityType<out Animal>, level: Level) : Animal(entityType, level) {
    @JvmField
    val idleAnimationState = AnimationState()
    private var idleAnimationTimeout = 0

    @JvmField
    var NktFarmSimulatorMod_CORN: Item? = null

    @JvmField
    var NktFarmSimulatorMod_CORN_SEEDS: Item? = null

    init {
        loadFoodFromNktFarmSimulator()
    }

    fun loadFoodFromNktFarmSimulator() {
        if (ModList.get().isLoaded("nktfarmsimulator")) {
            try {
                val clazz = Class.forName("com.antarktidov.nktfarmsimulator.items.ModItems")

                val cornField = clazz.getField("CORN")
                val registryObject = cornField.get(null)
                val getMethod = registryObject.javaClass.getMethod("get")
                NktFarmSimulatorMod_CORN = getMethod.invoke(registryObject) as Item

                val cornSeedsField = clazz.getField("CORN_SEEDS")
                val registryObject2 = cornSeedsField.get(null)
                val getMethod2 = registryObject2.javaClass.getMethod("get")
                NktFarmSimulatorMod_CORN_SEEDS = getMethod2.invoke(registryObject2) as Item
            } catch (_: ClassNotFoundException) {
            } catch (e: ReflectiveOperationException) {
                throw RuntimeException(e)
            }
        }
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(1, PanicGoal(this, 2.0))
        goalSelector.addGoal(2, BreedGoal(this, 1.0))
        goalSelector.addGoal(
            3,
            TemptGoal(
                this,
                1.25,
                { stack ->
                    stack.`is`(Items.MELON_SLICE) ||
                        stack.`is`(Items.GLISTERING_MELON_SLICE) ||
                        stack.`is`(Items.BREAD) ||
                        (NktFarmSimulatorMod_CORN != null && stack.`is`(NktFarmSimulatorMod_CORN)) ||
                        (NktFarmSimulatorMod_CORN_SEEDS != null && stack.`is`(NktFarmSimulatorMod_CORN_SEEDS))
                },
                false
            )
        )
        goalSelector.addGoal(4, FollowParentGoal(this, 1.25))
        goalSelector.addGoal(6, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(7, RandomLookAroundGoal(this))
    }

    override fun isFood(stack: ItemStack): Boolean =
        stack.`is`(Items.MELON_SLICE) ||
            stack.`is`(Items.GLISTERING_MELON_SLICE) ||
            stack.`is`(Items.BREAD) ||
            (NktFarmSimulatorMod_CORN != null && stack.`is`(NktFarmSimulatorMod_CORN)) ||
            (NktFarmSimulatorMod_CORN_SEEDS != null && stack.`is`(NktFarmSimulatorMod_CORN_SEEDS))

    override fun getBreedOffspring(level: ServerLevel, otherParent: AgeableMob): AgeableMob? =
        ModEntities.DUCK.get().create(level)

    private fun setupAnimationStates() {
        if (idleAnimationTimeout <= 0) {
            idleAnimationTimeout = 40
            idleAnimationState.start(tickCount)
        } else {
            --idleAnimationTimeout
        }
    }

    override fun tick() {
        super.tick()
        if (level().isClientSide) {
            setupAnimationStates()
        }
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(VARIANT, 0)
    }

    private val typeVariant: Int
        get() = entityData.get(VARIANT)

    val variant: DuckVariant
        get() = DuckVariant.byId(typeVariant and 255)

    private fun setVariant(variant: DuckVariant) {
        entityData.set(VARIANT, variant.id and 255)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("Variant", typeVariant)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        entityData.set(VARIANT, compound.getInt("Variant"))
    }

    override fun finalizeSpawn(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnType: MobSpawnType,
        spawnGroupData: SpawnGroupData?
    ): SpawnGroupData? {
        setVariant(Util.getRandom(DuckVariant.entries.toTypedArray(), random))
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData)
    }

    override fun finalizeSpawnChildFromBreeding(level: ServerLevel, animal: Animal, baby: AgeableMob?) {
        val variant = Util.getRandom(DuckVariant.entries.toTypedArray(), random)
        (baby as DuckEntity).setVariant(variant)
        super.finalizeSpawnChildFromBreeding(level, animal, baby)
    }

    companion object {
        private val VARIANT: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(DuckEntity::class.java, EntityDataSerializers.INT)

        @JvmStatic
        fun createAttributes(): AttributeSupplier.Builder =
            Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
    }
}
