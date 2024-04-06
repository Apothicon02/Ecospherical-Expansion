package com.Apothic0n.EcosphericalExpansion.api.biome.features.foliage_placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SphericalCapFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<SphericalCapFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> blobParts(instance).apply(instance, SphericalCapFoliagePlacer::new));

    public SphericalCapFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EcoFoliagePlacerType.SPHERICAL_CAP_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        for(int i = offset; i >= offset - foliageHeight; --i) {
            if (i != offset - foliageHeight) {
                int j = foliageRadius + (i != offset && i != offset - foliageHeight ? 1 : 0);
                this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), j, i, attachment.doubleTrunk());
            }
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return Mth.square((float)localX + 0.5F) + Mth.square((float)localZ + 0.5F) > (float)(range * range);
    }
}
