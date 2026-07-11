package net.creative.tutorialmod.datagen;

import net.creative.tutorialmod.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.creative.tutorialmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE) // Makes blocks only able to be mined with a Pickaxe
                .add(ModBlocks.FLUORITE_BLOCK)
                .add(ModBlocks.RAW_FLUORITE_BLOCK)
                .add(ModBlocks.FLUORITE_ORE)
                .add(ModBlocks.FLUORITE_DEEPSLATE_ORE)
                .add(ModBlocks.FLUORITE_NETHER_ORE)
                .add(ModBlocks.FLUORITE_END_ORE)
                .add(ModBlocks.FLUORITE_STAIRS)
                .add(ModBlocks.FLUORITE_SLAB)
                .add(ModBlocks.FLUORITE_FENCE)
                .add(ModBlocks.FLUORITE_FENCE_GATE)
                .add(ModBlocks.FLUORITE_WALL)
                .add(ModBlocks.FLUORITE_DOOR)
                .add(ModBlocks.FLUORITE_TRAPDOOR)



        ;

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL) // Makes blocks only able to be mined with an Iron Pickaxe and below
                .add(ModBlocks.FLUORITE_DEEPSLATE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL) // Makes blocks only able to be mined with a Diamond Pickaxe and below
                .add(ModBlocks.MAGIC_BLOCK)
                .add(ModBlocks.FLUORITE_NETHER_ORE)
                .add(ModBlocks.FLUORITE_END_ORE);

        // Doesn't Do Anything, Another Mod Might need them tho
        valueLookupBuilder(BlockTags.STAIRS).add(ModBlocks.FLUORITE_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(ModBlocks.FLUORITE_SLAB);
        valueLookupBuilder(BlockTags.STAIRS).add(ModBlocks.FLUORITE_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(ModBlocks.FLUORITE_SLAB);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.FLUORITE_FENCE);
        //      * .Fence - Connects to Brick Fences
        //      * .WoodenFence - Connects to Wooden Fences
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.FLUORITE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.FLUORITE_WALL);

        valueLookupBuilder(BlockTags.DOORS).add(ModBlocks.FLUORITE_DOOR);
        valueLookupBuilder(BlockTags.TRAPDOORS).add(ModBlocks.FLUORITE_TRAPDOOR);

        // ---------------------------------------------------------
        valueLookupBuilder(ModTags.Blocks.NEEDS_FLUORITE_TOOL)
                .add(ModBlocks.MAGIC_BLOCK)
                // Can Mine Magic Block + Everything an Iron Tool Can
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        valueLookupBuilder(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL)
                // Fluorite Tool Cannot Mine Anything that Requires a Diamond Tool
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        // ---------------------------------------------------------













    }
}