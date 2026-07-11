package net.creative.tutorialmod.item;

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
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    // 2
    public static final Item FLUORITE = registerItem("fluorite", Item::new);
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", Item::new);

    // Tool
    public static final Item Chisel = registerItem("chisel", properties ->  new ChiselItem(properties.durability(32)));

    // Consumable
    public static final Item STRAWBERRY = registerItem("strawberry", properties ->  new Item(properties
            .food(ModFoods.STRAWBERRY, ModFoods.STRAWBERRY_CONSUMABLE))

    // Regular Tooltip
    {@Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.tutorialmod.strawberry"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }}

    );

    // Fuel
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
