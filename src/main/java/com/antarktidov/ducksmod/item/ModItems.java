package com.antarktidov.ducksmod.item;

import com.antarktidov.ducksmod.DucksMod;
import com.antarktidov.ducksmod.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DucksMod.MOD_ID);

    public static final RegistryObject<Item> DUCK_EGG = ITEMS.register("duck_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DUCK, 0x53524b, 0xdac741, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
