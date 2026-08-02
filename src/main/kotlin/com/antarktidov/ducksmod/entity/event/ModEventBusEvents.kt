package com.antarktidov.ducksmod.entity.event

import com.antarktidov.ducksmod.DucksMod
import com.antarktidov.ducksmod.entity.ModEntities
import com.antarktidov.ducksmod.entity.client.DuckModel
import com.antarktidov.ducksmod.entity.custom.DuckEntity
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = DucksMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEventBusEvents {
    @SubscribeEvent
    @JvmStatic
    fun registerLayers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(DuckModel.LAYER_LOCATION) { DuckModel.createBodyLayer() }
    }

    @SubscribeEvent
    @JvmStatic
    fun registerAttributes(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.DUCK.get(), DuckEntity.createAttributes().build())
    }
}
