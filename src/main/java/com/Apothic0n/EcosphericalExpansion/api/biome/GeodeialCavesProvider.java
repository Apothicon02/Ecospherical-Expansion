package com.Apothic0n.EcosphericalExpansion.api.biome;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.common.BiomeDictionary;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GeodeialCavesProvider extends Region {
    public GeodeialCavesProvider(ResourceLocation name, RegionType type, int weight) {
        super(name, type, weight);
    }
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<com.mojang.datafixers.util.Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.WARM_OCEAN, ECOBiomeCreator.GEODEIAL_CAVES);
            builder.replaceBiome(Biomes.COLD_OCEAN, ECOBiomeCreator.GEODEIAL_CAVES);
            builder.replaceBiome(Biomes.DEEP_LUKEWARM_OCEAN, ECOBiomeCreator.GEODEIAL_CAVES);
            builder.replaceBiome(Biomes.DEEP_COLD_OCEAN, ECOBiomeCreator.GEODEIAL_CAVES);
            builder.replaceBiome(Biomes.LUSH_CAVES, ECOBiomeCreator.GEODEIAL_CAVES);
            builder.replaceBiome(Biomes.DRIPSTONE_CAVES, ECOBiomeCreator.GEODEIAL_CAVES);
        });
        BiomeDictionary.addTypes(ECOBiomeCreator.GEODEIAL_CAVES, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.UNDERGROUND);
    }
}