package net.creative.tutorialmod.item.custom;

import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.data.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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

import java.util.ArrayList;
import java.util.List;

// Click Item and the CRTL+H to See all Items and how they Work
public class ChiselItem extends Item {

    // Type "override" to See More Options


//--------------------------------------------       Click On Air       ------------------------------------------------
    // Clears Coordinates When Right-Clicked in Air
@Override
public InteractionResult use(Level level, Player player, InteractionHand hand) {

    ItemStack item = player.getItemInHand(hand);

    if(!level.isClientSide()) {

        List<BlockPos> positions = item.getOrDefault(
                ModDataComponents.COORDINATES,
                new ArrayList<>()
        );

        // Ctrl + Right Click Air = Clear Selection
        if(player.isCrouching()) {

            item.remove(ModDataComponents.COORDINATES);

            return InteractionResult.SUCCESS;
        }


        // Right Click Air = Change Blocks
        for(BlockPos pos : positions) {

            Block block = level.getBlockState(pos).getBlock();

            if(CHISEL_MAP.containsKey(block)) {
                level.setBlockAndUpdate(pos,
                        CHISEL_MAP.get(block).defaultBlockState());
            }
        }

        if(!positions.isEmpty()) {
            item.hurtAndBreak(1, player, hand);
        }

        return InteractionResult.SUCCESS;
    }

    return InteractionResult.PASS;
}


//--------------------------------------------      Click On Block      ------------------------------------------------
    // Changes Blocks Based on Block Relationship in Map
    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();

        if (!level.isClientSide()) {

            ItemStack item = context.getItemInHand();

            List<BlockPos> positions = new ArrayList<>(
                    item.getOrDefault(
                            ModDataComponents.COORDINATES,
                            new ArrayList<>()
                    )
            );

            BlockPos pos = context.getClickedPos();

            if (positions.contains(pos)) {
                positions.remove(pos);
            }
            else if (positions.size() < 100) {
                positions.add(pos);
            }

            // Update component correctly
            if (positions.isEmpty()) {
                item.remove(ModDataComponents.COORDINATES);
            }
            else {
                item.set(ModDataComponents.COORDINATES, positions);
            }
        }

        return InteractionResult.SUCCESS;
    }


//-------------------------------------------------      Map      -----------------------------------------------------
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


//-----------------------------------------------      Tooltip      ---------------------------------------------------
    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel"));
        }

        // Old Coordinate Display
        //if(itemStack.has(ModDataComponents.COORDINATES)) {
        //    builder.accept(Component.literal("Last Block chiseled at X: " + itemStack.get(ModDataComponents.COORDINATES.)));
        //}

        // New Coordinate Display
        if(itemStack.has(ModDataComponents.COORDINATES)) {
            List<BlockPos> positions = itemStack.get(ModDataComponents.COORDINATES);

            builder.accept(Component.literal("Selected Blocks: " + positions.size()));
            for(BlockPos pos : positions) {
                builder.accept(Component.literal(
                        "X: " + pos.getX() +
                                " Y: " + pos.getY() +
                                " Z: " + pos.getZ()
                ));
            }
            // Puts Coordinates on the Line Below It
            //builder.accept(Component.literal("X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}