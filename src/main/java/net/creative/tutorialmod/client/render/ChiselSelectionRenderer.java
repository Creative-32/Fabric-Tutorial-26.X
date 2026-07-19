package net.creative.tutorialmod.client.render;

import net.creative.tutorialmod.TutorialModClient;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.item.custom.ChiselItem;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ChiselSelectionRenderer {

    public static void render(LevelRenderContext context) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.level == null)
            return;

        if (!TutorialModClient.chiselMode)
            return;

        ItemStack stack = client.player.getMainHandItem();

        if (!(stack.getItem() instanceof ChiselItem))
            return;

        List<BlockPos> positions = stack.get(ModDataComponents.COORDINATES);

        if (positions == null)
            return;


        for (BlockPos pos : positions) {
            /*
             * 0xFFFFFFFF:
             * FF = opacity   A->F
             * FF = red
             * FF = green
             * FF = blue
             *
             * 2.0F = line thickness
             */
            Gizmos.cuboid(
                    new AABB(pos).inflate(0.003F),
                    GizmoStyle.stroke(
                            0xcc55FFFF,
                            2.0F
                    )
            );
        }
    }
}