package com.Apothic0n.EcosphericalExpansion.api.biome.features.types;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.FloodConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;

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
        if (config.frozen) {
            state = config.frozenMaterial.getState(random, origin);
        }
        Integer elevation = config.getElevation().sample(random);
        if (elevation == -420) {
            elevation = origin.getY();
        }
        for (int x = origin.getX(); x < origin.getX()+16; ++x) {
            for (int z = origin.getZ(); z < origin.getZ()+16; ++z) {
                for (int y = elevation; y > worldgenlevel.getMinBuildHeight() + 1; --y) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (!worldgenlevel.getBlockState(pos).isSolid()) {
                        worldgenlevel.setBlock(pos, state, UPDATE_NONE);
                    }
                }
            }
        }
        return true;
    }
}
