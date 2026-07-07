package net.creative.tutorialmod;

import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.creativemodetabs.ModCreativeModeTabs;
import net.fabricmc.api.ModInitializer;

import net.creative.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
