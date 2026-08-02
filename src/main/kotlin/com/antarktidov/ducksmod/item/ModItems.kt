package com.antarktidov.ducksmod.item

import com.antarktidov.ducksmod.DucksMod
import com.antarktidov.ducksmod.entity.ModEntities
import net.minecraft.world.item.Item
import net.minecraftforge.common.ForgeSpawnEggItem
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object ModItems {
    @JvmField
    val ITEMS: DeferredRegister<Item> =
        DeferredRegister.create(ForgeRegistries.ITEMS, DucksMod.MOD_ID)

    @JvmField
    val DUCK_EGG: RegistryObject<Item> = ITEMS.register("duck_egg") {
        ForgeSpawnEggItem(ModEntities.DUCK, 0xffffff, 0xffffff, Item.Properties())
    }

    @JvmStatic
    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}
