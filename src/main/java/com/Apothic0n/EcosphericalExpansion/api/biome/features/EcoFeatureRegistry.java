package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.*;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.types.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class EcoFeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, EcosphericalExpansion.MODID);

    public static final RegistryObject<Feature<RockConfiguration>> SPHEROID_ROCK = FEATURES.register("spheroid_rock", () ->
            new SpheroidRockFeature(RockConfiguration.CODEC));

    public static final RegistryObject<Feature<VerticalBlobConfiguration>> ADDITIVE_BLOB = FEATURES.register("additive_blob", () ->
            new AdditiveBlobFeature(VerticalBlobConfiguration.CODEC));

    public static final RegistryObject<Feature<VerticalBlobConfiguration>> ADDITIVE_GROUND_BLOB = FEATURES.register("additive_ground_blob", () ->
            new AdditiveGroundBlobFeature(VerticalBlobConfiguration.CODEC));

    public static final RegistryObject<Feature<FloatingBlobConfiguration>> FLOATING_BLOB = FEATURES.register("floating_blob", () ->
            new FloatingBlobFeature(FloatingBlobConfiguration.CODEC));

    public static final RegistryObject<Feature<CatchingFallConfiguration>> CATCHING_FALL = FEATURES.register("catching_fall", () ->
            new CatchingFallFeature(CatchingFallConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_BASALT_PILLAR = FEATURES.register("large_basalt_pillar", () ->
            new LargeBasaltPillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_DEEPSLATE_PILLAR = FEATURES.register("large_deepslate_pillar", () ->
            new LargeDeepslatePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_OBSIDIAN_PILLAR = FEATURES.register("large_obsidian_pillar", () ->
            new LargeObsidianPillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_ICE_PILLAR = FEATURES.register("large_ice_pillar", () ->
            new LargeIcePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_HONEY_PILLAR = FEATURES.register("large_honey_pillar", () ->
            new LargeHoneyPillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_HONEYCOMB_PILLAR = FEATURES.register("large_honeycomb_pillar", () ->
            new LargeHoneycombPillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_GRANITE_PILLAR = FEATURES.register("large_granite_pillar", () ->
            new LargeGranitePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_PRISMARINE_PILLAR = FEATURES.register("large_prismarine_pillar", () ->
            new LargePrismarinePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_DARK_PRISMARINE_PILLAR = FEATURES.register("large_dark_prismarine_pillar", () ->
            new LargeDarkPrismarinePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_SLIME_PILLAR = FEATURES.register("large_slime_pillar", () ->
            new LargeSlimePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_BLUE_ICE_PILLAR = FEATURES.register("large_blue_ice_pillar", () ->
            new LargeBlueIcePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_PACKED_ICE_PILLAR = FEATURES.register("large_packed_ice_pillar", () ->
            new LargePackedIcePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> THIN_BLACKSTONE_PILLAR = FEATURES.register("thin_blackstone_pillar", () ->
            new LargeBlackstonePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<LargeDripstoneConfiguration>> LARGE_CALCITE_PILLAR = FEATURES.register("large_calcite_pillar", () ->
            new LargeCalcitePillarFeature(LargeDripstoneConfiguration.CODEC));

    public static final RegistryObject<Feature<VerticalBlobConfiguration>> CRYSTAL_SPIKE = FEATURES.register("crystal_spike", () ->
            new CrystalSpikeFeature(VerticalBlobConfiguration.CODEC));

    public static final RegistryObject<Feature<SpiralConfiguration>> SPIRAL = FEATURES.register("spiral", () ->
            new SpiralFeature(SpiralConfiguration.CODEC));

    public static final RegistryObject<Feature<FloodConfiguration>> FLOOD = FEATURES.register("flood", () ->
            new FloodFeature(FloodConfiguration.CODEC));

    public static final RegistryObject<Feature<VegetationPatchConfiguration>> NON_FLOATING_PATCH = FEATURES.register("non_floating_patch", () ->
            new NonFloatingPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CAVE_KELP_FEATURE = FEATURES.register("cave_kelp", () ->
            new CaveKelpFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CAVE_PICKLE_FEATURE = FEATURES.register("cave_pickle", () ->
            new CavePickleFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> CAVE_SEAGRASS_FEATURE = FEATURES.register("cave_seagrass", () ->
            new CaveSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<FallenTreeConfiguration>> THIN_FALLEN_TREE_FEATURE = FEATURES.register("thin_fallen_tree", () ->
            new ThinFallenTreeFeature(FallenTreeConfiguration.CODEC));

    public static final RegistryObject<Feature<FallenTreeConfiguration>> THICK_FALLEN_TREE_FEATURE = FEATURES.register("thick_fallen_tree", () ->
            new ThickFallenTreeFeature(FallenTreeConfiguration.CODEC));

    public static final RegistryObject<Feature<AnvilRockConfiguration>> ANVIL_ROCK_FEATURE = FEATURES.register("anvil_rock", () ->
            new AnvilRockFeature(AnvilRockConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AMETHYST_BLOCK_CLUSTER_FEATURE = FEATURES.register("amethyst_block_cluster", () ->
            new AmethystBlockClusterFeature(NoneFeatureConfiguration.CODEC));


    public static final RegistryObject<Feature<SimpleBlockConfiguration>> STEMMED_2X2X2_CUBE_FEATURE = FEATURES.register("stemmed_2x2x2_cube", () ->
            new Stemmed2x2x2CubeFeature(SimpleBlockConfiguration.CODEC));

    public static final RegistryObject<Feature<SimpleIntConfiguration>> THIN_BUSH_FEATURE = FEATURES.register("thin_bush", () ->
            new ThinBushFeature(SimpleIntConfiguration.CODEC));

    public static final RegistryObject<Feature<SimpleIntConfiguration>> THICK_BUSH_FEATURE = FEATURES.register("thick_bush", () ->
            new ThickBushFeature(SimpleIntConfiguration.CODEC));

    public static final RegistryObject<Feature<WaterloggableRandomPatchConfiguration>> WATERLOGGABLE_RANDOM_PATCH_FEATURE = FEATURES.register("waterloggable_random_patch", () ->
            new WaterloggableRandomPatchFeature(WaterloggableRandomPatchConfiguration.CODEC));

    public static final RegistryObject<Feature<DoubleBlockConfiguration>> POND_FEATURE = FEATURES.register("pond", () ->
            new PondFeature(DoubleBlockConfiguration.CODEC));
    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
