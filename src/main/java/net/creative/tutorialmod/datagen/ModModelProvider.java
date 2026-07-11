package net.creative.tutorialmod.datagen;

import net.creative.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.creative.tutorialmod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        //blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.RAW_FLUORITE_BLOCK);
        // Ore
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_DEEPSLATE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_NETHER_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_END_ORE);

        // Specialty
        blockModelGenerators.createTrivialCube(ModBlocks.MAGIC_BLOCK);

        // Changed to Block Family since Texture is the Same for Every Side
        blockModelGenerators.family(ModBlocks.FLUORITE_BLOCK)
                .stairs(ModBlocks.FLUORITE_STAIRS)
                .slab(ModBlocks.FLUORITE_SLAB)
                .pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE)
                .button(ModBlocks.FLUORITE_BUTTON);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        // Items
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_FLUORITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.STRAWBERRY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COMBUSTIBLE_SPORES, ModelTemplates.FLAT_ITEM);

        //Flat HandHeld Item makes Items Co-Linear instead of Perpendicular to Player Screen
        itemModelGenerators.generateFlatItem(ModItems.Chisel, ModelTemplates.FLAT_HANDHELD_ITEM);

    }
}