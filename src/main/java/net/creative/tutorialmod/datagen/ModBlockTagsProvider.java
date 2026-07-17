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
        tag(BlockTags.MINEABLE_WITH_PICKAXE) // Makes blocks only able to be mined with a Pickaxe
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.RAW_FLUORITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DEEPSLATE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_END_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DOOR))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.PEDESTAL_BLOCK));



        ;

        tag(BlockTags.NEEDS_IRON_TOOL) // Makes blocks only able to be mined with an Iron Pickaxe and below
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DEEPSLATE_ORE));

        tag(BlockTags.NEEDS_DIAMOND_TOOL) // Makes blocks only able to be mined with a Diamond Pickaxe and below
                .add(ModBlocks.getRK(ModBlocks.MAGIC_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_END_ORE));

        // Doesn't Do Anything, Another Mod Might need them tho
        tag(BlockTags.STAIRS).add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS));
        tag(BlockTags.SLABS).add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB));
        tag(BlockTags.STAIRS).add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS));
        tag(BlockTags.SLABS).add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB));

        tag(BlockTags.FENCES).add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE));
        //      * .Fence - Connects to Brick Fences
        //      * .WoodenFence - Connects to Wooden Fences
        tag(BlockTags.FENCE_GATES).add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE_GATE));
        tag(BlockTags.WALLS).add(ModBlocks.getRK(ModBlocks.FLUORITE_WALL));

        tag(BlockTags.DOORS).add(ModBlocks.getRK(ModBlocks.FLUORITE_DOOR));
        tag(BlockTags.TRAPDOORS).add(ModBlocks.getRK(ModBlocks.FLUORITE_TRAPDOOR));

        // ---------------------------------------------------------
        tag(ModTags.Blocks.NEEDS_FLUORITE_TOOL)
                .add(ModBlocks.getRK(ModBlocks.MAGIC_BLOCK))
                // Can Mine Magic Block + Everything an Iron Tool Can
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL)
                // Fluorite Tool Cannot Mine Anything that Requires a Diamond Tool
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        // ---------------------------------------------------------













    }
}