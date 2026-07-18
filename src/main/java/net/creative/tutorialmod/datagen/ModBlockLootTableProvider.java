package net.creative.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.block.custom.HoneyBerryBushBlock;
import net.creative.tutorialmod.block.custom.StrawberryCropBlock;
import net.creative.tutorialmod.item.ModItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.creative.tutorialmod.block.custom.RiceCropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        // For Berry Bush
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        // Drops Block when Mined
        dropSelf(ModBlocks.FLUORITE_BLOCK);
        dropSelf(ModBlocks.RAW_FLUORITE_BLOCK);
        dropSelf(ModBlocks.MAGIC_BLOCK);
        // Stair
        dropSelf(ModBlocks.FLUORITE_STAIRS);
        // Slab
        add(ModBlocks.FLUORITE_SLAB, this::createSlabItemTable); // 2 Blocks in One
        // Pressure Plate
        dropSelf(ModBlocks.FLUORITE_PRESSURE_PLATE);
        // Button
        dropSelf(ModBlocks.FLUORITE_BUTTON);
        // Fence
        dropSelf(ModBlocks.FLUORITE_FENCE);
        // Gate
        dropSelf(ModBlocks.FLUORITE_FENCE_GATE);
        // Wall
        dropSelf(ModBlocks.FLUORITE_WALL);
        // Door
        add(ModBlocks.FLUORITE_DOOR, this::createDoorTable); // 2 Blocks in One
        // Trap Door
        dropSelf(ModBlocks.FLUORITE_TRAPDOOR);
        // Lamp
        dropSelf(ModBlocks.FLUORITE_LAMP);
        // Pedestal
        dropSelf(ModBlocks.PEDESTAL_BLOCK);

        // Strawberry Crop
        this.add(ModBlocks.STRAWBERRY_CROP, this.createCropDrops(ModBlocks.STRAWBERRY_CROP, ModItems.STRAWBERRY, ModItems.STRAWBERRY_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, StrawberryCropBlock.MAX_AGE))));

        // Berry Bush
        this.add(ModBlocks.HONEY_BERRY_BUSH, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HoneyBerryBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        // Apply Bonus for Enchants
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HoneyBerryBushBlock.AGE, 2)))
                        .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))
        );

        // Rice Crop
        this.add(ModBlocks.RICE_CROP, this.createCropDrops(ModBlocks.RICE_CROP, ModItems.RICE_SHOOT, ModItems.RICE_SHOOT,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropBlock.AGE, RiceCropBlock.MAX_AGE))));

















        // Drops a Single Piece of Ore when Mined?
        add(ModBlocks.FLUORITE_ORE, createOreDrop(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        add(ModBlocks.FLUORITE_DEEPSLATE_ORE, createOreDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE));

        // Drops Random Amount of Ore
        add(ModBlocks.FLUORITE_NETHER_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 3, 6));
        add(ModBlocks.FLUORITE_END_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 5, 8));

    }

    // Loot Table & Enchant Effects???
    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}