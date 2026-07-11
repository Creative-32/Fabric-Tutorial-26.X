package net.creative.tutorialmod.item.custom;

import net.creative.tutorialmod.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

// Click Item and the CRTL+H to See all Items and how they Work
public class ChiselItem extends Item {

    // Type "override" to See More Options

    // Block A Turns Into Block B
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    // Block A, Block B
                    Blocks.GRASS_BLOCK, Blocks.DIRT,
                    Blocks.DIRT, Blocks.GRASS_BLOCK,

                    Blocks.STONE, Blocks.COBBLESTONE,
                    Blocks.COBBLESTONE, Blocks.STONE,

                    ModBlocks.FLUORITE_BLOCK, ModBlocks.RAW_FLUORITE_BLOCK,
                    ModBlocks.RAW_FLUORITE_BLOCK, ModBlocks.FLUORITE_BLOCK,

                    ModBlocks.FLUORITE_NETHER_ORE, Blocks.NETHER_QUARTZ_ORE,
                    Blocks.NETHER_QUARTZ_ORE, ModBlocks.FLUORITE_NETHER_ORE,

                    ModBlocks.FLUORITE_END_ORE, Blocks.END_STONE,
                    Blocks.END_STONE, ModBlocks.FLUORITE_END_ORE

            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    // use - Right click air
    // useOn - Right click Block
    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
                                                    // "!" Negates/Says No AKA Changes Occur on Server
        if(CHISEL_MAP.containsKey(clickedBlock) && !level.isClientSide()) {

            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
        }

        // Interaction Animation on Success
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        // ToolTip when Shift down
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel"));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}