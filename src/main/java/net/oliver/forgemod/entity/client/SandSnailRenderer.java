package net.oliver.forgemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.entity.custom.SandSnailEntity;

public class SandSnailRenderer extends MobRenderer<SandSnailEntity, SandSnailModel<SandSnailEntity>> {
    public SandSnailRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SandSnailModel<>(pContext.bakeLayer(SandSnailModel.LAYER_LOCATION)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(SandSnailEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID,"textures/entity/sandsnail/snail.png");
    }

    @Override
    public void render(SandSnailEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.8f, 0.8f, 0.8f);
        } else {
            pPoseStack.scale(1f,1f,1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
