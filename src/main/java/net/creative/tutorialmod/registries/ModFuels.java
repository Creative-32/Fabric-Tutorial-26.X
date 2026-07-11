package net.creative.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.creative.tutorialmod.item.ModItems;

public class ModFuels {
    public static void registerFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            // Add Burn Time
            builder.add(ModItems.COMBUSTIBLE_SPORES, 1200); // 1200 ticks is 60 Seconds


        });
    }
}