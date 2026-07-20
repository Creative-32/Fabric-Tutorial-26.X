package net.creative.tutorialmod;

import net.creative.tutorialmod.client.key.ModKeyBindings;
import net.creative.tutorialmod.client.render.ChiselSelectionRenderer;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.item.custom.ChiselItem;

import net.creative.tutorialmod.network.*;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;


public class TutorialModClient implements ClientModInitializer {

    // Client side display only
    public static boolean chiselMode = false;

    @Override
    public void onInitializeClient() {

        // Block selection outline renderer
        LevelRenderEvents.BEFORE_GIZMOS.register(ChiselSelectionRenderer::render);

//----------------------------------------------     Chisel Keybinds     ----------------------------------------------
        ModKeyBindings.registerKeyBindings();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            ItemStack stack = client.player.getMainHandItem();

            if(client.player == null) // Might Want to Delete this for the Redstone Block Auto Chisel
                return;

            if(!(stack.getItem() instanceof ChiselItem))
                return;


            // C - Toggle Selection Mode
            while(ModKeyBindings.chiselKey.consumeClick()){
                chiselMode = !chiselMode;
                ClientPlayNetworking.send(new ToggleChiselModePayload(chiselMode));
                client.player.sendSystemMessage(
                        Component.literal(
                                "Selection Mode: "
                                        + chiselMode
                        )
                );
            }

            // R - Cycle Preview
            while(ModKeyBindings.cycleChiselKey.consumeClick()){
                ClientPlayNetworking.send(new CycleChiselPayload());
            }

            // V - Confirm Preview
            while(ModKeyBindings.applyChiselKey.consumeClick()){
                ClientPlayNetworking.send(new ApplyChiselPayload());
                client.player.sendSystemMessage(
                        Component.literal(
                                "Preview Confirmed"
                        )
                );
            }


            // X - Cancel Preview
            while(ModKeyBindings.cancelChiselKey.consumeClick()){
                ClientPlayNetworking.send(new CancelChiselPayload());
                client.player.sendSystemMessage(
                        Component.literal(
                                "Preview Cancelled"
                        )
                );
            }



        });

//---------------------------------------------     Sync Chisel Packet     ---------------------------------------------

        // Receive Selected Blocks From Server
        ClientPlayNetworking.registerGlobalReceiver(SyncChiselSelectionPayload.TYPE,
                (payload, context) -> {context.client().execute(() -> {

                        if(context.client().player == null)
                            return;

                        ItemStack stack = context.client().player.getMainHandItem();

                        if(stack.getItem() instanceof ChiselItem){
                            stack.set(
                                    ModDataComponents.COORDINATES,
                                    payload.positions()
                            );
                        }
                    });
                }
        );

//----------------------------------------------------------------------------------------------------------------------





    }

}