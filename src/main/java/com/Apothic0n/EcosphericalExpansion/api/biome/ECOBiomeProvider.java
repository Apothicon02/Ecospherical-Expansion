package com.Apothic0n.EcosphericalExpansion.api.biome;

import com.Apothic0n.EcosphericalExpansion.config.CommonConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.common.BiomeDictionary;
import oshi.util.tuples.Pair;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ECOBiomeProvider extends BiomeProvider {
    public ECOBiomeProvider(ResourceLocation name, int weight) {
        super(name, weight);
    }

    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<com.mojang.datafixers.util.Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addSimilar(mapper, Biomes.FOREST, ECOBiomeCreator.LUSH_OAK);
        this.addSimilar(mapper, Biomes.JUNGLE, ECOBiomeCreator.LUSH_JUNGLE);
        this.addSimilar(mapper, Biomes.DARK_FOREST, ECOBiomeCreator.ENRICHED_ROOFED_FOREST);
        this.addSimilar(mapper, Biomes.SNOWY_TAIGA, ECOBiomeCreator.FROZEN_ROOFED_FOREST);
        this.addSimilar(mapper, Biomes.BIRCH_FOREST, ECOBiomeCreator.LUSH_BIRCH);
        this.addSimilar(mapper, Biomes.STONY_SHORE, ECOBiomeCreator.GLACIAL_PLAINS);
        this.addSimilar(mapper, Biomes.SAVANNA, ECOBiomeCreator.MEGA_SAVANNA);
        this.addSimilar(mapper, Biomes.SWAMP, ECOBiomeCreator.MEGA_SWAMP);
        this.addSimilar(mapper, Biomes.GROVE, ECOBiomeCreator.MUSHROOM_GROVE);
        this.addSimilar(mapper, Biomes.STONY_PEAKS, ECOBiomeCreator.DEEPSLATE_CLIFFS);
        this.addSimilar(mapper, Biomes.JAGGED_PEAKS, ECOBiomeCreator.CALCITE_CLIFFS);
        this.addSimilar(mapper, Biomes.PLAINS, ECOBiomeCreator.MUSHROOM_PLAINS);
        this.addSimilar(mapper, Biomes.DESERT, ECOBiomeCreator.LUSH_DESERT);
        this.addSimilar(mapper, Biomes.TAIGA, ECOBiomeCreator.ICY_TAIGA);
        this.addSimilar(mapper, Biomes.BEACH, ECOBiomeCreator.FLORAL_BEACH);
        this.addSimilar(mapper, Biomes.SNOWY_PLAINS, ECOBiomeCreator.OVERSNOWED_TAIGA);
        BiomeDictionary.addTypes(ECOBiomeCreator.LUSH_OAK, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
        BiomeDictionary.addTypes(ECOBiomeCreator.LUSH_JUNGLE, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.WET);
        BiomeDictionary.addTypes(ECOBiomeCreator.ENRICHED_ROOFED_FOREST, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(ECOBiomeCreator.FROZEN_ROOFED_FOREST, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.FOREST);
        BiomeDictionary.addTypes(ECOBiomeCreator.LUSH_BIRCH, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.CONIFEROUS);
        BiomeDictionary.addTypes(ECOBiomeCreator.GLACIAL_PLAINS, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(ECOBiomeCreator.MEGA_SAVANNA, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.MESA, BiomeDictionary.Type.DRY);
        BiomeDictionary.addTypes(ECOBiomeCreator.MEGA_SWAMP, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET);
        BiomeDictionary.addTypes(ECOBiomeCreator.MUSHROOM_GROVE, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.WET);
        BiomeDictionary.addTypes(ECOBiomeCreator.DEEPSLATE_CLIFFS, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.MOUNTAIN);
        BiomeDictionary.addTypes(ECOBiomeCreator.CALCITE_CLIFFS, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.MOUNTAIN);
        BiomeDictionary.addTypes(ECOBiomeCreator.MUSHROOM_PLAINS, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(ECOBiomeCreator.LUSH_DESERT, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.MESA, BiomeDictionary.Type.DRY, BiomeDictionary.Type.BEACH);
        BiomeDictionary.addTypes(ECOBiomeCreator.ICY_TAIGA, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.CONIFEROUS);
        BiomeDictionary.addTypes(ECOBiomeCreator.FLORAL_BEACH, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.PLAINS);
        BiomeDictionary.addTypes(ECOBiomeCreator.OVERSNOWED_TAIGA, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.CONIFEROUS);
    }

    public void addSimilar(Consumer<com.mojang.datafixers.util.Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper, ResourceKey<Biome> similarBiome, ResourceKey<Biome> biome) {
        List<String> allowed = (List<String>) CommonConfig.allowedBiomes.get();
        String biomeName = biome.location().toString();
        if (allowed.contains(biomeName)) {
            this.addBiomeSimilar(mapper, similarBiome, biome);
        }
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getOverworldSurfaceRules() {
        return Optional.of(ECOSurfaceRuleData.makeRules());
    }
}