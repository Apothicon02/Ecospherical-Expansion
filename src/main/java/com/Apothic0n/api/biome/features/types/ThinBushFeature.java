package com.Apothic0n.api.biome.features.types;

import com.Apothic0n.api.biome.features.configurations.SimpleIntConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class ThinBushFeature extends Feature<SimpleIntConfiguration> {
    public ThinBushFeature(Codec<SimpleIntConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<SimpleIntConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource random = pContext.random();
        int height = pContext.config().getIntValue().sample(random);
        if (worldgenlevel.isEmptyBlock(blockpos.below()) || worldgenlevel.getBlockState(blockpos.below()).is(BlockTags.SNOW)) {
            return false;
        } else {
            int randomNumber = (int)(Math.random()*(2)+1);
            if (worldgenlevel.getBiome(blockpos).is(BiomeTags.IS_TAIGA) || worldgenlevel.getBiome(blockpos).is(BiomeTags.HAS_IGLOO) || worldgenlevel.getBiome(blockpos).is(Biomes.FROZEN_RIVER) ||
                    worldgenlevel.getBiome(blockpos).is(Biomes.SNOWY_BEACH) || worldgenlevel.getBiome(blockpos).is(Biomes.ICE_SPIKES) || worldgenlevel.getBiome(blockpos).is(Biomes.SNOWY_SLOPES)) {
                worldgenlevel.setBlock(blockpos, Blocks.BIG_DRIPLEAF.defaultBlockState(), 2);
            } else if (randomNumber < 2) {
                worldgenlevel.setBlock(blockpos.offset(0, 0, 0), Blocks.AZALEA.defaultBlockState(), 2);
            } else {
                worldgenlevel.setBlock(blockpos.offset(0, 0, 0), Blocks.FLOWERING_AZALEA.defaultBlockState(), 2);
            }
            int randomNumber2 = (int)(Math.random()*(10)+1);
            boolean useBiome = false;
            if (randomNumber2 <= 6) {
                useBiome = true;
            }
            for (int y = 1; y < height; y++) {
                setLeaves(worldgenlevel, blockpos.offset(0, y, 0), useBiome);
                if (y == height-1) {
                    int randomNumber3 = (int) (Math.random() * (8 - 1 + 1) + 1);
                    if (randomNumber3 < 2) {
                        placeVines(worldgenlevel, blockpos, 1, y, 0, VineBlock.WEST);
                    } else if (randomNumber3 == 2) {
                        placeVines(worldgenlevel, blockpos, 0, y, 1, VineBlock.NORTH);
                    } else if (randomNumber3 == 3) {
                        placeVines(worldgenlevel, blockpos, -1, y, 0, VineBlock.EAST);
                    } else if (randomNumber3 == 4) {
                        placeVines(worldgenlevel, blockpos, 0, y, -1, VineBlock.SOUTH);
                    }
                    y++;
                    int randomNumber4 = (int) (Math.random() * (2 - 1 + 1) + 1);
                    if (randomNumber4 < 2) {
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
        for (int y = startY; y > 1; y--) {
            if (worldGenLevel.getBlockState(blockPos.offset(x, y, z)).canBeReplaced()) {
                worldGenLevel.setBlock(blockPos.offset(x, y, z), Blocks.VINE.defaultBlockState().setValue(shape, true), 2);
            }
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
