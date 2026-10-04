package com.antarktidov.ducksmod.entity;

import com.antarktidov.ducksmod.DucksMod;
import com.antarktidov.ducksmod.entity.custom.DuckEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DucksMod.MOD_ID);

    public static final RegistryObject<EntityType<DuckEntity>> DUCK =
            ENTITY_TYPES.register("duck", () -> EntityType.Builder.of(DuckEntity::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.35f).build("duck"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
