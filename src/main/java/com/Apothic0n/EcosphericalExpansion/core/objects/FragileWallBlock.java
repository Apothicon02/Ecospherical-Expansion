package com.Apothic0n.EcosphericalExpansion.core.objects;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class FragileWallBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final EnumProperty<WallSide> EAST_WALL = BlockStateProperties.EAST_WALL;
    public static final EnumProperty<WallSide> NORTH_WALL = BlockStateProperties.NORTH_WALL;
    public static final EnumProperty<WallSide> SOUTH_WALL = BlockStateProperties.SOUTH_WALL;
    public static final EnumProperty<WallSide> WEST_WALL = BlockStateProperties.WEST_WALL;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final Map<BlockState, VoxelShape> shapeByIndex;
    private final Map<BlockState, VoxelShape> collisionShapeByIndex;
    private static final int WALL_WIDTH = 3;
    private static final int WALL_HEIGHT = 14;
    private static final int POST_WIDTH = 4;
    private static final int POST_COVER_WIDTH = 1;
    private static final int WALL_COVER_START = 7;
    private static final int WALL_COVER_END = 9;
    private static final VoxelShape POST_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape NORTH_TEST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape SOUTH_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_TEST = Block.box(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape EAST_TEST = Block.box(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);

    public FragileWallBlock(Properties p_57964_) {
        super(p_57964_);
        this.registerDefaultState(this.stateDefinition.any().setValue(UP, Boolean.valueOf(true)).setValue(NORTH_WALL, WallSide.NONE).setValue(EAST_WALL, WallSide.NONE).setValue(SOUTH_WALL, WallSide.NONE).setValue(WEST_WALL, WallSide.NONE).setValue(WATERLOGGED, Boolean.valueOf(false)));
        this.shapeByIndex = this.makeShapes(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
        this.collisionShapeByIndex = this.makeShapes(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
    }

    private static VoxelShape applyWallShape(VoxelShape p_58034_, WallSide p_58035_, VoxelShape p_58036_, VoxelShape p_58037_) {
        if (p_58035_ == WallSide.TALL) {
            return Shapes.or(p_58034_, p_58037_);
        } else {
            return p_58035_ == WallSide.LOW ? Shapes.or(p_58034_, p_58036_) : p_58034_;
        }
    }

    private Map<BlockState, VoxelShape> makeShapes(float p_57966_, float p_57967_, float p_57968_, float p_57969_, float p_57970_, float p_57971_) {
        float f = 8.0F - p_57966_;
        float f1 = 8.0F + p_57966_;
        float f2 = 8.0F - p_57967_;
        float f3 = 8.0F + p_57967_;
        VoxelShape voxelshape = Block.box((double)f, 0.0D, (double)f, (double)f1, (double)p_57968_, (double)f1);
        VoxelShape voxelshape1 = Block.box((double)f2, (double)p_57969_, 0.0D, (double)f3, (double)p_57970_, (double)f3);
        VoxelShape voxelshape2 = Block.box((double)f2, (double)p_57969_, (double)f2, (double)f3, (double)p_57970_, 16.0D);
        VoxelShape voxelshape3 = Block.box(0.0D, (double)p_57969_, (double)f2, (double)f3, (double)p_57970_, (double)f3);
        VoxelShape voxelshape4 = Block.box((double)f2, (double)p_57969_, (double)f2, 16.0D, (double)p_57970_, (double)f3);
        VoxelShape voxelshape5 = Block.box((double)f2, (double)p_57969_, 0.0D, (double)f3, (double)p_57971_, (double)f3);
        VoxelShape voxelshape6 = Block.box((double)f2, (double)p_57969_, (double)f2, (double)f3, (double)p_57971_, 16.0D);
        VoxelShape voxelshape7 = Block.box(0.0D, (double)p_57969_, (double)f2, (double)f3, (double)p_57971_, (double)f3);
        VoxelShape voxelshape8 = Block.box((double)f2, (double)p_57969_, (double)f2, 16.0D, (double)p_57971_, (double)f3);
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

        for(Boolean obool : UP.getPossibleValues()) {
            for(WallSide wallside : EAST_WALL.getPossibleValues()) {
                for(WallSide wallside1 : NORTH_WALL.getPossibleValues()) {
                    for(WallSide wallside2 : WEST_WALL.getPossibleValues()) {
                        for(WallSide wallside3 : SOUTH_WALL.getPossibleValues()) {
                            VoxelShape voxelshape9 = Shapes.empty();
                            voxelshape9 = applyWallShape(voxelshape9, wallside, voxelshape4, voxelshape8);
                            voxelshape9 = applyWallShape(voxelshape9, wallside2, voxelshape3, voxelshape7);
                            voxelshape9 = applyWallShape(voxelshape9, wallside1, voxelshape1, voxelshape5);
                            voxelshape9 = applyWallShape(voxelshape9, wallside3, voxelshape2, voxelshape6);
                            if (obool) {
                                voxelshape9 = Shapes.or(voxelshape9, voxelshape);
                            }

                            BlockState blockState = this.defaultBlockState().setValue(UP, obool).setValue(EAST_WALL, wallside).setValue(WEST_WALL, wallside2).setValue(NORTH_WALL, wallside1).setValue(SOUTH_WALL, wallside3);
                            builder.put(blockState.setValue(WATERLOGGED, Boolean.valueOf(false)), voxelshape9);
                            builder.put(blockState.setValue(WATERLOGGED, Boolean.valueOf(true)), voxelshape9);
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    public VoxelShape getShape(BlockState p_58050_, BlockGetter p_58051_, BlockPos p_58052_, CollisionContext p_58053_) {
        return this.shapeByIndex.get(p_58050_);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_56625_, BlockGetter p_56626_, BlockPos p_56627_, CollisionContext p_56628_) {
        return Shapes.empty();
    }

    public boolean isPathfindable(BlockState p_57996_, BlockGetter p_57997_, BlockPos p_57998_, PathComputationType p_57999_) {
        return false;
    }

    private boolean connectsTo(BlockState p_58021_, boolean p_58022_, Direction p_58023_) {
        Block block = p_58021_.getBlock();
        boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(p_58021_, p_58023_);
        return p_58021_.is(BlockTags.WALLS) || !isExceptionForConnection(p_58021_) && p_58022_ || block instanceof IronBarsBlock || flag;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelAccessor = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos blockPos1 = blockPos.north();
        BlockPos blockPos2 = blockPos.east();
        BlockPos blockPos3 = blockPos.south();
        BlockPos blockPos4 = blockPos.west();
        BlockPos blockPos5 = blockPos.above();
        BlockState blockState = levelAccessor.getBlockState(blockPos1);
        BlockState blockState1 = levelAccessor.getBlockState(blockPos2);
        BlockState blockState2 = levelAccessor.getBlockState(blockPos3);
        BlockState blockState3 = levelAccessor.getBlockState(blockPos4);
        BlockState blockState4 = levelAccessor.getBlockState(blockPos5);
        boolean flag = this.connectsTo(blockState, blockState.isFaceSturdy(levelAccessor, blockPos1, Direction.SOUTH), Direction.SOUTH);
        boolean flag1 = this.connectsTo(blockState1, blockState1.isFaceSturdy(levelAccessor, blockPos2, Direction.WEST), Direction.WEST);
        boolean flag2 = this.connectsTo(blockState2, blockState2.isFaceSturdy(levelAccessor, blockPos3, Direction.NORTH), Direction.NORTH);
        boolean flag3 = this.connectsTo(blockState3, blockState3.isFaceSturdy(levelAccessor, blockPos4, Direction.EAST), Direction.EAST);
        BlockState blockState5 = this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        Boolean supported = false;
        for (int i = 1; i < 48; i++) {
            BlockState block = levelAccessor.getBlockState(blockPos.above(i));
            if (!(block.getBlock() instanceof FragileWallBlock)) {
                supported = block.isSolid();
                break;
            }
        }
        if (!supported) {
            for (int i = 1; i < 48; i++) {
                BlockState block = levelAccessor.getBlockState(blockPos.below(i));
                if (!(block.getBlock() instanceof FragileWallBlock)) {
                    supported = block.isSolid();
                    break;
                }
            }
        }
        if (!supported) {
            return breakBlock(levelAccessor, blockPos, blockState, null);
        }
        return this.updateShape(levelAccessor, blockState5, blockPos5, blockState4, flag, flag1, flag2, flag3);
    }

    public BlockState updateShape(BlockState blockState, Direction p_58015_, BlockState p_58016_, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos p_58019_) {
        RandomSource randomSource = levelAccessor.getRandom();
        Boolean supported = false;
        for (int i = 1; i < 48; i++) {
            BlockState block = levelAccessor.getBlockState(blockPos.above(i));
            if (!(block.getBlock() instanceof FragileWallBlock)) {
                supported = block.isSolid();
                break;
            }
        }
        if (!supported) {
            for (int i = 1; i < 48; i++) {
                BlockState block = levelAccessor.getBlockState(blockPos.below(i));
                if (!(block.getBlock() instanceof FragileWallBlock)) {
                    supported = block.isSolid();
                    break;
                }
            }
        }
        if (!supported) {
            return breakBlock(levelAccessor, blockPos, blockState, randomSource);
        }


        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if (p_58015_ == Direction.DOWN) {
            return super.updateShape(blockState, p_58015_, p_58016_, levelAccessor, blockPos, p_58019_);
        } else {
            return p_58015_ == Direction.UP ? this.topUpdate(levelAccessor, blockState, p_58019_, p_58016_) : this.sideUpdate(levelAccessor, blockPos, blockState, p_58019_, p_58016_, p_58015_);
        }
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        if (randomSource.nextInt(10) == 0) {
            BlockPos blockpos = blockPos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                String name = level.getBlockState(blockPos).getBlock().getName().getContents().toString();
                if (name.contains("dark_oak")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.DARK_OAK_LEAVES.get());
                } else if (name.contains("oak")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.OAK_LEAVES.get());
                } else if (name.contains("birch")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.BIRCH_LEAVES.get());
                } else if (name.contains("spruce")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.SPRUCE_LEAVES.get());
                } else if (name.contains("jungle")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.JUNGLE_LEAVES.get());
                } else if (name.contains("acacia")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.ACACIA_LEAVES.get());
                } else if (name.contains("mangrove")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.MANGROVE_LEAVES.get());
                } else if (name.contains("flowering_azalea")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.FLOWERING_AZALEA_LEAVES.get());
                } else if (name.contains("azalea")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, EcoParticleTypes.AZALEA_LEAVES.get());
                } else if (name.contains("cherry")) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, ParticleTypes.CHERRY_LEAVES);
                }
            }
        }
    }
    
    private BlockState breakBlock(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, @Nullable RandomSource randomSource) {
        levelAccessor.setBlock(blockPos, Blocks.AIR.defaultBlockState(), UPDATE_ALL);
        if (randomSource != null) {
            levelAccessor.addParticle(ParticleTypes.RAIN, (double) blockPos.getX() + 0.5D + randomSource.nextDouble() / 4.0D * (double) (randomSource.nextBoolean() ? 1 : -1), (double) blockPos.getY() + 0.4D, (double) blockPos.getZ() - 0.5D - randomSource.nextDouble() / 4.0D * (double) (randomSource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
        }
        return Blocks.AIR.defaultBlockState();
    }

    private static boolean isConnected(BlockState p_58011_, Property<WallSide> p_58012_) {
        return p_58011_.getValue(p_58012_) != WallSide.NONE;
    }

    private static boolean isCovered(VoxelShape p_58039_, VoxelShape p_58040_) {
        return !Shapes.joinIsNotEmpty(p_58040_, p_58039_, BooleanOp.ONLY_FIRST);
    }

    private BlockState topUpdate(LevelReader p_57975_, BlockState p_57976_, BlockPos p_57977_, BlockState p_57978_) {
        boolean flag = isConnected(p_57976_, NORTH_WALL);
        boolean flag1 = isConnected(p_57976_, EAST_WALL);
        boolean flag2 = isConnected(p_57976_, SOUTH_WALL);
        boolean flag3 = isConnected(p_57976_, WEST_WALL);
        return this.updateShape(p_57975_, p_57976_, p_57977_, p_57978_, flag, flag1, flag2, flag3);
    }

    private BlockState sideUpdate(LevelReader p_57989_, BlockPos p_57990_, BlockState p_57991_, BlockPos p_57992_, BlockState p_57993_, Direction p_57994_) {
        Direction direction = p_57994_.getOpposite();
        boolean flag = p_57994_ == Direction.NORTH ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, NORTH_WALL);
        boolean flag1 = p_57994_ == Direction.EAST ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, EAST_WALL);
        boolean flag2 = p_57994_ == Direction.SOUTH ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, SOUTH_WALL);
        boolean flag3 = p_57994_ == Direction.WEST ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, WEST_WALL);
        BlockPos blockPos = p_57990_.above();
        BlockState blockState = p_57989_.getBlockState(blockPos);
        return this.updateShape(p_57989_, p_57991_, blockPos, blockState, flag, flag1, flag2, flag3);
    }

    private BlockState updateShape(LevelReader p_57980_, BlockState p_57981_, BlockPos p_57982_, BlockState p_57983_, boolean p_57984_, boolean p_57985_, boolean p_57986_, boolean p_57987_) {
        VoxelShape voxelshape = p_57983_.getCollisionShape(p_57980_, p_57982_).getFaceShape(Direction.DOWN);
        BlockState blockState = this.updateSides(p_57981_, p_57984_, p_57985_, p_57986_, p_57987_, voxelshape);
        return blockState.setValue(UP, Boolean.valueOf(this.shouldRaisePost(blockState, p_57983_, voxelshape)));
    }

    private boolean shouldRaisePost(BlockState p_58007_, BlockState p_58008_, VoxelShape p_58009_) {
        boolean flag = p_58008_.getBlock() instanceof WallBlock && p_58008_.getValue(UP);
        if (flag) {
            return true;
        } else {
            WallSide wallside = p_58007_.getValue(NORTH_WALL);
            WallSide wallside1 = p_58007_.getValue(SOUTH_WALL);
            WallSide wallside2 = p_58007_.getValue(EAST_WALL);
            WallSide wallside3 = p_58007_.getValue(WEST_WALL);
            boolean flag1 = wallside1 == WallSide.NONE;
            boolean flag2 = wallside3 == WallSide.NONE;
            boolean flag3 = wallside2 == WallSide.NONE;
            boolean flag4 = wallside == WallSide.NONE;
            boolean flag5 = flag4 && flag1 && flag2 && flag3 || flag4 != flag1 || flag2 != flag3;
            if (flag5) {
                return true;
            } else {
                boolean flag6 = wallside == WallSide.TALL && wallside1 == WallSide.TALL || wallside2 == WallSide.TALL && wallside3 == WallSide.TALL;
                if (flag6) {
                    return false;
                } else {
                    return p_58008_.is(BlockTags.WALL_POST_OVERRIDE) || isCovered(p_58009_, POST_TEST);
                }
            }
        }
    }

    private BlockState updateSides(BlockState p_58025_, boolean p_58026_, boolean p_58027_, boolean p_58028_, boolean p_58029_, VoxelShape p_58030_) {
        return p_58025_.setValue(NORTH_WALL, this.makeWallState(p_58026_, p_58030_, NORTH_TEST)).setValue(EAST_WALL, this.makeWallState(p_58027_, p_58030_, EAST_TEST)).setValue(SOUTH_WALL, this.makeWallState(p_58028_, p_58030_, SOUTH_TEST)).setValue(WEST_WALL, this.makeWallState(p_58029_, p_58030_, WEST_TEST));
    }

    private WallSide makeWallState(boolean p_58042_, VoxelShape p_58043_, VoxelShape p_58044_) {
        if (p_58042_) {
            return isCovered(p_58043_, p_58044_) ? WallSide.TALL : WallSide.LOW;
        } else {
            return WallSide.NONE;
        }
    }

    public FluidState getFluidState(BlockState p_58060_) {
        return p_58060_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_58060_);
    }

    public boolean propagatesSkylightDown(BlockState p_58046_, BlockGetter p_58047_, BlockPos p_58048_) {
        return !p_58046_.getValue(WATERLOGGED);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_58032_) {
        p_58032_.add(UP, NORTH_WALL, EAST_WALL, WEST_WALL, SOUTH_WALL, WATERLOGGED);
    }

    public BlockState rotate(BlockState p_58004_, Rotation p_58005_) {
        switch (p_58005_) {
            case CLOCKWISE_180:
                return p_58004_.setValue(NORTH_WALL, p_58004_.getValue(SOUTH_WALL)).setValue(EAST_WALL, p_58004_.getValue(WEST_WALL)).setValue(SOUTH_WALL, p_58004_.getValue(NORTH_WALL)).setValue(WEST_WALL, p_58004_.getValue(EAST_WALL));
            case COUNTERCLOCKWISE_90:
                return p_58004_.setValue(NORTH_WALL, p_58004_.getValue(EAST_WALL)).setValue(EAST_WALL, p_58004_.getValue(SOUTH_WALL)).setValue(SOUTH_WALL, p_58004_.getValue(WEST_WALL)).setValue(WEST_WALL, p_58004_.getValue(NORTH_WALL));
            case CLOCKWISE_90:
                return p_58004_.setValue(NORTH_WALL, p_58004_.getValue(WEST_WALL)).setValue(EAST_WALL, p_58004_.getValue(NORTH_WALL)).setValue(SOUTH_WALL, p_58004_.getValue(EAST_WALL)).setValue(WEST_WALL, p_58004_.getValue(SOUTH_WALL));
            default:
                return p_58004_;
        }
    }

    public BlockState mirror(BlockState p_58001_, Mirror p_58002_) {
        switch (p_58002_) {
            case LEFT_RIGHT:
                return p_58001_.setValue(NORTH_WALL, p_58001_.getValue(SOUTH_WALL)).setValue(SOUTH_WALL, p_58001_.getValue(NORTH_WALL));
            case FRONT_BACK:
                return p_58001_.setValue(EAST_WALL, p_58001_.getValue(WEST_WALL)).setValue(WEST_WALL, p_58001_.getValue(EAST_WALL));
            default:
                return super.mirror(p_58001_, p_58002_);
        }
    }
}