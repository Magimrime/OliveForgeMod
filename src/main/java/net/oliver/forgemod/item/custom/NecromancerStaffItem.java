package net.oliver.forgemod.item.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.PlayerTeam;
import java.util.function.Consumer;

public class NecromancerStaffItem extends SwordItem {

    public NecromancerStaffItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            // Get or create "necromancer_minions" team
            Scoreboard scoreboard = level.getScoreboard();
            PlayerTeam team = scoreboard.getPlayerTeam("necromancer_minions");
            if (team == null) {
                team = scoreboard.addPlayerTeam("necromancer_minions");
                team.setAllowFriendlyFire(false);
                team.setSeeFriendlyInvisibles(true);
            }

            // Add player to team
            scoreboard.addPlayerToTeam(player.getScoreboardName(), team);

            double px = player.getX(), py = player.getY() + 1, pz = player.getZ();
            double radius = 3.0;

            for (int i = 0; i < 3; i++) {
                double angle = Math.toRadians(120 * i);
                double dx = px + radius * Math.cos(angle);
                double dz = pz + radius * Math.sin(angle);

                Skeleton skeleton = EntityType.SKELETON.create(level);
                if (skeleton != null) {
                    skeleton.setPos(dx, py, dz);

                    if (level.getRandom().nextBoolean()) {
                        // 50% chance: Bow
                        skeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                        skeleton.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
                        skeleton.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
                        skeleton.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
                        skeleton.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
                    } else {
                        // 50% chance: Sword + Shield
                        skeleton.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                        skeleton.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
                        skeleton.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                        skeleton.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                        skeleton.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
                        skeleton.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
                    }

                    scoreboard.addPlayerToTeam(skeleton.getScoreboardName(), team);

                    skeleton.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(skeleton, Monster.class, true,
                            target -> !(target instanceof Player) && !target.isAlliedTo(skeleton)));

                    level.addFreshEntity(skeleton);
                }
            }
            // Apply durability damage and cooldown
            ItemStack stack = player.getItemInHand(hand);
            stack.hurtAndBreak(3, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            player.getCooldowns().addCooldown(this, 100); // 5 seconds (20 ticks per second * 5)
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}