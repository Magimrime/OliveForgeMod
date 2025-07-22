package net.oliver.forgemod.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.entity.ModEntities;
import net.oliver.forgemod.entity.client.SnailModel;
import net.oliver.forgemod.entity.custom.SnailEntity;

@Mod.EventBusSubscriber(modid = ForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SnailModel.LAYER_LOCATION, SnailModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SNAIL.get(), SnailEntity.createAttributes().build());
    }

}
