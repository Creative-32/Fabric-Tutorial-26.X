package net.creative.tutorialmod.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class ChiselPreviewData {

    private static final Map<BlockPos, BlockState> ORIGINALS = new HashMap<>();

    public static void save( BlockPos pos, BlockState state) {
        ORIGINALS.putIfAbsent( pos, state);
    }

    public static BlockState get( BlockPos pos ){
        return ORIGINALS.get(pos);
    }

    public static void clear(){
        ORIGINALS.clear();
    }

}