package com.Apothic0n.api.biome.features.types;

import com.Apothic0n.api.biome.features.configurations.WaterloggableRandomPatchConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.stream.Stream;

public class WaterloggableRandomPatchFeature extends Feature<WaterloggableRandomPatchConfiguration> {
    public WaterloggableRandomPatchFeature(Codec<WaterloggableRandomPatchConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<WaterloggableRandomPatchConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource random = pContext.random();
        WaterloggableRandomPatchConfiguration randomPatchConfiguration = pContext.config();
        int i = 0;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        int j = randomPatchConfiguration.xz_spread + 1;
        int k = randomPatchConfiguration.y_spread + 1;
        for (int l = 0; l < randomPatchConfiguration.tries; ++l) {
            mutableBlockPos.setWithOffset(blockpos, random.nextInt(j) - random.nextInt(j), random.nextInt(k) - random.nextInt(k), random.nextInt(j) - random.nextInt(j));
            if (worldgenlevel.getBlockState(mutableBlockPos).equals(Blocks.WATER)) {
                placeBlock(worldgenlevel, mutableBlockPos, randomPatchConfiguration.to_place.getState(random, mutableBlockPos));
            } else {

            }
            ++i;
        }
        return i > 0;
    }

    private boolean placeBlock(WorldGenLevel worldGenLevel, BlockPos blockPos, BlockState blockState) {
        if (!blockState.canSurvive(worldGenLevel, blockPos)) return false;
        if (blockState.getBlock() instanceof DoublePlantBlock) {
            if (!worldGenLevel.isEmptyBlock(blockPos.above())) return false;
            DoublePlantBlock.placeAt(worldGenLevel, blockState, blockPos, 2);
            return true;
        } else {
            if (blockState.getProperties().contains(BlockStateProperties.WATERLOGGED) && worldGenLevel.getBlockState(blockPos).is(Blocks.WATER)) {
                worldGenLevel.setBlock(blockPos, blockState.setValue(BlockStateProperties.WATERLOGGED, true), 2);
            }
            worldGenLevel.setBlock(blockPos, blockState, 2);
        }
        return true;
    }
}
