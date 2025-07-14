package net.oliver.forgemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.oliver.forgemod.block.ModBlocks;
import net.oliver.forgemod.item.ModItems;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
            // Check if the player is holding flint and steel in the used hand
            ItemStack itemStack = pPlayer.getItemInHand(pHand);
            if (itemStack.getItem() == Items.FLINT_AND_STEEL) {
                // Debug log to confirm method is called
                System.out.println("Flint and steel used at " + pPos);

                // Play sound on both client and server
                pLevel.playSound(pPlayer, pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

                // Spawn extra particles only on the client side
                if (pLevel.isClientSide) {
                    RandomSource random = pLevel.random;
                    for (int i = 0; i < 30; i++) { // Increased to 30 for "extra" effect
                        // Randomly choose one of the five faces (north, south, east, west, top)
                        int face = random.nextInt(5);
                        double x = pPos.getX() + 0.5;
                        double y = pPos.getY() + 0.5;
                        double z = pPos.getZ() + 0.5;

                        switch (face) {
                            case 0: // North (z - 0.5)
                                x += (random.nextDouble() - 0.5) * 0.8;
                                y += (random.nextDouble() - 0.5) * 0.8;
                                z -= 0.5;
                                break;
                            case 1: // South (z + 0.5)
                                x += (random.nextDouble() - 0.5) * 0.8;
                                y += (random.nextDouble() - 0.5) * 0.8;
                                z += 0.5;
                                break;
                            case 2: // East (x + 0.5)
                                x += 0.5;
                                y += (random.nextDouble() - 0.5) * 0.8;
                                z += (random.nextDouble() - 0.5) * 0.8;
                                break;
                            case 3: // West (x - 0.5)
                                x -= 0.5;
                                y += (random.nextDouble() - 0.5) * 0.8;
                                z += (random.nextDouble() - 0.5) * 0.8;
                                break;
                            case 4: // Top (y + 1.0)
                                x += (random.nextDouble() - 0.5) * 0.8;
                                y = pPos.getY() + 1.0;
                                z += (random.nextDouble() - 0.5) * 0.8;
                                break;
                        }

                        pLevel.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
                    }
                }
                return ItemInteractionResult.SUCCESS;
            }

        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        // Set the entity on fire (excluding ItemEntity to avoid burning items)
        if (!(pEntity instanceof ItemEntity) && !pEntity.fireImmune()) {
            pEntity.setRemainingFireTicks(60); // Sets entity on fire for 3 seconds
        }

        // Item smelting and conversion logic
        if (pEntity instanceof ItemEntity itemEntity) {
            ItemStack itemStack = itemEntity.getItem();
            // Convert all log variants to charcoal
            if (itemStack.is(ItemTags.LOGS)) {
                itemEntity.setItem(new ItemStack(Items.CHARCOAL, itemStack.getCount()));
            }
            // Existing smelting logic
            if (itemStack.getItem() == Items.RAW_GOLD) {
                itemEntity.setItem(new ItemStack(Items.GOLD_INGOT, itemStack.getCount()));
            }

            if (itemStack.getItem() == ModItems.RAW_ALEXANDRITE.get()) {
                itemEntity.setItem(new ItemStack(ModItems.ALEXANDRITE.get(), itemStack.getCount()));
            }

            if (itemStack.getItem() == Items.RAW_IRON) {
                itemEntity.setItem(new ItemStack(Items.IRON_INGOT, itemStack.getCount()));
            }
            if (itemStack.getItem() == Items.RAW_COPPER) {
                itemEntity.setItem(new ItemStack(Items.COPPER_INGOT, itemStack.getCount()));
            }
            if (itemStack.getItem() == Items.RAW_GOLD_BLOCK) {
                itemEntity.setItem(new ItemStack(Items.GOLD_BLOCK, itemStack.getCount()));

            }
            if (itemStack.getItem() == Items.RAW_IRON_BLOCK) {
                itemEntity.setItem(new ItemStack(Items.IRON_BLOCK, itemStack.getCount()));
            }
            if (itemStack.getItem() == Items.RAW_COPPER_BLOCK) {
                itemEntity.setItem(new ItemStack(Items.COPPER_BLOCK, itemStack.getCount()));
            }
            if (itemStack.getItem() == Items.RABBIT_HIDE) {
                itemEntity.setItem(new ItemStack(Items.RABBIT_FOOT, itemStack.getCount()));
            }

            if (itemStack.getItem() == ModBlocks.RAW_ALEXANDRITE_BLOCK.get().asItem()) {
                itemEntity.setItem(new ItemStack(ModBlocks.ALEXANDRITE_BLOCK.get().asItem(), itemStack.getCount()));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        // Only spawn particles on the client side
        if (pLevel.isClientSide) {
            // Spawn one particle on a random face
            int face = pRandom.nextInt(5); // Randomly choose one of the five faces
            double x = pPos.getX() + 0.5;
            double y = pPos.getY() + 0.5;
            double z = pPos.getZ() + 0.5;

            switch (face) {
                case 0: // North (z - 0.5)
                    x += (pRandom.nextDouble() - 0.5) * 0.8; // Random x position
                    y += (pRandom.nextDouble() - 0.5) * 0.8; // Random y position
                    z -= 0.5; // Fixed at north face
                    break;
                case 1: // South (z + 0.5)
                    x += (pRandom.nextDouble() - 0.5) * 0.8;
                    y += (pRandom.nextDouble() - 0.5) * 0.8;
                    z += 0.5;
                    break;
                case 2: // East (x + 0.5)
                    x += 0.5;
                    y += (pRandom.nextDouble() - 0.5) * 0.8;
                    z += (pRandom.nextDouble() - 0.5) * 0.8;
                    break;
                case 3: // West (x - 0.5)
                    x -= 0.5;
                    y += (pRandom.nextDouble() - 0.5) * 0.8;
                    z += (pRandom.nextDouble() - 0.5) * 0.8;
                    break;
                case 4: // Top (y + 1.0)
                    x += (pRandom.nextDouble() - 0.5) * 0.8;
                    y = pPos.getY() + 1.0;
                    z += (pRandom.nextDouble() - 0.5) * 0.8;
                    break;
            }

            pLevel.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
        }
    }
}