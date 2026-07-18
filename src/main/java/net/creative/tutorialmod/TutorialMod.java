package net.creative.tutorialmod;

import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.creativemodetabs.ModCreativeModeTabs;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.registries.ModCompostables;
import net.creative.tutorialmod.registries.ModFuels;
import net.creative.tutorialmod.stat.ModStats;
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

		ModDataComponents.registerDataComponents();
		ModStats.registerStats();

		ModFuels.registerFuels();
		ModCompostables.registerCompostables();


	}
}
