package net.creative.tutorialmod.block;

import net.creative.tutorialmod.TutorialMod;
import net.creative.tutorialmod.block.custom.FluoriteLampBlock;
import net.creative.tutorialmod.block.custom.MagicBlock;
import net.creative.tutorialmod.block.custom.PedestalBlock;
import net.creative.tutorialmod.block.custom.StrawberryCropBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModBlocks {

    // General
    public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final Block RAW_FLUORITE_BLOCK = registerBlock("raw_fluorite_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops()));

    // Ore
    public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5),
                    properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 6),
                    properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
            properties -> new DropExperienceBlock(UniformInt.of(1, 5),
                    properties.strength(3f).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
            properties -> new DropExperienceBlock(UniformInt.of(4, 8),
                    properties.strength(6f).requiresCorrectToolForDrops()));

    // Specialty
    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)),
            // Block ToolTip
            Component.translatable("tooltip.tutorialmod.magic_block"));

    // Stairs
    public static final Block FLUORITE_STAIRS = registerBlock("fluorite_stairs",
            properties -> new StairBlock(ModBlocks.FLUORITE_BLOCK.defaultBlockState(),
                    properties.strength(3f).requiresCorrectToolForDrops()));

    // Slabs
    public static final Block FLUORITE_SLAB = registerBlock("fluorite_slab",
            properties -> new SlabBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    // Button
    public static final Block FLUORITE_BUTTON = registerBlock("fluorite_button",
                                                                 // Iron - Sensitivity
            properties -> new ButtonBlock(BlockSetType.IRON, 20,
                    properties.strength(3f).noCollision()));

    // Pressure Plate
    public static final Block FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON,
                    properties.mapColor(MapColor.COLOR_BLUE).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                            .noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY)));

    // Fence
    public static final Block FLUORITE_FENCE = registerBlock("fluorite_fence",
            properties -> new FenceBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    // Gate
    public static final Block FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate",
            properties -> new FenceGateBlock(WoodType.ACACIA,
                    properties.strength(3f).requiresCorrectToolForDrops()));

    // Wall
    public static final Block FLUORITE_WALL = registerBlock("fluorite_wall",
            properties -> new WallBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    // Door
    public static final Block FLUORITE_DOOR = registerBlock("fluorite_door",

            properties -> new DoorBlock(BlockSetType.ACACIA, properties.strength(3f)
                    .requiresCorrectToolForDrops().noOcclusion())); // .noOcclusion - For Transparent Pixels Otherwise it will be a Xray

    // Trap Door
    public static final Block FLUORITE_TRAPDOOR = registerBlock("fluorite_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.ACACIA, properties.strength(3f)
                    .requiresCorrectToolForDrops().noOcclusion()));

    // Lamp
    public static final Block FLUORITE_LAMP = registerBlock("fluorite_lamp",
            properties -> new FluoriteLampBlock(properties.strength(3f)
                    .requiresCorrectToolForDrops().lightLevel(state -> state.getValue(FluoriteLampBlock.CLICKED) ? 15 : 0)));
                    // lightLevel(state -> 15) - Sets Light Level
                    // state.getValue(FluoriteLampBlock.CLICKED) ? 15 : 0) - Sets Light Level when Lamp is Clicked
    // Pedestal Block
                    public static final Block PEDESTAL_BLOCK = registerBlock("pedestal",
                            properties -> new PedestalBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    // Strawberry
    public static final Block STRAWBERRY_CROP = registerBlockWithoutBlockItem("strawberry_crop",
            // NO Collision, Random Growth, Instant Break, Break Sound, Piston Push Breaks
            properties -> new StrawberryCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)));











    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, Component... tooltips) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        registerBlockItem(name, toRegister, tooltips);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block, Component... tooltips) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))) {
                    @Override
                    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        for(var component : tooltips) {
                            builder.accept(component);
                        }
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                    }
                });
    }

    // For Seeds
    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for" + TutorialMod.MOD_ID);
    }

}
