package com.Apothic0n.api.biome.features.foliage_placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class TallFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<TallFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> blobParts(instance).apply(instance, TallFoliagePlacer::new));

    public TallFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EcoFoliagePlacerType.TALL_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        foliageHeight = foliageHeight - 4;
        this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), (foliageRadius - 3) + (offset != offset - foliageHeight ? 1 : 0), offset, attachment.doubleTrunk());
        offset--;
        foliageHeight++;
        this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), (foliageRadius - 2) + (offset != offset - foliageHeight ? 1 : 0), offset, attachment.doubleTrunk());
        offset--;
        foliageHeight++;
        this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), (foliageRadius - 1) + (offset != offset - foliageHeight ? 1 : 0), offset, attachment.doubleTrunk());
        offset--;
        foliageHeight++;
        for(int i = offset; i >= offset - foliageHeight; --i) {
            int j = foliageRadius + (i != offset && i != offset - foliageHeight ? 1 : 0);
            placeThinLeavesRow(level, blockSetter, random, config, attachment.pos(), j, i, attachment.doubleTrunk());
        }
        offset--;
        foliageHeight++;
        this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), (foliageRadius - 1) + (offset != offset - foliageHeight ? 1 : 0), offset, attachment.doubleTrunk());
        offset--;
        foliageHeight++;
        this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), (foliageRadius - 2) + (offset != offset - foliageHeight ? 1 : 0), offset, attachment.doubleTrunk());
    }

    protected void placeThinLeavesRow(LevelSimulatedReader level, FoliagePlacer.FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, BlockPos blockPos, int foliageHeight, int foliageRadius, boolean offset) {
        int i = offset ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int j = -foliageHeight; j < foliageHeight + i; ++j) {
            for(int k = -foliageHeight; k < foliageHeight + i; ++k) {
                if (!this.shouldSkipLocationSigned(random, j, foliageRadius, k, foliageHeight, offset)) {
                    blockpos$mutableblockpos.setWithOffset(blockPos, j, foliageRadius, k);
                    tryPlaceLeaf(level, blockSetter, random, config, blockpos$mutableblockpos);
                }
            }
        }

    }
    
    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return Mth.square((float)localX + 0.5F) + Mth.square((float)localZ + 0.5F) > (float)(range * range);
    }
}
