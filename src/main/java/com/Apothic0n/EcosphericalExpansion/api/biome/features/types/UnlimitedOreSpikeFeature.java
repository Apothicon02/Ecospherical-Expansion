package com.Apothic0n.EcosphericalExpansion.api.biome.features.types;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.OreSpikeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class UnlimitedOreSpikeFeature extends Feature<OreSpikeConfiguration> {
    public UnlimitedOreSpikeFeature(Codec<OreSpikeConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<OreSpikeConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource random = pContext.random();
        OreSpikeConfiguration config = pContext.config();
        int blobMass = config.getBlobMass().sample(random);
        int blobWidth = config.getBlobWidth().sample(random);
        int blobHeight = config.getBlobHeight().sample(random);
        if (worldgenlevel.getBlockState(blockpos.above()).isSolid() && worldgenlevel.getBlockState(blockpos.above().north()).isSolid() && worldgenlevel.getBlockState(blockpos.above().east()).isSolid() &&
                worldgenlevel.getBlockState(blockpos.above().south()).isSolid() && worldgenlevel.getBlockState(blockpos.above().west()).isSolid()) {
            placeOre(config, random, worldgenlevel, blockpos);
            BlockPos blockpos1 = blockpos.below(blobHeight/4);
            BlockPos blockpos2 = blockpos.below(blobHeight/4);
            boolean northNegative = false;
            boolean eastNegative = false;
            int randomNumber = (int)(random.nextFloat() * 4.0 + 1.0);
            if (randomNumber >= 4) {
                northNegative = true;
                eastNegative = true;
            } else if (randomNumber == 3) {
                northNegative = true;
            } else if (randomNumber == 2) {
                eastNegative = true;
            }

            int xFactor = 1;
            int zFactor = 1;
            if (northNegative) {
                xFactor = -1;
            }

            if (eastNegative) {
                zFactor = -1;
            }

            int thickness = 0;
            for (int i = 0; i < blobMass * 4; ++i) {
                int randomNumber2 = (int)(random.nextFloat() * 4.0 + 1.0);
                if (randomNumber2 >= 4 / blobHeight) {
                    blockpos1 = new BlockPos(blockpos1.getX() + xFactor, blockpos1.getY() + 1, blockpos1.getZ() + zFactor);
                } else {
                    blockpos1 = new BlockPos(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ());
                }

                int xDistance = blockpos1.getX() - blockpos.getX();
                int zDistance = blockpos1.getZ() - blockpos.getZ();
                if (xDistance >= blobWidth || zDistance >= blobWidth) {
                    i = blobMass * 5;
                }

                placeOre(config, random, worldgenlevel, blockpos1);

                thickness += 1;
                i += 3;
            }

            thickness = thickness/2;
            boolean toggleThickness = false;
            for (int i = 0; i < blobMass * 4; ++i) {
                int randomNumber2 = (int)(random.nextFloat() * 4.0 + 1.0);
                if (randomNumber2 >= 4 / blobHeight) {
                    blockpos2 = new BlockPos(blockpos2.getX() + xFactor, blockpos2.getY() + 1, blockpos2.getZ() + zFactor);
                } else {
                    blockpos2 = new BlockPos(blockpos2.getX(), blockpos2.getY() + 1, blockpos2.getZ());
                }

                int xDistance = blockpos2.getX() - blockpos.getX();
                int zDistance = blockpos2.getZ() - blockpos.getZ();
                if (xDistance >= blobWidth || zDistance >= blobWidth) {
                    i = blobMass * 5;
                }

                for (int w = 0; w < thickness; w++) {
                    for (int l = 0; l < thickness; l++) {
                        if (!(w == l && l == thickness-1)) {
                            placeOre(config, random, worldgenlevel, blockpos2.north(w).east(l));
                            placeOre(config, random, worldgenlevel, blockpos2.north(w).west(l));
                            placeOre(config, random, worldgenlevel, blockpos2.south(w).east(l));
                            placeOre(config, random, worldgenlevel, blockpos2.south(w).west(l));
                            placeOre(config, random, worldgenlevel, blockpos2.north(l).east(w));
                            placeOre(config, random, worldgenlevel, blockpos2.north(l).west(w));
                            placeOre(config, random, worldgenlevel, blockpos2.south(l).east(w));
                            placeOre(config, random, worldgenlevel, blockpos2.south(l).west(w));
                        }
                    }
                }

                if (toggleThickness) {
                    thickness -= 1;
                    toggleThickness = false;
                } else {
                    toggleThickness = true;
                }
                i += 3;
            }

            return true;
        }
        return false;
    }
    private void placeOre(OreSpikeConfiguration config, RandomSource random, WorldGenLevel worldgenlevel, BlockPos blockPos) {
        if (blockPos.getY() > 0) {
            worldgenlevel.setBlock(blockPos, config.upperState.getState(random, blockPos), 2);
        } else {
            worldgenlevel.setBlock(blockPos, config.lowerState.getState(random, blockPos), 2);
        }
    }
}