package net.oliver.forgemod.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.item.ModItems;

// Register event handler for the Forge event bus
@Mod.EventBusSubscriber(modid = ForgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventHandler {
    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        // Check if the entity is a zombie
        if (event.getEntity() instanceof Zombie zombie) {
            // Generate a random number to determine if the zombie should hold kohlrabi (10% chance)
            if (zombie.getRandom().nextFloat() < 0.1f) {
                // Set the zombie's main hand to hold a kohlrabi item
                zombie.setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, new ItemStack(ModItems.KOHLRABI.get()));
                // Set equipment drop chance to 100% for the main hand to ensure kohlrabi drops when killed
                zombie.setDropChance(net.minecraft.world.entity.EquipmentSlot.MAINHAND, 10);
            }
        }
    }
}