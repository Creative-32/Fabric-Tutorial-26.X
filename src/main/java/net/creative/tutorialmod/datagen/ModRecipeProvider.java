package net.creative.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override


            // Enter Recipes HERE
            public void buildRecipes() {

                // Type "this." to See More Methods

                // All Blocks/Items that can be Smelted/Blasted
                List<ItemLike> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE, ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE, ModBlocks.FLUORITE_END_ORE);

                // Ore Smelting
                //    * FLUORITE_SMELTABLES are the Ores Listed Above
                oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 200, "fluorite");

                // Ore Blasting
                oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 100, "fluorite");

                // 3x3 Crafting Recipe for items into Blocks
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.FLUORITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_BLOCK);

                // .Pattern represents Crafting Table
                //      * Can have Min of 1 .Pattern with 1 R per .Pattern
                //      * Can have Max of 3 .Pattern with 3 R per .Pattern
                // These Recipes Can Be Used In Any Position of the Table
                shaped(RecipeCategory.MISC, ModBlocks.RAW_FLUORITE_BLOCK)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', ModItems.RAW_FLUORITE)
                        // Unlocks Recipe in the Recipe Book
                        .unlockedBy(getHasName(ModItems.RAW_FLUORITE), has(ModItems.RAW_FLUORITE))
                        .group("fluorite")
                        .save(output);

                // Block into 9 Items
                shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE, 9)
                        // Items/Blocks Required For Recipe
                        .requires(ModBlocks.RAW_FLUORITE_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_FLUORITE_BLOCK), has(ModBlocks.RAW_FLUORITE_BLOCK))
                        .group("fluorite")
                        .save(output);

                // Gives Raw Fluorite from Fluorite and Stick
                //      * If Output Item has Multiple Ways to Be Crafted -> .Save needs a new ID like line 76
                shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE, 4)
                        // Items/Blocks Required For Recipe
                        .requires(ModItems.FLUORITE)
                        .requires(Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output, "raw_fluorite_from_fluorite_and_stick");

            }
        };
    }

    @Override
    public String getName() {
        return "TutorialMod Recipes";
    }
}