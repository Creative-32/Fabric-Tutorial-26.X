package net.creative.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PedestalBlock extends Block {
    // Shape of Block
    //    * Add Extra Cube in Block Bench and then Stretch it out to Cover your Block by Adjust Position and Size
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    // Overriding the Origianl Shape and Returning Custom Shape Defined Above
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}