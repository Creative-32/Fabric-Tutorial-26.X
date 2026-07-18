package net.creative.tutorialmod.data;

import net.creative.tutorialmod.TutorialMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {

    // Changed to list
    public static final DataComponentType<List<BlockPos>> COORDINATES =
            register("coordinates",
                    builder -> builder
                            .persistent(BlockPos.CODEC.listOf())
                            .networkSynchronized(
                                    ByteBufCodecs.collection(
                                            ArrayList::new,
                                            BlockPos.STREAM_CODEC
                                    )
                            )
            );





    // Helper Method, T is a Placeholder to the data time
    //       (DataComponentType<List<BlockPos>>), ect
    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {

        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,name),
                builderOperator.apply(DataComponentType.builder()).build()
        );
    }

    public static void registerDataComponents() {
        TutorialMod.LOGGER.info(
                "Registering Data Components for " + TutorialMod.MOD_ID
        );
    }
}