package net.creative.tutorialmod.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {

    // Keybind Category
    public static final KeyMapping.Category CHISEL_CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath( "tutorialmod", "chisel"));

    // Keybinds
    public static KeyMapping chiselKey;        // C
    public static KeyMapping cycleChiselKey;   // R
    public static KeyMapping applyChiselKey;   // V
    public static KeyMapping cancelChiselKey;  // X


    public static void registerKeyBindings() {

        // C - Toggle Selection Mode
        chiselKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.tutorialmod.chisel",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_C,
                        CHISEL_CATEGORY
                )
        );


        // R - Cycle Preview
        cycleChiselKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.tutorialmod.cycle_chisel",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_R,
                        CHISEL_CATEGORY
                )
        );


        // V - Confirm Chisel
        applyChiselKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.tutorialmod.apply_chisel",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_V,
                        CHISEL_CATEGORY
                )
        );


        // X - Cancel Preview
        cancelChiselKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                    "key.tutorialmod.cancel_chisel",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_X,
                    CHISEL_CATEGORY
                )
        );







    }
}
