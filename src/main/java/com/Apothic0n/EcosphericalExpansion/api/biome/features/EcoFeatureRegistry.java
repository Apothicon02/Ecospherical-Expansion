package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.*;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.types.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class EcoFeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, EcosphericalExpansion.MODID);

    public static final RegistryObject<Feature<RockConfiguration>> SPHEROID_ROCK = FEATURES.register("spheroid_rock", () ->
            new SpheroidRockFeature(RockConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
