package net.creative.tutorialmod.datagen;

import net.creative.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.creative.tutorialmod.item.ModItems;
import net.creative.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.getRK(ModItems.FLUORITE))
                .add(ModItems.getRK(Items.IRON_INGOT))
                .add(ModItems.getRK(Items.COAL))
                .add(ModItems.getRK(Items.BRICK));

        // Allows Items to Be Enchanted as that Specific Item
        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.FLUORITE_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.FLUORITE_PICKAXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.FLUORITE_SHOVEL));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.FLUORITE_AXE));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.FLUORITE_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.FLUORITE_SPEAR));

        tag(ItemTags.HEAD_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_HELMET));
        tag(ItemTags.CHEST_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_BOOTS));

        tag(ItemTags.BOW_ENCHANTABLE).add(ModItems.getRK(ModItems.KAUPEN_BOW));

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(ModItems.getRK(ModItems.BAR_BRAWL_MUSIC_DISC));







    }
}