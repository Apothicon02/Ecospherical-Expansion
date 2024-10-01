package com.Apothic0n.EcosphericalExpansion.api.biome.features.trunk_placers;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.RockConfiguration;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.types.SpheroidRockFeature;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EcoTrunkPlacerType {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, EcosphericalExpansion.MODID);

    public static final RegistryObject<TrunkPlacerType<StraightBranchingTrunkPlacer>> STRAIGHT_BRANCHING_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("straight_branching_trunk_placer", () ->
            new TrunkPlacerType<>(StraightBranchingTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<GiantStraightBranchingTrunkPlacer>> GIANT_STRAIGHT_BRANCHING_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("giant_straight_branching_trunk_placer", () ->
            new TrunkPlacerType<>(GiantStraightBranchingTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<BranchingTrunkPlacer>> BRANCHING_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("branching_trunk_placer", () ->
            new TrunkPlacerType<>(BranchingTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<GiantBranchingTrunkPlacer>> GIANT_BRANCHING_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("giant_branching_trunk_placer", () ->
            new TrunkPlacerType<>(GiantBranchingTrunkPlacer.CODEC));
    public static void register(IEventBus eventBus) {
        TRUNK_PLACER_TYPES.register(eventBus);
    }
}