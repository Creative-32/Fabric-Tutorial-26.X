package net.creative.tutorialmod.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {


    public static final KeyMapping.Category CHISEL_CATEGORY =
            KeyMapping.Category.register(
                    Identifier.fromNamespaceAndPath(
                            "tutorialmod",
                            "chisel"
                    )
            );


    public static KeyMapping chiselKey;
    public static KeyMapping applyChiselKey;



    public static void registerKeyBindings() {


        chiselKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.tutorialmod.chisel",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_C,
                        CHISEL_CATEGORY
                )
        );


        applyChiselKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.tutorialmod.apply_chisel",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_V,
                        CHISEL_CATEGORY
                )
        );











    }
}
