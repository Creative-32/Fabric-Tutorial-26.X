package net.creative.tutorialmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record CancelChiselPayload() implements CustomPacketPayload {

    public static final Type<CancelChiselPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath( "tutorialmod", "cancel_chisel"));


    public static final StreamCodec<RegistryFriendlyByteBuf, CancelChiselPayload> STREAM_CODEC =
            StreamCodec.unit(new CancelChiselPayload());


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}