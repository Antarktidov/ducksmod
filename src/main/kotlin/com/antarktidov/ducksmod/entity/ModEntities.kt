package com.antarktidov.ducksmod.entity

import com.antarktidov.ducksmod.DucksMod
import com.antarktidov.ducksmod.entity.custom.DuckEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object ModEntities {
    @JvmField
    val ENTITY_TYPES: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DucksMod.MOD_ID)

    @JvmField
    val DUCK: RegistryObject<EntityType<DuckEntity>> = ENTITY_TYPES.register("duck") {
        EntityType.Builder.of(::DuckEntity, MobCategory.CREATURE)
            .sized(0.25f, 0.25f)
            .build("duck")
    }

    @JvmStatic
    fun register(eventBus: IEventBus) {
        ENTITY_TYPES.register(eventBus)
    }
}
