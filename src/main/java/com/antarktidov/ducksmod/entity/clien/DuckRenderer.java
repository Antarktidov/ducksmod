package com.antarktidov.ducksmod.entity.clien;

import com.antarktidov.ducksmod.DucksMod;
import com.antarktidov.ducksmod.entity.custom.DuckEntity;
import com.antarktidov.ducksmod.entity.custom.DuckVariant;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class DuckRenderer  extends MobRenderer<DuckEntity, DuckModel<DuckEntity>> {
    private static final Map<DuckVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DuckVariant.class), map -> {
                map.put(DuckVariant.WHITE,
                        ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/white_duck.png"));
                map.put(DuckVariant.MALE_MALLARD,
                        ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/male_mallard.png"));
                map.put(DuckVariant.FEMALE_MALLARD,
                        ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/female_mallard.png"));
                map.put(DuckVariant.MUSCOVY,
                        ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/muscovy_duck.png"));

            });
    public DuckRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DuckModel<>(pContext.bakeLayer(DuckModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(DuckEntity entity) {
        if (entity.isBaby()) {
            return ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/baby_duck.png");
        }
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(DuckEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
