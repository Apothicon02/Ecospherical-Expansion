package com.Apothic0n.api.biome.features.foliage_placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class GiantPineFoliagePlacer extends FoliagePlacer {
    public static final Codec<GiantPineFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> GiantPineFoliagePlacer.foliagePlacerParts(instance).and(
            (IntProvider.codec(0, 42).fieldOf("crown_height")).forGetter(giantPineFoliagePlacer -> giantPineFoliagePlacer.crownHeight)
    ).apply(instance, GiantPineFoliagePlacer::new));

    private final IntProvider crownHeight;
    public GiantPineFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider crownHeight) {
        super(radius, offset);
        this.crownHeight = crownHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EcoFoliagePlacerType.GIANT_PINE_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliagePlacer.FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockPos = attachment.pos();
        int i = 0;
        for (int j = blockPos.getY() - foliageHeight + offset; j <= blockPos.getY() + offset; ++j) {
            int k = blockPos.getY() - j;
            int l = foliageRadius + attachment.radiusOffset() + Mth.floor((float)k / (float)foliageHeight * 3.5f);
            int m = k > 0 && l == i && (j & 1) == 0 ? l + 1 : l;
            this.placeLeavesRow(level, blockSetter, random, config, new BlockPos(blockPos.getX(), j, blockPos.getZ()), m, 0, attachment.doubleTrunk());
            i = l;
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.crownHeight.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if (localX + localZ >= 7) {
            return true;
        }
        return localX * localX + localZ * localZ > range * range;
    }
}
