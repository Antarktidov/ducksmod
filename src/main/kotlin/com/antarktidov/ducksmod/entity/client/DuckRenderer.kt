package com.antarktidov.ducksmod.entity.client

import com.antarktidov.ducksmod.DucksMod
import com.antarktidov.ducksmod.entity.custom.DuckEntity
import com.antarktidov.ducksmod.entity.custom.DuckVariant
import com.google.common.collect.Maps
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.Util
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class DuckRenderer(context: EntityRendererProvider.Context) :
    MobRenderer<DuckEntity, DuckModel<DuckEntity>>(
        context,
        DuckModel(context.bakeLayer(DuckModel.LAYER_LOCATION)),
        0.85f
    ) {

    override fun getTextureLocation(entity: DuckEntity): ResourceLocation {
        if (entity.isBaby) {
            return ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/baby_duck.png")
        }
        return LOCATION_BY_VARIANT[entity.variant]!!
    }

    override fun render(
        entity: DuckEntity,
        entityYaw: Float,
        partialTicks: Float,
        poseStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int
    ) {
        if (entity.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f)
        } else {
            poseStack.scale(1f, 1f, 1f)
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight)
    }

    companion object {
        private val LOCATION_BY_VARIANT: Map<DuckVariant, ResourceLocation> =
            Util.make(Maps.newEnumMap(DuckVariant::class.java)) { map ->
                map[DuckVariant.WHITE] =
                    ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/white_duck.png")
                map[DuckVariant.MALE_MALLARD] =
                    ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/male_mallard.png")
                map[DuckVariant.FEMALE_MALLARD] =
                    ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/female_mallard.png")
                map[DuckVariant.MUSCOVY] =
                    ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/muscovy_duck.png")
                map[DuckVariant.MANDARIN] =
                    ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/mandarin_duck.png")
            }
    }
}
