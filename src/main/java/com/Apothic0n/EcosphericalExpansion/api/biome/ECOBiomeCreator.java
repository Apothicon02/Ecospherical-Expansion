package com.Apothic0n.EcosphericalExpansion.api.biome;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;

import java.util.List;

public class ECOBiomeCreator {
    public static final ResourceKey<Biome> GEODEIAL_CAVES = register("geodeial_caves");
    public static final ResourceKey<Biome> MOLTEN_SEEPING_CAVES = register("molten_seeping_caves");
    //public static final ResourceKey<Biome> OBSIDIAN_SPIRALLING_CAVES = register("obsidian_spiraling_caves");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(EcosphericalExpansion.MODID, name));
    }
}