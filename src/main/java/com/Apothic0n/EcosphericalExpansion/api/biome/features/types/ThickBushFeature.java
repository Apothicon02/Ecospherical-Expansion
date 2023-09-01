package com.Apothic0n.EcosphericalExpansion.api.biome.features.types;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.SimpleIntConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ThickBushFeature extends Feature<SimpleIntConfiguration> {
    public ThickBushFeature(Codec<SimpleIntConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<SimpleIntConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource random = pContext.random();
        int height = pContext.config().getIntValue().sample(random)+1;
        if (worldgenlevel.isEmptyBlock(blockpos.below()) || worldgenlevel.getBlockState(blockpos.below()).is(BlockTags.SNOW)) {
            return false;
        } else {
            worldgenlevel.setBlock(blockpos.offset(0, 0, 0), Blocks.MANGROVE_ROOTS.defaultBlockState(), 2);
            worldgenlevel.setBlock(blockpos.offset(0, 1, 0), Blocks.MANGROVE_ROOTS.defaultBlockState(), 2);
            int randomNumber = (int)(Math.random()*(10)+1);
            boolean useBiome = false;
            if (randomNumber <= 6) {
                useBiome = true;
            }
            for (int y = 2; y < height; y++) {
                setLeaves(worldgenlevel, blockpos.offset(0, y, 0), useBiome);
                int randomNumber2 = (int)(Math.random()*(2-1+1)+1);
                if (randomNumber2 < 2) {
                    setLeaves(worldgenlevel, blockpos.offset(1, y, 0), useBiome);
                    setLeaves(worldgenlevel, blockpos.offset(0, y, 1), useBiome);
                    setLeaves(worldgenlevel, blockpos.offset(1, y, 1), useBiome);
                    if (y == 1) {
                        setLeaves(worldgenlevel, blockpos.offset(1, 0, 1), useBiome);
                    }
                } else {
                    setLeaves(worldgenlevel, blockpos.offset(-1, y, 0), useBiome);
                    setLeaves(worldgenlevel, blockpos.offset(0, y, -1), useBiome);
                    setLeaves(worldgenlevel, blockpos.offset(-1, y, -1), useBiome);
                    if (y == 1) {
                        setLeaves(worldgenlevel, blockpos.offset(-1, y, -1), useBiome);
                    }
                }
                if (y == height-1) {
                    if (randomNumber2 < 2) {
                        placeVines(worldgenlevel, blockpos, 0, y, 2, VineBlock.NORTH);
                        placeVines(worldgenlevel, blockpos, 1, y, 2, VineBlock.NORTH);
                        placeVines(worldgenlevel, blockpos, 2, y, 0, VineBlock.WEST);
                        placeVines(worldgenlevel, blockpos, 2, y, 1, VineBlock.WEST);
                    } else {
                        placeVines(worldgenlevel, blockpos, 0, y, -2, VineBlock.SOUTH);
                        placeVines(worldgenlevel, blockpos, -1, y, -2, VineBlock.SOUTH);
                        placeVines(worldgenlevel, blockpos, -2, y, 0, VineBlock.EAST);
                        placeVines(worldgenlevel, blockpos, -2, y, -1, VineBlock.EAST);
                    }
                    y++;
                    int randomNumber3 = (int) (Math.random() * (2 - 1 + 1) + 1);
                    if (randomNumber3 < 2) {
                        if (worldgenlevel.getBlockState(blockpos.offset(0, y, 0)).canBeReplaced()) {
                            worldgenlevel.setBlock(blockpos.offset(0, y, 0), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                        }
                    }
                }
            }
            return true;
        }
    }
    
    private void placeVines(WorldGenLevel worldGenLevel, BlockPos blockPos, int x, int startY, int z, BooleanProperty shape) {
        if (worldGenLevel.getBlockState(blockPos.offset(x, startY, z)).canBeReplaced()) {
            worldGenLevel.setBlock(blockPos.offset(x, startY, z), Blocks.VINE.defaultBlockState().setValue(shape, true), 2);
        }
        if (worldGenLevel.getBlockState(blockPos.offset(x, startY-1, z)).canBeReplaced()) {
            worldGenLevel.setBlock(blockPos.offset(x, startY-1, z), Blocks.VINE.defaultBlockState().setValue(shape, true), 2);
        }
    }

    private void setLeaves(WorldGenLevel worldGenLevel, BlockPos blockPos, Boolean useBiome) {
        int randomNumber = (int)(Math.random()*(2)+1);
        if (worldGenLevel.getBiome(blockPos).is(BiomeTags.IS_TAIGA) || worldGenLevel.getBiome(blockPos).is(BiomeTags.HAS_IGLOO) || worldGenLevel.getBiome(blockPos).is(Biomes.FROZEN_RIVER) ||
                worldGenLevel.getBiome(blockPos).is(Biomes.SNOWY_BEACH) || worldGenLevel.getBiome(blockPos).is(Biomes.ICE_SPIKES) || worldGenLevel.getBiome(blockPos).is(Biomes.SNOWY_SLOPES)) {
            worldGenLevel.setBlock(blockPos, Blocks.SPRUCE_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
        } else if (useBiome) {
            if (worldGenLevel.getBiome(blockPos).is(BiomeTags.IS_JUNGLE)) {
                worldGenLevel.setBlock(blockPos, Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
            } else if (worldGenLevel.getBiome(blockPos).is(BiomeTags.IS_SAVANNA)) {
                worldGenLevel.setBlock(blockPos, Blocks.ACACIA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
            } else if (worldGenLevel.getBiome(blockPos).is(BiomeTags.IS_BADLANDS) || worldGenLevel.getBiome(blockPos).is(BiomeTags.HAS_SWAMP_HUT) || worldGenLevel.getBiome(blockPos).is(Biomes.MANGROVE_SWAMP)) {
                worldGenLevel.setBlock(blockPos, Blocks.MANGROVE_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
            } else {
                worldGenLevel.setBlock(blockPos, Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
            }
        } else if (randomNumber < 2) {
            if (worldGenLevel.getBlockState(blockPos).canBeReplaced()) {
                worldGenLevel.setBlock(blockPos, Blocks.AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
            }
        } else {
            if (worldGenLevel.getBlockState(blockPos).canBeReplaced()) {
                worldGenLevel.setBlock(blockPos, Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
            }
        }
    }
}
