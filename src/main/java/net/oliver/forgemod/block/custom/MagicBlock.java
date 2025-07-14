package net.oliver.forgemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
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

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos,
                                               Player pPlayer, BlockHitResult pHitResult) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f, 1f);
        return InteractionResult.SUCCESS;
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
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}