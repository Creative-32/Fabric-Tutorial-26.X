package net.creative.tutorialmod.item;

import net.creative.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.creative.tutorialmod.TutorialMod;
import net.creative.tutorialmod.food.ModFoods;
import net.creative.tutorialmod.item.custom.ChiselItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {



    // --------------------------------------        Ore        --------------------------------------
    public static final Item FLUORITE = registerItem("fluorite", Item::new);
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", Item::new);



    // --------------------------------------       Items       --------------------------------------
    public static final Item CHISEL = registerItem("chisel", properties ->  new ChiselItem(properties.durability(32)));



    // --------------------------------------    Consumable    --------------------------------------
    public static final Item STRAWBERRY = registerItem("strawberry", properties ->  new Item(properties
            .food(ModFoods.STRAWBERRY, ModFoods.STRAWBERRY_CONSUMABLE))
    // Regular Tooltip
    {@Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.tutorialmod.strawberry"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }}
    );



    // --------------------------------------       Fuels       --------------------------------------
    public static final Item COMBUSTIBLE_SPORES = registerItem("combustible_spores",
            properties -> new Item(properties.stacksTo(16))
            // ToolTip on Shift
            {@Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                            Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if (Minecraft.getInstance().hasShiftDown()) {
                        builder.accept(Component.translatable("tooltip.tutorialmod.combustible_spores.shift_down"));
                    } else {
                        builder.accept(Component.translatable("tooltip.tutorialmod.combustible_spores"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }}
            );



    // --------------------------------------       Tools       --------------------------------------
    // Sword
    public static final Item FLUORITE_SWORD = registerItem("fluorite_sword",
            properties -> new Item(properties.sword(ModToolMaterials.FLUORITE, 3, -2.4f)));
    // Pickaxe
    public static final Item FLUORITE_PICKAXE = registerItem("fluorite_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.FLUORITE, 1, -2.8f)));
    // Shovel
    public static final Item FLUORITE_SHOVEL = registerItem("fluorite_shovel",
            properties -> new ShovelItem(ModToolMaterials.FLUORITE, 1.5f, -3.0f, properties));
    // Axe
    public static final Item FLUORITE_AXE = registerItem("fluorite_axe",
            properties -> new AxeItem(ModToolMaterials.FLUORITE, 6f, -3.2f, properties));
    // Hoe
    public static final Item FLUORITE_HOE = registerItem("fluorite_hoe",
            properties -> new HoeItem(ModToolMaterials.FLUORITE, 0f, -3.0f, properties));
    // Spear
    public static final Item FLUORITE_SPEAR = registerItem("fluorite_spear",
            properties -> new Item(properties.spear(ModToolMaterials.FLUORITE, 0.95F, 0.95F, 0.6F,
                    2.5F, 11.0F, 6.75F, 5.1F, 11.25F, 4.6F)));
    // Bow
    public static final Item KAUPEN_BOW = registerItem("kaupen_bow",
            properties -> new BowItem(properties.durability(500)));



//-------------------------------------------       Custom Item Model       -------------------------------------------

    // Sculk Beam Staff (Custom Item Model)
    public static final Item SCULKBEAM_STAFF = registerItem("sculkbeam_staff",
            properties -> new Item(properties.stacksTo(1)));

//---------------------------------------------------------------------------------------------------------------------











    // --------------------------------------       Armors       --------------------------------------
    // Helmet
    public static final Item FLUORITE_HELMET = registerItem("fluorite_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.HELMET)));
    // ChestPlate
    public static final Item FLUORITE_CHESTPLATE = registerItem("fluorite_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    // Legging
    public static final Item FLUORITE_LEGGINGS = registerItem("fluorite_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    // Boots
    public static final Item FLUORITE_BOOTS = registerItem("fluorite_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // Horse
    public static final Item FLUORITE_HORSE_ARMOR = registerItem("fluorite_horse_armor",
            // Change .horseArmor to Any Other Animal for their Armor Type
            properties -> new Item(properties.horseArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL)));

    // Strawberry
    public static final Item STRAWBERRY_SEEDS = registerItem("strawberry_seeds",
            properties -> new BlockItem(ModBlocks.STRAWBERRY_CROP, properties.useItemDescriptionPrefix()));


















    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    // Adds Items to Ingredient Tab
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);
        // 2
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(FLUORITE);
            output.accept(RAW_FLUORITE);

        });
    }
}
