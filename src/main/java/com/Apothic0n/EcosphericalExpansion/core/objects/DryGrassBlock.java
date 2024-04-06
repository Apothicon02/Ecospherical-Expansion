package com.Apothic0n.EcosphericalExpansion.core.objects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DryGrassBlock extends GrowingPlantHeadBlock {
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape TOP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    public DryGrassBlock(Properties p_154864_) {
        super(p_154864_, Direction.UP, SHAPE, false, 0.1D);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HALF, Half.TOP));
    }

    public boolean propagatesSkylightDown(BlockState p_51039_, BlockGetter p_51040_, BlockPos p_51041_) {
        return p_51039_.getFluidState().isEmpty();
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource p_222649_) {
        return NetherVines.getBlocksToGrowWhenBonemealed(p_222649_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_53961_) {
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return makeShape(context.getLevel(), context.getClickedPos());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos p_56392_, CollisionContext p_56393_) {
        if (blockState.getValue(HALF).equals(Half.BOTTOM)) {
            return BOTTOM_AABB;
        }
        return TOP_AABB;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return makeShape(levelAccessor, blockPos);
    }

    private BlockState makeShape(LevelAccessor levelAccessor, BlockPos blockPos) {
        BlockState belowState = levelAccessor.getBlockState(blockPos.below());
        if (!belowState.is(BlockTags.DIRT) && !belowState.is(EcoBlocks.DRY_GRASS.get())) {
            return Blocks.AIR.defaultBlockState();
        } else if (levelAccessor.getBlockState(blockPos.above()).is(EcoBlocks.DRY_GRASS.get())) {
            return this.defaultBlockState().setValue(HALF, Half.BOTTOM);
        }
        return this.defaultBlockState().setValue(HALF, Half.TOP);
    }

    protected Block getBodyBlock() {
        return EcoBlocks.DRY_GRASS.get();
    }

    protected boolean canGrowInto(BlockState p_154869_) {
        return NetherVines.isValidGrowthState(p_154869_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HALF, AGE);
    }
}
