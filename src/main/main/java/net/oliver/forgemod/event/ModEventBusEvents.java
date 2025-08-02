package net.oliver.forgemod.event;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
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

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.SNAIL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING,
                (entityType, level, spawnType, pos, random) -> {
                    // Custom spawn check to allow spawning on leaves or logs
                    BlockState blockBelow = level.getBlockState(pos.below());
                    return blockBelow.is(BlockTags.DIRT) ||
                            blockBelow.is(BlockTags.LEAVES) ||
                            blockBelow.is(BlockTags.LOGS) ||
                            blockBelow.is(Blocks.SAND) ||
                            blockBelow.is(Blocks.SANDSTONE) ||
                            blockBelow.is(Blocks.TERRACOTTA) ||
                            blockBelow.is(Blocks.RED_SAND) ||
                            blockBelow.is(Blocks.RED_SANDSTONE) ||
                            blockBelow.is(Blocks.STONE) ||
                            blockBelow.is(Blocks.DIRT) ||
                            blockBelow.is(Blocks.COARSE_DIRT) ||
                            blockBelow.is(Blocks.GRAVEL);
                }, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
