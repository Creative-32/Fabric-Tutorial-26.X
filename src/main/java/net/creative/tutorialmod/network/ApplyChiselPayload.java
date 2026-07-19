package net.creative.tutorialmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;


public record ApplyChiselPayload()
        implements CustomPacketPayload {


    public static final Type<ApplyChiselPayload> TYPE =
            new Type<>(
                    Identifier.fromNamespaceAndPath(
                            "tutorialmod",
                            "apply_chisel"
                    )
            );


    public static final StreamCodec<RegistryFriendlyByteBuf, ApplyChiselPayload> STREAM_CODEC =
            StreamCodec.unit(
                    new ApplyChiselPayload()
            );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
