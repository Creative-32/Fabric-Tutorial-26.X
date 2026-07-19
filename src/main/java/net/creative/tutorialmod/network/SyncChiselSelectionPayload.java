package net.creative.tutorialmod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;


public record SyncChiselSelectionPayload(List<BlockPos> positions)
        implements CustomPacketPayload {


    public static final Type<SyncChiselSelectionPayload> TYPE =
            new Type<>(
                    Identifier.fromNamespaceAndPath(
                            "tutorialmod",
                            "sync_chisel_selection"
                    )
            );


    public static final StreamCodec<RegistryFriendlyByteBuf, SyncChiselSelectionPayload> STREAM_CODEC =
            StreamCodec.of(
                    (buf,payload)->{
                        buf.writeCollection(
                                payload.positions(),
                                (b,pos)-> BlockPos.STREAM_CODEC.encode(b,pos)
                        );
                    },
                    buf->{
                        return new SyncChiselSelectionPayload(
                                buf.readList(
                                        BlockPos.STREAM_CODEC
                                )
                        );
                    }
            );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
