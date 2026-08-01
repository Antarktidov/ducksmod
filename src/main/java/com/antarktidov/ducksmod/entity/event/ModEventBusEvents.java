package com.antarktidov.ducksmod.entity.event;

import com.antarktidov.ducksmod.DucksMod;
import com.antarktidov.ducksmod.entity.ModEntities;
import com.antarktidov.ducksmod.entity.clien.DuckModel;
import com.antarktidov.ducksmod.entity.custom.DuckEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DucksMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DuckModel.LAYER_LOCATION, DuckModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DUCK.get(), DuckEntity.createAttributes().build());
    }
}
