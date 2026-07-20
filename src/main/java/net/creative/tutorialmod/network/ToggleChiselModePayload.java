package net.creative.tutorialmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ToggleChiselModePayload(boolean enabled) implements CustomPacketPayload {

    public static final Type<ToggleChiselModePayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath( "tutorialmod", "toggle_chisel_mode"));


    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleChiselModePayload> STREAM_CODEC =
            StreamCodec.of(
                    (buf, payload) -> buf.writeBoolean(payload.enabled()),
                    buf -> new ToggleChiselModePayload(buf.readBoolean())
            );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
