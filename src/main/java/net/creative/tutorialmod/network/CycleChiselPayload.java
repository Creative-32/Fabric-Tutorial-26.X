package net.creative.tutorialmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;


public record CycleChiselPayload()
        implements CustomPacketPayload {


    public static final Type<CycleChiselPayload> TYPE =
            new Type<>(
                    Identifier.fromNamespaceAndPath(
                            "tutorialmod",
                            "cycle_chisel"
                    )
            );


    public static final StreamCodec<RegistryFriendlyByteBuf, CycleChiselPayload> STREAM_CODEC =
            StreamCodec.unit(
                    new CycleChiselPayload()
            );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
