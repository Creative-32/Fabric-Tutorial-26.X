package net.creative.tutorialmod.item.custom;

import net.creative.tutorialmod.data.ChiselPlayerData;
import net.creative.tutorialmod.data.ChiselPreviewData;
import net.creative.tutorialmod.data.ModDataComponents;
import net.creative.tutorialmod.stat.ModStats;
import net.creative.tutorialmod.network.SyncChiselSelectionPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.block.state.BlockState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;



public class ChiselItem extends Item {

    public ChiselItem(Properties properties) {
        super(properties);
    }

//-----------------------------------------          Right Click Air          -----------------------------------------
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if(player.isCrouching()){

            player.getMainHandItem().remove(ModDataComponents.CHISEL_COORDINATES);
            ChiselPreviewData.clear();
            player.sendSystemMessage(
                    Component.literal(
                            "Selection cleared"
                    )
            );

            return InteractionResult.SUCCESS;
        }

        return super.use(level,player,hand);
    }

//----------------------------------------          Right Click Block          ----------------------------------------
    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();

        if(level.isClientSide())
            return InteractionResult.SUCCESS;

        Player player = context.getPlayer();

        ItemStack stack = context.getItemInHand();

        BlockPos pos = context.getClickedPos();


//------------------------------------------          Selection Mode          ------------------------------------------

        if(player instanceof ServerPlayer serverPlayer && ChiselPlayerData.isChiselMode(serverPlayer)) {

            List<BlockPos> list = stack.get(ModDataComponents.CHISEL_COORDINATES);

            if(list == null) {
                list = new ArrayList<>();
            } else {
                list = new ArrayList<>(list);
            }

            if(list.contains(pos)) { // Need to make it so if they change block then remove it, it calls original block back
                list.remove(pos);    // Do a Check to Add Original Block back
                player.sendSystemMessage(
                        Component.literal(
                                "Removed Block"
                        )
                );
            } else if(list.size() >= 16) {
                player.sendSystemMessage(
                        Component.literal(
                                "Max Block Amount Reached"
                        )
                );
            } else {
                list.add(pos);
                player.sendSystemMessage(
                        Component.literal(
                                "Added Block"
                        )
                );
            }

            stack.set(ModDataComponents.CHISEL_COORDINATES,list);

            ServerPlayNetworking.send(
                    serverPlayer,
                    new SyncChiselSelectionPayload(list)
            );

            return InteractionResult.SUCCESS;
        }


//-------------------------------------------          Normal Mode          -------------------------------------------

        Block block = level.getBlockState(pos).getBlock();

        if(CHISEL_MAP.containsKey(block)) {
            level.setBlockAndUpdate(
                    pos,
                    CHISEL_MAP.get(block)
                            .defaultBlockState()
            );

            stack.hurtAndBreak(1, player, context.getHand());

            player.awardStat(
                    ModStats.CHISEL_USED_STAT,
                    1
            );
        }

        return InteractionResult.SUCCESS;
    }


//-------------------------------------------          Preview Mode          -------------------------------------------

    public static void cyclePreview(Level level, Player player, ItemStack stack) {

        List<BlockPos> positions = stack.get(ModDataComponents.CHISEL_COORDINATES);

        if(positions == null || positions.isEmpty()){
            player.sendSystemMessage(
                    Component.literal(
                            "No blocks selected"
                    )
            );
            return;
        }


        for(BlockPos pos : positions) { BlockState old = level.getBlockState(pos);

            // Save original once
            ChiselPreviewData.save(pos, old);

            Block current = old.getBlock();

            if(CHISEL_MAP.containsKey(current)){
                Block next = CHISEL_MAP.get(current);
                level.setBlockAndUpdate(pos, next.defaultBlockState());
            }
        }
    }


//----------------------------------------          Confirmation Mode          ----------------------------------------
    public static void confirmPreview(Level level, Player player, ItemStack stack){

        List<BlockPos> positions = stack.get(ModDataComponents.CHISEL_COORDINATES);

        if(positions == null)
            return;

        stack.hurtAndBreak(1, player, InteractionHand.MAIN_HAND);

        ChiselPreviewData.clear();

        stack.remove(ModDataComponents.CHISEL_COORDINATES);

        player.sendSystemMessage(
                Component.literal(
                        "Chisel Confirmed"
                )
        );
    }


//----------------------------------------          Cancellation Mode          ----------------------------------------
    public static void cancelPreview(Level level,Player player,ItemStack stack) {

        List<BlockPos> positions = stack.get(ModDataComponents.CHISEL_COORDINATES);

        if(positions != null) {
            for(BlockPos pos : positions) {

                BlockState original = ChiselPreviewData.get(pos);

                if(original != null){
                    level.setBlockAndUpdate(pos, original);
                }
            }
        }

        ChiselPreviewData.clear();

        stack.remove(ModDataComponents.CHISEL_COORDINATES);

        player.sendSystemMessage(
                Component.literal(
                        "Preview Cancelled"
                )
        );
    }


//--------------------------------------------          Block Map          --------------------------------------------
    private static final Map<Block,Block> CHISEL_MAP = new HashMap<>();

    // There has to be a Better way to Cycle through Block Families
    //      * Need a way to Auto Get Similar Blocks
    static{

        CHISEL_MAP.put(
                Blocks.GRASS_BLOCK,
                Blocks.DIRT
        );


        CHISEL_MAP.put(
                Blocks.DIRT,
                Blocks.DIRT_PATH
        );


        CHISEL_MAP.put(
                Blocks.DIRT_PATH,
                Blocks.GRASS_BLOCK
        );


        CHISEL_MAP.put(
                Blocks.STONE,
                Blocks.COBBLESTONE
        );


        CHISEL_MAP.put(
                Blocks.COBBLESTONE,
                Blocks.STONE
        );
    }


//---------------------------------------------          Tooltip          ---------------------------------------------
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag flag) {

        if(stack.has(ModDataComponents.CHISEL_COORDINATES)){

            List<BlockPos> list = stack.get(ModDataComponents.CHISEL_COORDINATES);

            builder.accept(
                    Component.literal(
                            "Selected Blocks: "
                                    + list.size()
                    )
            );
        }

        super.appendHoverText(stack, context, display, builder, flag);
    }









}