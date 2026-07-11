package net.creative.tutorialmod.tags;

import net.creative.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        // Everything that Can be Mined with Specific Tool
        public static final TagKey<Block> NEEDS_FLUORITE_TOOL = createTag("needs_fluorite_tool");
        // Everything that Cant be Mined with Specific Tool
        public static final TagKey<Block> INCORRECT_FOR_FLUORITE_TOOL = createTag("incorrect_for_fluorite_tool");




        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }

    public static class Items {
        // List of Items that can be Transformed into Diamonds using the Magic Block
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        // Items that Can be Used to Repair Tool
        public static final TagKey<Item> FLUORITE_REPAIR = createTag("fluorite_repair");






        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }
}