package net.creative.tutorialmod.data;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChiselPlayerData {

    private static final Set<UUID> CHISEL_MODE_PLAYERS = new HashSet<>();

    public static void setChiselMode(ServerPlayer player, boolean enabled) {
        if(enabled) {
            CHISEL_MODE_PLAYERS.add(player.getUUID());
        } else {
            CHISEL_MODE_PLAYERS.remove(player.getUUID());
        }
    }


    public static boolean isChiselMode(ServerPlayer player) {
        return CHISEL_MODE_PLAYERS.contains(player.getUUID());
    }


}
