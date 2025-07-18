package net.oliver.forgemod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.block.ModBlocks;
import net.oliver.forgemod.item.custom.ChiselItem;
import net.oliver.forgemod.item.custom.FuelItem;
import net.oliver.forgemod.item.custom.HammerItem;
import net.oliver.forgemod.item.custom.ModArmorItem;
import net.oliver.forgemod.sound.ModSounds;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ForgeMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> KHOLRABI = ITEMS.register("kohlrabi",
        () -> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)) {
            @Override
            public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                pTooltipComponents.add(Component.translatable("tooltip.oliveforgemod.kohlrabi"));
                super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            }
        });

    public static final RegistryObject<Item> WALNUT = ITEMS.register("walnut",
            () -> new Item(new Item.Properties().food(ModFoodProperties.WALNUT)));


    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register("aurora_ashes",
            () -> new FuelItem(new Item.Properties(),20000));

    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register("alexandrite_sword",
            () -> new SwordItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ALEXANDRITE,3,-2.4f))));
    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register("alexandrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE,1,-2.8f))));
    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register("alexandrite_shovel",
            () -> new ShovelItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.ALEXANDRITE,1.5f,-3.0f))));
    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register("alexandrite_axe",
            () -> new AxeItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.ALEXANDRITE,6,-3.2f))));
    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register("alexandrite_hoe",
            () -> new HoeItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.ALEXANDRITE,0,-3.0f))));

    public static final RegistryObject<Item> ALEXANDRITE_HAMMER = ITEMS.register("alexandrite_hammer",
            () -> new HammerItem(ModToolTiers.ALEXANDRITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE,7,-3.5f))));

    public static final RegistryObject<Item> ALEXANDRITE_HELMET = ITEMS.register("alexandrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(10))));
    public static final RegistryObject<Item> ALEXANDRITE_CHESTPLATE = ITEMS.register("alexandrite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(15))));
    public static final RegistryObject<Item> ALEXANDRITE_LEGGINGS = ITEMS.register("alexandrite_leggings",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(14))));
    public static final RegistryObject<Item> ALEXANDRITE_BOOTS = ITEMS.register("alexandrite_boots",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(12))));

    public static final RegistryObject<Item> ALEXANDRITE_HORSE_ARMOR = ITEMS.register("alexandrite_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> KAUPEN_SMITHING_TEMPLATE = ITEMS.register("kaupen_armor_trim_smithing_template",
            () -> SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, "kaupen")));

    public static final RegistryObject<Item> ALEXANDRITE_BOW = ITEMS.register("alexandrite_bow",
            () -> new BowItem(new Item.Properties().durability(500)));

    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).stacksTo(1)));
    public static final RegistryObject<Item> ROLL_MUSIC_DISC = ITEMS.register("roll_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.ROLL_KEY).stacksTo(1)));
    public static final RegistryObject<Item> TAKE_MUSIC_DISC = ITEMS.register("take_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.TAKE_KEY).stacksTo(1)));

    public static final RegistryObject<Item> KOHLRABI_SEEDS = ITEMS.register("kohlrabi_seeds",
            () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> NIGHT_BERRIES = ITEMS.register("night_berries",
            () -> new ItemNameBlockItem(ModBlocks.NIGHT_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.NIGHT_BERRY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
