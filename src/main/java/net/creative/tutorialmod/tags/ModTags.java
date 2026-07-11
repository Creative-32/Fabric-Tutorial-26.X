package net.creative.tutorialmod.tags;

import net.creative.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    // Blocks
    public static class Blocks {
        // No Tag


        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }

    // Items
    public static class Items {
        // Name of Tag
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");


        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }
}