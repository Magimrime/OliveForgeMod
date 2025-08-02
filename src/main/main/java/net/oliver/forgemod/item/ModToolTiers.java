package net.oliver.forgemod.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.oliver.forgemod.util.ModTags;

public class ModToolTiers {
    public static final Tier ALEXANDRITE = new ForgeTier(800, 3, 4f,16,
            ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL, () -> Ingredient.of(ModItems.ALEXANDRITE.get()),
            ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL);
}
