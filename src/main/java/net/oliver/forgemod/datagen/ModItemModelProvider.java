package net.oliver.forgemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ForgeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.RAW_ALEXANDRITE.get());

        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.KHOLRABI.get());
        basicItem(ModItems.AURORA_ASHES.get());
    }
}


