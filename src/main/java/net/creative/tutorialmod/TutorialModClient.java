package net.creative.tutorialmod;

import net.creative.tutorialmod.client.key.ModKeyBindings;
import net.creative.tutorialmod.client.render.ChiselSelectionRenderer;
import net.creative.tutorialmod.item.custom.ChiselItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

public class TutorialModClient implements ClientModInitializer {

    // Default Chisel Mde
    public static boolean chiselMode = false;

    @Override
    public void onInitializeClient() {

        ModKeyBindings.registerKeyBindings();

        LevelRenderEvents.BEFORE_GIZMOS.register(ChiselSelectionRenderer::render);

        ClientTickEvents.END_CLIENT_TICK.register(client -> { // Continuously listening for Keybind Press


            // Toggle Selection Mode (C)
            while(ModKeyBindings.chiselKey.consumeClick()) { //Checks if Custom "C" Keybind was Pressed
                chiselMode = !chiselMode; //Changes Chisel Mod
                if(client.player != null) { // System Message on KeyPress
                    client.player.sendSystemMessage(
                            Component.literal("Chisel Mode: " + chiselMode)
                    );
                }
            }

            // Apply Selected Blocks (V)
            while(ModKeyBindings.applyChiselKey.consumeClick()) { //Checks if Custom "V" Keybind was Pressed
                if(client.player != null) { // Makes sure Player Exist
                    ItemStack stack = client.player.getMainHandItem(); // Gets what Item the Player is Holding
                    if(stack.getItem() instanceof ChiselItem) { // If Player is Holding "Chisel" on "V" Press do Below
                        ChiselItem.chiselSelectedBlocks(
                                client.level,
                                client.player,
                                stack
                        );
                    }
                }
            }
        });
    }
}