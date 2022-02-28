package com.Apothic0n.EcosphericalExpansion.api.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.Optional;
import java.util.function.Consumer;

public class ECOBiomeProvider extends BiomeProvider {
    public ECOBiomeProvider(ResourceLocation name, int weight) {
        super(name, weight);
    }

    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addBiomeSimilar(mapper, Biomes.FOREST, ECOBiomeCreator.LUSH_OAK);
        this.addBiomeSimilar(mapper, Biomes.JUNGLE, ECOBiomeCreator.LUSH_JUNGLE);
        this.addBiomeSimilar(mapper, Biomes.DARK_FOREST, ECOBiomeCreator.ENRICHED_ROOFED_FOREST);
        this.addBiomeSimilar(mapper, Biomes.SNOWY_TAIGA, ECOBiomeCreator.FROZEN_ROOFED_FOREST);
        this.addBiomeSimilar(mapper, Biomes.BIRCH_FOREST, ECOBiomeCreator.LUSH_BIRCH);
        this.addBiomeSimilar(mapper, Biomes.STONY_SHORE, ECOBiomeCreator.GLACIAL_PLAINS);
        this.addBiomeSimilar(mapper, Biomes.SAVANNA, ECOBiomeCreator.MEGA_SAVANNA);
        this.addBiomeSimilar(mapper, Biomes.SWAMP, ECOBiomeCreator.MEGA_SWAMP);
        this.addBiomeSimilar(mapper, Biomes.GROVE, ECOBiomeCreator.MUSHROOM_GROVE);
        this.addBiomeSimilar(mapper, Biomes.STONY_PEAKS, ECOBiomeCreator.DEEPSLATE_CLIFFS);
        this.addBiomeSimilar(mapper, Biomes.JAGGED_PEAKS, ECOBiomeCreator.CALCITE_CLIFFS);
        this.addBiomeSimilar(mapper, Biomes.PLAINS, ECOBiomeCreator.MUSHROOM_PLAINS);
        this.addBiomeSimilar(mapper, Biomes.DESERT, ECOBiomeCreator.LUSH_DESERT);
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getOverworldSurfaceRules() {
        return Optional.of(ECOSurfaceRuleData.makeRules());
    }
}