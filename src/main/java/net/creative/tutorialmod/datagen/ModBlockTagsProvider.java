package net.creative.tutorialmod.datagen;

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
                .add(ModBlocks.MAGIC_BLOCK)
                .add(ModBlocks.FLUORITE_STAIRS)
                .add(ModBlocks.FLUORITE_SLAB);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL) // Makes blocks only able to be mined with an Iron Pickaxe and below
                .add(ModBlocks.FLUORITE_DEEPSLATE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL) // Makes blocks only able to be mined with a Diamond Pickaxe and below
                .add(ModBlocks.FLUORITE_NETHER_ORE)
                .add(ModBlocks.FLUORITE_END_ORE);

        // Doesn't Do Anything, Another Mod Might need them tho
        valueLookupBuilder(BlockTags.STAIRS).add(ModBlocks.FLUORITE_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(ModBlocks.FLUORITE_SLAB);




    }
}