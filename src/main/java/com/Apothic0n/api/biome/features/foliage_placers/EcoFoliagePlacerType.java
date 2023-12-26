package com.Apothic0n.api.biome.features.foliage_placers;

import com.Apothic0n.EcosphericalExpansion;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class EcoFoliagePlacerType {
    public static final FoliagePlacerType<GiantPineFoliagePlacer> GIANT_PINE_FOLIAGE_PLACER = register("giant_pine_foliage_placer", GiantPineFoliagePlacer.CODEC);
    public static final FoliagePlacerType<TallFoliagePlacer> TALL_FOLIAGE_PLACER = register("tall_foliage_placer", TallFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String key, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, new ResourceLocation(EcosphericalExpansion.MODID, key), new FoliagePlacerType<P>(codec));
    }

    public static void init() {

    }
}