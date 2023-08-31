package com.Apothic0n.api.biome.features.types;

import com.Apothic0n.api.biome.features.configurations.FloodConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;

import static net.minecraft.world.level.block.Block.*;

public class FloodFeature extends Feature<FloodConfiguration> {
    public FloodFeature(Codec<FloodConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<FloodConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        ChunkPos chunkOrigin = new ChunkPos(pContext.origin());
        BlockPos origin = new BlockPos(chunkOrigin.getMiddleBlockX(), pContext.origin().getY(), chunkOrigin.getMiddleBlockZ());
        RandomSource random = pContext.random();
        FloodConfiguration config = pContext.config();
        BlockState state = config.material.getState(random, origin);
        BlockState frozenState = config.frozenMaterial.getState(random, origin);
        Integer elevation = config.getElevation().sample(random);
        Boolean frozen = config.frozen;
        BlockState barrierState = Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState();
        if (frozen) {barrierState = Blocks.BLUE_ICE.defaultBlockState();}
        for (int x = origin.getX()-16; x < origin.getX()+16; ++x) {
            for (int z = origin.getZ()-16; z < origin.getZ()+16; ++z) {
                for (int y = elevation; y > worldgenlevel.getMinBuildHeight() + 1; --y) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if ((worldgenlevel.getBlockState(pos).is(BlockTags.DIRT) || worldgenlevel.getBlockState(pos).is(BlockTags.SAND) || worldgenlevel.getBlockState(pos).is(Blocks.SNOW_BLOCK) || worldgenlevel.getBlockState(pos).is(Blocks.POWDER_SNOW)) && !worldgenlevel.getFluidState(pos).is(Fluids.LAVA) && worldgenlevel.getBlockState(pos) != state && worldgenlevel.getBlockState(pos) != Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState() && worldgenlevel.getBiome(pos).is(BiomeTags.IS_RIVER) && !worldgenlevel.getBiome(pos).is(Biomes.RIVER)) {
                        worldgenlevel.setBlock(pos, state, UPDATE_NONE);
                        if (worldgenlevel.getBlockState(pos.above()).isAir() && frozen) {
                            worldgenlevel.setBlock(pos, frozenState, UPDATE_NONE);
                        }
                        if (worldgenlevel.getBlockState(pos.below()).isAir() || worldgenlevel.getFluidState(pos.below()).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(pos.below(), barrierState, UPDATE_NONE);
                        }
                        placeBarrier(worldgenlevel, pos.north(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.east(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.south(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.west(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.north().east(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.north().west(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.south().east(), state, frozenState, barrierState, frozen);
                        placeBarrier(worldgenlevel, pos.south().west(), state, frozenState, barrierState, frozen);
                    }
                }
            }
        }
        return true;
    }

    private void placeBarrier(WorldGenLevel worldgenlevel, BlockPos pos, BlockState state, BlockState frozenState, BlockState barrierState, Boolean frozen) {
        if (worldgenlevel.getBlockState(pos).isAir() || worldgenlevel.getFluidState(pos).is(Fluids.LAVA)) {
            if (worldgenlevel.getBlockState(pos.below()).isAir() || worldgenlevel.getFluidState(pos.below()).is(Fluids.LAVA)) {
                worldgenlevel.setBlock(pos, barrierState, UPDATE_NONE);
            } else if (worldgenlevel.getBiome(pos).is(BiomeTags.IS_RIVER) && !worldgenlevel.getBiome(pos).is(Biomes.RIVER)) {
                if (worldgenlevel.getBlockState(pos.above()).isAir() && frozen) {
                    worldgenlevel.setBlock(pos, frozenState, UPDATE_NONE);
                } else {
                    if (state.is(Blocks.WATER) || state.is(Blocks.LAVA)) {
                        worldgenlevel.setBlock(pos, state.setValue(BlockStateProperties.LEVEL, 1), UPDATE_NONE);
                    } else {
                        worldgenlevel.setBlock(pos, state, UPDATE_NONE);
                    }
                }
            }
        }
    }
}
