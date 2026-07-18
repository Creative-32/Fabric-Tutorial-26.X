package net.creative.tutorialmod;

import net.creative.tutorialmod.client.key.ModKeyBindings;
import net.creative.tutorialmod.item.custom.ChiselItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class TutorialModClient implements ClientModInitializer {


    public static boolean chiselMode = false;


    @Override
    public void onInitializeClient() {


        ModKeyBindings.registerKeyBindings();


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Toggle Selection Mode (C)
            while(ModKeyBindings.chiselKey.consumeClick()) {
                chiselMode = !chiselMode;
                if(client.player != null) {
                    client.player.sendSystemMessage(
                            Component.literal(
                                    "Chisel Mode: " + chiselMode
                            )
                    );
                }
            }



            // Apply Selected Blocks (V)
            while(ModKeyBindings.applyChiselKey.consumeClick()) {
                if(client.player != null) {
                    ItemStack stack =
                            client.player.getMainHandItem();
                    if(stack.getItem() instanceof ChiselItem) {
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