package com.antarktidov.ducksmod.entity.clien;

import com.antarktidov.ducksmod.DucksMod;
import com.antarktidov.ducksmod.entity.custom.DuckEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DuckRenderer  extends MobRenderer<DuckEntity, DuckModel<DuckEntity>> {
    public DuckRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DuckModel<>(pContext.bakeLayer(DuckModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(DuckEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(DucksMod.MOD_ID, "textures/entity/duck/white_duck.png");
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
