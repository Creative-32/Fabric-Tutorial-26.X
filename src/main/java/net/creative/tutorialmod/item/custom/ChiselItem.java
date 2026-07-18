package net.creative.tutorialmod.item.custom;

import net.creative.tutorialmod.block.ModBlocks;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.stat.ModStats;
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

import net.creative.tutorialmod.TutorialModClient;

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




    // Clears Coordinates When Right-Clicked in Air
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(player.isCrouching()) {
            player.getMainHandItem().remove(ModDataComponents.COORDINATES);
            return InteractionResult.SUCCESS;
        }
        return super.use(level, player, hand);
    }




    // Changes Blocks Based on Block Relationship in Map
    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();

        if(level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ItemStack stack = context.getItemInHand();
        BlockPos clickedPos = context.getClickedPos();

        // Selection Mode
        if(TutorialModClient.chiselMode) {

            List<BlockPos> positions = stack.get(ModDataComponents.COORDINATES);

            if(positions == null) {
                positions = new ArrayList<>();
            }
            else {
                positions = new ArrayList<>(positions);
            }

            // Removing Block from List on Second Click
            if(positions.contains(clickedPos)) {

                positions.remove(clickedPos);

                context.getPlayer().sendSystemMessage(
                        Component.literal(
                                "2: Removed " + clickedPos
                        )
                );
            }
            else {

                positions.add(clickedPos);
                // Adding Block from List on First Click
                context.getPlayer().sendSystemMessage(
                        Component.literal(
                                "1: Added " + clickedPos
                        )
                );
            }

            stack.set(ModDataComponents.COORDINATES, positions);

            return InteractionResult.SUCCESS;
        }


        // Normal Chisel Mode
        Block clickedBlock = level.getBlockState(clickedPos).getBlock();


        if(CHISEL_MAP.containsKey(clickedBlock)) {

            level.setBlockAndUpdate( clickedPos, CHISEL_MAP.get(clickedBlock).defaultBlockState());

            stack.hurtAndBreak(1, context.getPlayer(), context.getHand());

            context.getPlayer().awardStat(ModStats.CHISEL_USED_STAT,1);}

        return InteractionResult.SUCCESS;
    }


    public static void chiselSelectedBlocks(Level level, Player player, ItemStack stack) {

        List<BlockPos> positions = stack.get(ModDataComponents.COORDINATES);

        if(positions == null || positions.isEmpty()) {
            player.sendSystemMessage(
                    Component.literal("No blocks selected")
            );
            return;
        }

//        player.sendSystemMessage(
//                Component.literal(
//                        "Blocks: 3" + positions.size()
//                )
//        );


        for(BlockPos pos : positions) {

            Block block = level.getBlockState(pos).getBlock();

//           player.sendSystemMessage(Component.literal("Checking 4" + pos));

            if(CHISEL_MAP.containsKey(block)) {
                level.setBlockAndUpdate(
                        pos,
                        CHISEL_MAP.get(block)
                                .defaultBlockState()
                );

                player.sendSystemMessage(
                        Component.literal(
                                "5: Changed " + pos
                        )
                );
            }
        }

        stack.remove(ModDataComponents.COORDINATES);
    }









//---------------------------------------------          Tooltip          ---------------------------------------------

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

        // Tooltip
        // New Coordinate Display
        if(itemStack.has(ModDataComponents.COORDINATES)) {

            List<BlockPos> positions =
                    itemStack.get(ModDataComponents.COORDINATES);


            builder.accept(
                    Component.literal(
                            "Selected Blocks: " + positions.size()
                    )
            );


            for(BlockPos pos : positions) {

                builder.accept(
                        Component.literal(
                                 " X: " + pos.getX()
                                    + " Y: " + pos.getY()
                                    + " Z: " + pos.getZ()
                        )
                );
            }
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}