package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.RockConfiguration;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.VerticalBlobConfiguration;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.types.AdditiveGroundBlobFeature;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.types.SpheroidRockFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class EcoFeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, EcosphericalExpansion.MODID);

    public static final RegistryObject<Feature<VerticalBlobConfiguration>> ADDITIVE_GROUND_BLOB = FEATURES.register("additive_ground_blob", () ->
            new AdditiveGroundBlobFeature(VerticalBlobConfiguration.CODEC));

    public static final RegistryObject<Feature<RockConfiguration>> SPHEROID_ROCK = FEATURES.register("spheroid_rock", () ->
            new SpheroidRockFeature(RockConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
