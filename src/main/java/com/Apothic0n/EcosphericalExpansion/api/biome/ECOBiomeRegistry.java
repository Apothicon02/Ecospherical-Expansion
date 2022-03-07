package com.Apothic0n.EcosphericalExpansion.api.biome;

import com.Apothic0n.EcosphericalExpansion.config.CommonConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ECOBiomeRegistry {
    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();
        registry.register(ECOBiomes.lushOak().setRegistryName(ECOBiomeCreator.LUSH_OAK.location()));
        registry.register(ECOBiomes.lushJungle().setRegistryName(ECOBiomeCreator.LUSH_JUNGLE.location()));
        registry.register(ECOBiomes.enrichedRoofedForest().setRegistryName(ECOBiomeCreator.ENRICHED_ROOFED_FOREST.location()));
        registry.register(ECOBiomes.frozenRoofedForest().setRegistryName(ECOBiomeCreator.FROZEN_ROOFED_FOREST.location()));
        registry.register(ECOBiomes.lushBirch().setRegistryName(ECOBiomeCreator.LUSH_BIRCH.location()));
        registry.register(ECOBiomes.glacialPlains().setRegistryName(ECOBiomeCreator.GLACIAL_PLAINS.location()));
        registry.register(ECOBiomes.megaSavanna().setRegistryName(ECOBiomeCreator.MEGA_SAVANNA.location()));
        registry.register(ECOBiomes.megaSwamp().setRegistryName(ECOBiomeCreator.MEGA_SWAMP.location()));
        registry.register(ECOBiomes.mushroomGrove().setRegistryName(ECOBiomeCreator.MUSHROOM_GROVE.location()));
        registry.register(ECOBiomes.deepslateCliffs().setRegistryName(ECOBiomeCreator.DEEPSLATE_CLIFFS.location()));
        registry.register(ECOBiomes.calciteCliffs().setRegistryName(ECOBiomeCreator.CALCITE_CLIFFS.location()));
        registry.register(ECOBiomes.mushroomPlains().setRegistryName(ECOBiomeCreator.MUSHROOM_PLAINS.location()));
        registry.register(ECOBiomes.lushDesert().setRegistryName(ECOBiomeCreator.LUSH_DESERT.location()));
        registry.register(ECOBiomes.icyTaiga().setRegistryName(ECOBiomeCreator.ICY_TAIGA.location()));
        registry.register(ECOBiomes.floralBeach().setRegistryName(ECOBiomeCreator.FLORAL_BEACH.location()));
        registry.register(ECOBiomes.oversnowedTaiga().setRegistryName(ECOBiomeCreator.OVERSNOWED_TAIGA.location()));
    }
}
