package net.oliver.forgemod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.entity.SnailVariant;
import net.oliver.forgemod.entity.custom.SnailEntity;

import java.util.Map;

public class SnailRenderer extends MobRenderer<SnailEntity, SnailModel<SnailEntity>> {
    private static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SnailVariant.class), map -> {
                map.put(SnailVariant.DIRT,
                        ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, "textures/entity/snail/dirtsnail.png"));
                map.put(SnailVariant.SAND,
                        ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, "textures/entity/snail/sandsnail.png"));
            });

    public SnailRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SnailModel<>(pContext.bakeLayer(SnailModel.LAYER_LOCATION)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(SnailEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(SnailEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.8f, 0.8f, 0.8f);
        } else {
            pPoseStack.scale(1f,1f,1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
