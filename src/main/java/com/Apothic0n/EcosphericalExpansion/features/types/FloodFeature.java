package com.Apothic0n.EcosphericalExpansion.features.types;

import com.Apothic0n.EcosphericalExpansion.features.configuartions.FloodConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static net.minecraft.world.level.block.Block.UPDATE_NONE;

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
        Holder<Biome> biome = worldgenlevel.getBiome(pContext.origin());
        for (int x = origin.getX()-16; x < origin.getX()+16; ++x) {
            for (int z = origin.getZ()-16; z < origin.getZ()+16; ++z) {
                if (frozen && !frozenState.is(Blocks.VOID_AIR)) {
                    BlockPos pos = new BlockPos(x, elevation, z);
                    if (worldgenlevel.getBlockState(pos).is(state.getBlock()) && worldgenlevel.getBlockState(pos.above()).isAir() && worldgenlevel.getBiome(pos) == biome) {
                        worldgenlevel.setBlock(pos, frozenState, UPDATE_ALL);
                    }
                } else {
                    for (int y = elevation; y > worldgenlevel.getMinBuildHeight() + 1; --y) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (worldgenlevel.getBlockState(pos).isAir()) {
                            if (worldgenlevel.getBiome(pos) == biome) {
                                worldgenlevel.setBlock(pos, state, UPDATE_ALL);
                                if (!(worldgenlevel.getBiome(pos.below()) == biome) || worldgenlevel.getFluidState(pos.below()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.below(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.north()) == biome) || worldgenlevel.getFluidState(pos.north()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.north(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.east()) == biome) || worldgenlevel.getFluidState(pos.east()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.east(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.south()) == biome) || worldgenlevel.getFluidState(pos.south()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.south(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.west()) == biome) || worldgenlevel.getFluidState(pos.west()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.west(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.north().east()) == biome) || worldgenlevel.getFluidState(pos.north().east()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.north().east(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.south().east()) == biome) || worldgenlevel.getFluidState(pos.south().east()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.north().east(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.north().west()) == biome) || worldgenlevel.getFluidState(pos.north().west()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.north().east(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                                if (!(worldgenlevel.getBiome(pos.south().west()) == biome) || worldgenlevel.getFluidState(pos.south().west()).is(Fluids.LAVA)) {
                                    worldgenlevel.setBlock(pos.north().east(), Blocks.DEEPSLATE.defaultBlockState(), UPDATE_NONE);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
