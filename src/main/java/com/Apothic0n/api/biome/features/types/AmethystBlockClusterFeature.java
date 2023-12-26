package com.Apothic0n.api.biome.features.types;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AmethystBlockClusterFeature extends Feature<NoneFeatureConfiguration> {
    public AmethystBlockClusterFeature(Codec<NoneFeatureConfiguration> pContext) {
        super(pContext);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        BlockState BuddingAmethyst = Blocks.BUDDING_AMETHYST.defaultBlockState();
        BlockState AmethystBlock = Blocks.AMETHYST_BLOCK.defaultBlockState();
        BlockState GlowingAmethyst = Blocks.AMETHYST_CLUSTER.defaultBlockState();
        Block BioxGlowingAmethyst =  BuiltInRegistries.BLOCK.get(new ResourceLocation("biox", "glowing_amethyst"));
        if (!BioxGlowingAmethyst.equals(Blocks.AIR)) {
            GlowingAmethyst = BioxGlowingAmethyst.defaultBlockState();
        }
        if (worldgenlevel.isEmptyBlock(blockpos.below())) {
            return false;
        } else {
            worldgenlevel.setBlock(blockpos.offset(0, 0, 0), BuddingAmethyst, 2);
            makeColumn(worldgenlevel, blockpos, AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), true);
            makeColumn(worldgenlevel, blockpos.offset(1, 0, 0), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(0, 0, 1), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(-1, 0, 0), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(0, 0, -1), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(1, 0, -1), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(1, 0, -1), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(1, 0, 1), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            makeColumn(worldgenlevel, blockpos.offset(-1, 0, -1), AmethystBlock, GlowingAmethyst, (int)(Math.random()*(4)), false);
            return true;
        }
    }

    private void makeColumn(WorldGenLevel worldgenlevel, BlockPos blockpos, BlockState AmethystBlock, BlockState GlowingAmethyst, Integer height, Boolean isCenter) {
        if ((int)(Math.random()*(8)) <= 2) {
            for (int h = 0; h <= height; ++h) {
                worldgenlevel.setBlock(blockpos.offset(0, h, 0), AmethystBlock, 2);
                if (h == height && isCenter) {
                    worldgenlevel.setBlock(blockpos.offset(0, h+1, 0), GlowingAmethyst, 2);
                } else if (h == height && (int)(Math.random()*(4)) <= 2) {
                    worldgenlevel.setBlock(blockpos.offset(0, h+1, 0), GlowingAmethyst, 2);
                }
            }
        }
    }
}