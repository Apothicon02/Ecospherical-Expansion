package com.Apothic0n.api.biome.features;

import com.Apothic0n.EcosphericalExpansion;
import com.Apothic0n.api.biome.features.configurations.*;
import com.Apothic0n.api.biome.features.types.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;

public abstract class EcoFeatureRegistry {
    public static final Feature<RockConfiguration> SPHEROID_ROCK = new SpheroidRockFeature(RockConfiguration.CODEC);

    public static final Feature<VerticalBlobConfiguration> ADDITIVE_BLOB = new AdditiveBlobFeature(VerticalBlobConfiguration.CODEC);

    public static final Feature<VerticalBlobConfiguration> ADDITIVE_GROUND_BLOB = new AdditiveGroundBlobFeature(VerticalBlobConfiguration.CODEC);

    public static final Feature<FloatingBlobConfiguration> FLOATING_BLOB = new FloatingBlobFeature(FloatingBlobConfiguration.CODEC);

    public static final Feature<CatchingFallConfiguration> CATCHING_FALL = new CatchingFallFeature(CatchingFallConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_BASALT_PILLAR = new LargeBasaltPillarFeature(LargeDripstoneConfiguration.CODEC);
    
    public static final Feature<LargeDripstoneConfiguration> LARGE_DEEPSLATE_PILLAR = new LargeDeepslatePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_OBSIDIAN_PILLAR = new LargeObsidianPillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_ICE_PILLAR = new LargeIcePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_HONEY_PILLAR = new LargeHoneyPillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_HONEYCOMB_PILLAR = new LargeHoneycombPillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_GRANITE_PILLAR = new LargeGranitePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_PRISMARINE_PILLAR = new LargePrismarinePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_DARK_PRISMARINE_PILLAR = new LargeDarkPrismarinePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_SLIME_PILLAR = new LargeSlimePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_BLUE_ICE_PILLAR = new LargeBlueIcePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_PACKED_ICE_PILLAR = new LargePackedIcePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> THIN_BLACKSTONE_PILLAR = new LargeBlackstonePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<LargeDripstoneConfiguration> LARGE_CALCITE_PILLAR = new LargeCalcitePillarFeature(LargeDripstoneConfiguration.CODEC);

    public static final Feature<VerticalBlobConfiguration> CRYSTAL_SPIKE = new CrystalSpikeFeature(VerticalBlobConfiguration.CODEC);

    public static final Feature<SpiralConfiguration> SPIRAL = new SpiralFeature(SpiralConfiguration.CODEC);

    public static final Feature<FloodConfiguration> FLOOD = new FloodFeature(FloodConfiguration.CODEC);

    public static final Feature<VegetationPatchConfiguration> NON_FLOATING_PATCH = new NonFloatingPatchFeature(VegetationPatchConfiguration.CODEC);

    public static final Feature<NoneFeatureConfiguration> CAVE_KELP_FEATURE = new CaveKelpFeature(NoneFeatureConfiguration.CODEC);

    public static final Feature<NoneFeatureConfiguration> CAVE_PICKLE_FEATURE = new CavePickleFeature(NoneFeatureConfiguration.CODEC);

    public static final Feature<ProbabilityFeatureConfiguration> CAVE_SEAGRASS_FEATURE = new CaveSeagrassFeature(ProbabilityFeatureConfiguration.CODEC);

    public static void register() {
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "spheroid_rock"), SPHEROID_ROCK);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "additive_blob"), ADDITIVE_BLOB);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "additive_ground_blob"), ADDITIVE_GROUND_BLOB);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "floating_blob"), FLOATING_BLOB);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "catching_fall"), CATCHING_FALL);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_basalt_pillar"), LARGE_BASALT_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_deepslate_pillar"), LARGE_DEEPSLATE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_obsidian_pillar"), LARGE_OBSIDIAN_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_ice_pillar"), LARGE_ICE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_honey_pillar"), LARGE_HONEY_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_honeycomb_pillar"), LARGE_HONEYCOMB_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_granite_pillar"), LARGE_GRANITE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_prismarine_pillar"), LARGE_PRISMARINE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_dark_prismarine_pillar"), LARGE_DARK_PRISMARINE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_slime_pillar"), LARGE_SLIME_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_blue_ice_pillar"), LARGE_BLUE_ICE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_packed_ice_pillar"), LARGE_PACKED_ICE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "thin_blackstone_pillar"), THIN_BLACKSTONE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "large_calcite_pillar"), LARGE_CALCITE_PILLAR);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "crystal_spike"), CRYSTAL_SPIKE);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "spiral"), SPIRAL);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "flood"), FLOOD);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "non_floating_patch"), NON_FLOATING_PATCH);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "cave_kelp"), CAVE_KELP_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "cave_pickle"), CAVE_PICKLE_FEATURE);
        Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(EcosphericalExpansion.MODID, "cave_seagrass"), CAVE_SEAGRASS_FEATURE);
    }
}
