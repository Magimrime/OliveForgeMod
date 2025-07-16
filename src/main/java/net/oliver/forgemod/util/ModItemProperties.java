package net.oliver.forgemod.util;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.component.ModDataComponentTypes;
import net.oliver.forgemod.item.ModItems;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.CHISEL.get(), ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, "used"),
                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f :0f);
    }
}
