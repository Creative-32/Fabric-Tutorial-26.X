package net.creative.tutorialmod.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.creative.tutorialmod.TutorialModClient;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.item.custom.ChiselItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Inject(method = "submitBlockOutline", at = @At("HEAD"), cancellable = true)
    private void onSubmitBlockOutline(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            LevelRenderState levelRenderState,
            CallbackInfo ci
    ) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null)
            return;

        // Only while in chisel mode
        if (!TutorialModClient.chiselMode)
            return;

        ItemStack stack = client.player.getMainHandItem();

        // Only while holding the chisel
        if (!(stack.getItem() instanceof ChiselItem))
            return;

        // Block Minecraft is about to outline
        BlockOutlineRenderState outlineState = levelRenderState.blockOutlineRenderState;

        if (outlineState == null)
            return;

        BlockPos lookedAt = outlineState.pos();

        // Selected blocks stored in the chisel
        List<BlockPos> positions = stack.get(ModDataComponents.CHISEL_COORDINATES);

        if (positions == null)
            return;

        // Hide vanilla outline only for selected blocks
        if (positions.contains(lookedAt)) {
            ci.cancel();
        }
    }
}
