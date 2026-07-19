package net.creative.tutorialmod.network;

import net.creative.tutorialmod.data.ChiselPlayerData;
import net.creative.tutorialmod.item.custom.ChiselItem;
import net.creative.tutorialmod.data.ModDataComponents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class ModPackets {


    public static void register() {


        // C - Toggle Selection Mode
        PayloadTypeRegistry.serverboundPlay()
                .register(
                        ToggleChiselModePayload.TYPE,
                        ToggleChiselModePayload.STREAM_CODEC
                );


        // V - Confirm Preview
        PayloadTypeRegistry.serverboundPlay()
                .register(
                        ApplyChiselPayload.TYPE,
                        ApplyChiselPayload.STREAM_CODEC
                );


        // R - Cycle Preview
        PayloadTypeRegistry.serverboundPlay()
                .register(
                        CycleChiselPayload.TYPE,
                        CycleChiselPayload.STREAM_CODEC
                );


        // X - Cancel Preview
        PayloadTypeRegistry.serverboundPlay()
                .register(
                        CancelChiselPayload.TYPE,
                        CancelChiselPayload.STREAM_CODEC
                );


        // Server -> Client selected blocks
        PayloadTypeRegistry.clientboundPlay()
                .register(
                        SyncChiselSelectionPayload.TYPE,
                        SyncChiselSelectionPayload.STREAM_CODEC
                );





        // C - Toggle Selection Mode
        ServerPlayNetworking.registerGlobalReceiver(
                ToggleChiselModePayload.TYPE,
                (payload, context) -> {
                    context.server().execute(() -> {
                        ChiselPlayerData.setChiselMode(
                                context.player(),
                                payload.enabled()
                        );
                        context.player()
                                .sendSystemMessage(
                                        Component.literal(
                                                "Selection Mode: "
                                                        + payload.enabled()
                                        )
                                );
                    });
                }
        );





        // R - Cycle Preview
        ServerPlayNetworking.registerGlobalReceiver(
                CycleChiselPayload.TYPE,
                (payload, context) -> {
                    context.server().execute(() -> {
                        Player player =
                                context.player();
                        ItemStack stack =
                                player.getMainHandItem();
                        if(stack.getItem()
                                instanceof ChiselItem){
                            ChiselItem.cyclePreview(
                                    player.level(),
                                    player,
                                    stack
                            );
                        }
                    });
                }
        );


        // V - Confirm Preview
        ServerPlayNetworking.registerGlobalReceiver(
                ApplyChiselPayload.TYPE,
                (payload, context) -> {
                    context.server().execute(() -> {
                        Player player =
                                context.player();
                        ItemStack stack =
                                player.getMainHandItem();
                        if(stack.getItem()
                                instanceof ChiselItem){
                            ChiselItem.confirmPreview(
                                    player.level(),
                                    player,
                                    stack
                            );
                        }
                    });
                }
        );


        // X - Cancel Preview
        ServerPlayNetworking.registerGlobalReceiver(
                CancelChiselPayload.TYPE,
                (payload, context) -> {
                    context.server().execute(() -> {
                        Player player =
                                context.player();
                        ItemStack stack =
                                player.getMainHandItem();
                        if(stack.getItem()
                                instanceof ChiselItem){
                            ChiselItem.cancelPreview(
                                    player.level(),
                                    player,
                                    stack
                            );
                        }
                    });
                }
        );





    }
}