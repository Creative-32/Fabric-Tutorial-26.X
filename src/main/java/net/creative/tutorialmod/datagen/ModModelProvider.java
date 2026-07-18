package net.creative.tutorialmod.datagen;

import net.creative.tutorialmod.block.custom.HoneyBerryBushBlock;
import net.creative.tutorialmod.block.custom.RiceCropBlock;
import net.creative.tutorialmod.block.custom.StrawberryCropBlock;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.block.custom.FluoriteLampBlock;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.item.ModArmorMaterials;
import net.creative.tutorialmod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;

import java.util.Optional;

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
                .button(ModBlocks.FLUORITE_BUTTON)
                .fence(ModBlocks.FLUORITE_FENCE)
                .fenceGate(ModBlocks.FLUORITE_FENCE_GATE)
                .wall(ModBlocks.FLUORITE_WALL);

        // Door
        blockModelGenerators.createDoor(ModBlocks.FLUORITE_DOOR);
        // TrapDoor
        blockModelGenerators.createTrapdoor(ModBlocks.FLUORITE_TRAPDOOR);

        // Blockstate is Off
        Identifier lampOffIdentifier = TexturedModel.CUBE.create(ModBlocks.FLUORITE_LAMP, blockModelGenerators.modelOutput);
        // Blockstate is On
        Identifier lampOnIdentifier = blockModelGenerators.createSuffixedVariant(ModBlocks.FLUORITE_LAMP, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        // Determines which Identifier to Point To
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.FLUORITE_LAMP)
                .with(BlockModelGenerators.createBooleanModelDispatch(FluoriteLampBlock.CLICKED,
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOnIdentifier)).build()),
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOffIdentifier)).build()))));

        // Pedestal
        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.PEDESTAL_BLOCK);

        // Strawberry Crop
        blockModelGenerators.createCropBlock(ModBlocks.STRAWBERRY_CROP, StrawberryCropBlock.AGE, 0, 1, 2, 3, 4, 5);

        // Berry Bush
        blockModelGenerators.createCrossBlock(ModBlocks.HONEY_BERRY_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                HoneyBerryBushBlock.AGE, 0, 1, 2, 3);

        // Rice Crop
        blockModelGenerators.createCropBlock(ModBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);






    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        // Items
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_FLUORITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.STRAWBERRY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COMBUSTIBLE_SPORES, ModelTemplates.FLAT_ITEM);

        //Flat HandHeld Item makes Items Co-Linear instead of Perpendicular to Player Screen
        //itemModelGenerators.generateFlatItem(ModItems.CHISEL, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Tools
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateSpear(ModItems.FLUORITE_SPEAR);

        // Armors
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_HELMET, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_CHESTPLATE, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_LEGGINGS, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_BOOTS, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        // Creates the normal Chisel Model (No Stored Coordinates
        ItemModel.Unbaked unbakedChisel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.CHISEL, ModelTemplates.FLAT_HANDHELD_ITEM));
        // Creates Used Chisel Model
        ItemModel.Unbaked unbakedUsedChisel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.CHISEL, "_used", ModelTemplates.FLAT_HANDHELD_ITEM));
        // Determines Which Texture to Point Too
        itemModelGenerators.itemModelOutput.accept(ModItems.CHISEL,
                new ClientItem(new ConditionalItemModel.Unbaked(Optional.empty(), new HasComponent(ModDataComponents.COORDINATES, false),
                        unbakedUsedChisel, unbakedChisel), new ClientItem.Properties(false, false, 1f)).model());

        // Bow
        itemModelGenerators.createFlatItemModel(ModItems.KAUPEN_BOW, ModelTemplates.BOW);
        itemModelGenerators.generateBow(ModItems.KAUPEN_BOW);

        // Points to Item Model
        itemModelGenerators.declareCustomModelItem(ModItems.SCULKBEAM_STAFF);



    }
}