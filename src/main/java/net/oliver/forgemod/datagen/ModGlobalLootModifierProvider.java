package net.oliver.forgemod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.item.ModItems;
import net.oliver.forgemod.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, ForgeMod.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        /*this.add("kohlrabi_seeds_from_short_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build() }, ModItems.KOHLRABI_SEEDS.get()));*/

        this.add("necromancer_staff_from_stronghold_corridor",
                new AddItemModifier(
                        new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/stronghold_corridor")).build(),
                        },
                        ModItems.NECROMANCER_STAFF.get()
                )
        );
    }
}
