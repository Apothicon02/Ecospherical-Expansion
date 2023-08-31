package com.Apothic0n.api.biome.features.trunk_placers;

import com.Apothic0n.EcosphericalExpansion;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class EcoTrunkPlacerType {
    public static final TrunkPlacerType<StraightBranchingTrunkPlacer> STRAIGHT_BRANCHING_TRUNK_PLACER = register("straight_branching_trunk_placer", StraightBranchingTrunkPlacer.CODEC);

    public static final TrunkPlacerType<GiantStraightBranchingTrunkPlacer> GIANT_STRAIGHT_BRANCHING_TRUNK_PLACER = register("giant_straight_branching_trunk_placer", GiantStraightBranchingTrunkPlacer.CODEC);

    public static final TrunkPlacerType<BranchingTrunkPlacer> BRANCHING_TRUNK_PLACER = register("branching_trunk_placer", BranchingTrunkPlacer.CODEC);

    public static final TrunkPlacerType<GiantBranchingTrunkPlacer> GIANT_BRANCHING_TRUNK_PLACER = register("giant_branching_trunk_placer", GiantBranchingTrunkPlacer.CODEC);
    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String key, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, new ResourceLocation(EcosphericalExpansion.MODID, key), new TrunkPlacerType<P>(codec));
    }

    public static void init() {

    }
}