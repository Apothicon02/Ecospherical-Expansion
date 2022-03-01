package com.Apothic0n.EcosphericalExpansion.api.biome;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class ECOBiomeCreator {
    public static final ResourceKey<Biome> LUSH_OAK = register("lush_oak");
    public static final ResourceKey<Biome> LUSH_JUNGLE = register("lush_jungle");
    public static final ResourceKey<Biome> ENRICHED_ROOFED_FOREST = register("enriched_roofed_forest");
    public static final ResourceKey<Biome> FROZEN_ROOFED_FOREST = register("frozen_roofed_forest");
    public static final ResourceKey<Biome> LUSH_BIRCH = register("lush_birch");
    public static final ResourceKey<Biome> GLACIAL_PLAINS = register("glacial_plains");
    public static final ResourceKey<Biome> MEGA_SAVANNA = register("mega_savanna");
    public static final ResourceKey<Biome> MEGA_SWAMP = register("mega_swamp");
    public static final ResourceKey<Biome> MUSHROOM_GROVE = register("mushroom_grove");
    public static final ResourceKey<Biome> DEEPSLATE_CLIFFS = register("deepslate_cliffs");
    public static final ResourceKey<Biome> CALCITE_CLIFFS = register("calcite_cliffs");
    public static final ResourceKey<Biome> MUSHROOM_PLAINS = register("mushroom_plains");
    public static final ResourceKey<Biome> LUSH_DESERT = register("lush_desert");

    private static ResourceKey<Biome> register(String name) {
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
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(EcosphericalExpansion.MODID, name));
    }
}
