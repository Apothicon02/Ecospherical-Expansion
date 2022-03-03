package com.Apothic0n.EcosphericalExpansion.api.biome;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.ECOBiomeFeatureGroups;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import terrablender.worldgen.DefaultBiomeProvider;
import terrablender.worldgen.noise.LayeredNoiseUtil;

import javax.annotation.Nullable;

public class ECOBiomes {
    @Nullable
    private static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music, BiomeSpecialEffects specialEffects)
    {
        return biome2(precipitation, category, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music, specialEffects);
    }

    private static Biome biome2(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music, BiomeSpecialEffects specialEffects)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).specialEffects(specialEffects).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome lushOak() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.plainsSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 2, 3);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOakTrees(biomeBuilder);
        BiomeDefaultFeatures.addOtherBirchTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addOverworldMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addFernGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addRareBerryBushes(biomeBuilder);
        ECOBiomeFeatureGroups.addRootedCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.01F))
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.7F)).build();

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F, 0.8F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome lushJungle() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        BiomeDefaultFeatures.baseJungleSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addLushJungleTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addAzaleaTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addRedFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addPurpleFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addNetherMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDripstone(biomeBuilder);
        ECOBiomeFeatureGroups.addTuffCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.02F))
                .fogColor(12638463).waterFogColor(329011).waterColor(0x21d9c0).skyColor(calculateSkyColor(0.7F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.JUNGLE, 0.8F, 0.9F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome enrichedRoofedForest() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 2, 3);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addBigDarkOakTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addNormalDarkOakTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addSmallDarkOakTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addFernGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addRootedCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addNormalOceanVegetationFeatures(biomeBuilder);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.7F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.3F, 0.8F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome frozenRoofedForest() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 1, 5, 4);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addBigDarkOakTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addNormalDarkOakTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addSmallDarkOakTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addFernGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addGrassyIceCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addColdOceanVegetationFeatures(biomeBuilder);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.3F, 0.8F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome glacialPlains() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.snowySpawns(spawnBuilder);
        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 1, 5, 4);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addFernGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addColdOceanVegetationFeatures(biomeBuilder);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.ICY, -0.8F, 0.3F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome lushBirch() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 1, 3);
        BiomeDefaultFeatures.snowySpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addVeryTallBirchTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addAzaleaTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addPurpleFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addCalciteCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_NORMAL);

        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.01F))
                .fogColor(12638463).waterFogColor(329011).waterColor(0x7a81e6).skyColor(calculateSkyColor(0.7F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.MOUNTAIN, 0.6F, 0.8F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome megaSavanna() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        BiomeDefaultFeatures.baseJungleSpawns(spawnBuilder);
        spawnBuilder
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 2, 2, 3))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 1))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 8, 4, 5))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 5, 1, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 4, 1, 3));


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addMegaSavannaTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addRedFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addGrassyTerracottaCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(0x21d9c0).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.SAVANNA, 2.0F, 0.0F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome megaSwamp() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        spawnBuilder
                .addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 2, 2, 3))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 3, 1, 3));


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addSwampClayDisk(biomeBuilder);
        ECOBiomeFeatureGroups.addMegaSwampTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addTreelessSwampVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addOverworldMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addNetherMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        BiomeDefaultFeatures.addSwampExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addDeadTuffCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP).foliageColorOverride(6975545)
                .fogColor(12638463).waterFogColor(2302743).waterColor(6388580).skyColor(calculateSkyColor(0.8F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.SWAMP, 0.8F, 0.9F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome mushroomGrove() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 1, 3);
        BiomeDefaultFeatures.snowySpawns(spawnBuilder);
        spawnBuilder
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 3, 1, 3))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.MOOSHROOM, 3, 2, 5));


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addTallMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addOverworldMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addMushroomCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_NORMAL);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW);
        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.01F))
                .fogColor(12638463).waterFogColor(329011).waterColor(0x7a81e6).skyColor(calculateSkyColor(0.7F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.MOUNTAIN, 0.5F, 0.8F, spawnBuilder, biomeBuilder, music, specialEffects);
    }

    public static Biome deepslateCliffs() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        spawnBuilder
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 4, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addMegaSavannaTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addAmethystCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_STONY_PEAKS);
        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.MOUNTAIN, 1.0F, 0.3F, spawnBuilder, biomeBuilder, music, specialEffects);
    }

    public static Biome calciteCliffs() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        spawnBuilder
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 4, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addMegaSavannaTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addPurpleFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addAmethystCavesVegetationFeatures(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS);
        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.MOUNTAIN, 1.0F, 0.3F, spawnBuilder, biomeBuilder, music, specialEffects);
    }

    public static Biome mushroomPlains() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 1, 3);
        spawnBuilder
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 3, 1, 3))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.MOOSHROOM, 3, 2, 5));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addThinSpruceTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addTallMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addOverworldMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addNetherMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDripstone(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW);
        BiomeSpecialEffects specialEffects = new BiomeSpecialEffects.Builder()
                .fogColor(12638463).waterFogColor(329011).waterColor(0x7a81e6).skyColor(calculateSkyColor(0.7F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.4F, 0.9F, spawnBuilder, biomeBuilder, music, specialEffects);
    }

    public static Biome lushDesert() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        BiomeDefaultFeatures.desertSpawns(spawnBuilder);
        spawnBuilder
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 1))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 8, 4, 5));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addMegaSavannaTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicBushes(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicDesertFoilage(biomeBuilder);
        ECOBiomeFeatureGroups.addRedFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addTallGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addGrassyTerracottaCavesVegetationFeatures(biomeBuilder);
        BiomeDefaultFeatures.addDripstone(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(0x21d9c0).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.SAVANNA, 2.0F, 0.0F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome icyTaiga() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.snowySpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        ECOBiomeFeatureGroups.addIceSpikes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addThinSpruceTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addTallMushrooms(biomeBuilder);
        ECOBiomeFeatureGroups.addBasicBushes(biomeBuilder);
        ECOBiomeFeatureGroups.addPurpleFlowers(biomeBuilder);
        ECOBiomeFeatureGroups.addFernGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addRootedCavesVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_COLD);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SNOWY_SLOPES);
        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.TAIGA, -0.2F, 0.9F, spawnBuilder, biomeBuilder, music, specialEffects);
    }

    public static Biome floralBeach() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 6, 3);
        spawnBuilder
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.TURTLE, 1, 1, 3))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 5, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addLightBambooVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addSandyJungleTrees(biomeBuilder);
        if (Math.random()*2 >= 1) {
            ECOBiomeFeatureGroups.addPurpleFlowers(biomeBuilder);
            ECOBiomeFeatureGroups.addGravelCavesBareFeatures(biomeBuilder);
        } else if (Math.random()*4 >= 2) {
            ECOBiomeFeatureGroups.addRedFlowers(biomeBuilder);
            ECOBiomeFeatureGroups.addSandCavesBareFeatures(biomeBuilder);
        } else {
            ECOBiomeFeatureGroups.addRedFlowers(biomeBuilder);
            ECOBiomeFeatureGroups.addPurpleFlowers(biomeBuilder);
        }
        ECOBiomeFeatureGroups.addBasicGrass(biomeBuilder);
        ECOBiomeFeatureGroups.addAdditionalLushVegetation(biomeBuilder);
        ECOBiomeFeatureGroups.addLushOceanVegetationFeatures(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.BEACH, 1.0F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }

    public static Biome oversnowedTaiga() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeDefaultFeatures.snowySpawns(spawnBuilder);
        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 1, 3, 8);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);
        ECOBiomeFeatureGroups.addIceSpikes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        ECOBiomeFeatureGroups.addMegaSpruceTrees(biomeBuilder);
        ECOBiomeFeatureGroups.addThinSpruceTrees(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        BiomeSpecialEffects specialEffects = (new BiomeSpecialEffects.Builder())
                .fogColor(12638463).waterFogColor(329011).waterColor(4159204).foliageColorOverride(0xffffff).skyColor(calculateSkyColor(0.1F)).build();
        return biome(Biome.Precipitation.SNOW, Biome.BiomeCategory.BEACH, -0.4F, 0.6F, spawnBuilder, biomeBuilder, NORMAL_MUSIC, specialEffects);
    }
}
