package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ECOVegetationPlacements {
    public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);
    public static final PlacedFeature TREES_LUSH_JUNGLE = PlacementUtils.register("eco:trees_lush_jungle", ECOVegetationFeatures.TREES_LUSH_JUNGLE.placed(treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))));
    public static final PlacedFeature TREES_SANDY_JUNGLE = PlacementUtils.register("eco:trees_sandy_jungle", ECOVegetationFeatures.TREES_SANDY_JUNGLE.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_AZALEA = PlacementUtils.register("eco:trees_azalea", ECOVegetationFeatures.TREES_AZALEA.placed(treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))));
    public static final PlacedFeature TREES_LUSH_OAK = PlacementUtils.register("eco:trees_lush_oak", ECOVegetationFeatures.TREES_LUSH_OAK.placed(treePlacement(PlacementUtils.countExtra(3, 0.2F, 1))));
    public static final PlacedFeature TREES_MEGA_ACACIA = PlacementUtils.register("eco:trees_mega_acacia", ECOVegetationFeatures.TREES_MEGA_ACACIA.placed(treePlacement(PlacementUtils.countExtra(2, 0.2F, 2))));
    public static final PlacedFeature TREES_MEGA_SWAMP = PlacementUtils.register("eco:trees_mega_swamp", ECOVegetationFeatures.TREES_MEGA_SWAMP.placed(treePlacement(PlacementUtils.countExtra(1, 0.2F, 2))));
    public static final PlacedFeature TREES_MEGA_SPRUCE = PlacementUtils.register("eco:trees_mega_spruce", ECOVegetationFeatures.TREES_MEGA_SPRUCE.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature TREES_THIN_SPRUCE = PlacementUtils.register("eco:trees_thin_spruce", ECOVegetationFeatures.TREES_THIN_SPRUCE.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_BIG_DARK_OAK = PlacementUtils.register("eco:trees_big_dark_oak", ECOVegetationFeatures.TREES_BIG_DARK_OAK.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_SMALL_DARK_OAK = PlacementUtils.register("eco:trees_small_dark_oak", ECOVegetationFeatures.TREES_SMALL_DARK_OAK.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_NORMAL_DARK_OAK = PlacementUtils.register("eco:trees_normal_dark_oak", ECOVegetationFeatures.TREES_NORMAL_DARK_OAK.placed(treePlacement(PlacementUtils.countExtra(5, 1F, 5))));
    public static final PlacedFeature TREES_TALL_BIRCH = PlacementUtils.register("eco:trees_tall_birch", ECOVegetationFeatures.TREES_TALL_BIRCH.placed(treePlacement(PlacementUtils.countExtra(3, 1F, 2))));
    public static final PlacedFeature TREES_TALL_MUSHROOMS = PlacementUtils.register("eco:trees_tall_mushrooms", ECOVegetationFeatures.TREES_TALL_MUSHROOMS.placed(treePlacement(PlacementUtils.countExtra(1, 1F, 1))));
    public static final PlacedFeature MISC_AMETHYSTS = PlacementUtils.register("eco:misc_amethysts", ECOVegetationFeatures.MISC_AMETHYSTS.placed(treePlacement(PlacementUtils.countExtra(3, 1F, 2))));
    public static final PlacedFeature OAK_BUSH = PlacementUtils.register("eco:oak_bush", ECOVegetationFeatures.OAK_BUSH.placed(treePlacement(PlacementUtils.countExtra(3, 0.2F, 1))));
    public static final PlacedFeature SPRUCE_BUSH = PlacementUtils.register("eco:spruce_bush", ECOVegetationFeatures.SPRUCE_BUSH.placed(treePlacement(PlacementUtils.countExtra(2, 0.2F, 1))));
    public static final PlacedFeature TALL_CACTI = PlacementUtils.register("eco:tall_cacti", ECOVegetationFeatures.TALL_CACTI.placed(treePlacement(PlacementUtils.countExtra(3, 0.2F, 1))));
    public static final PlacedFeature BASIC_FLOWERS = PlacementUtils.register("eco:basic_flowers", ECOVegetationFeatures.BASIC_FLOWERS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature RED_FLOWERS = PlacementUtils.register("eco:red_flowers", ECOVegetationFeatures.RED_FLOWERS.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PURPLE_FLOWERS = PlacementUtils.register("eco:purple_flowers", ECOVegetationFeatures.PURPLE_FLOWERS.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature OVERWORLD_MUSHROOMS = PlacementUtils.register("eco:overworld_mushrooms", ECOVegetationFeatures.OVERWORLD_MUSHROOMS.placed(CountPlacement.of(3), RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature NETHER_MUSHROOMS = PlacementUtils.register("eco:nether_mushrooms", ECOVegetationFeatures.NETHER_MUSHROOMS.placed(CountPlacement.of(2), RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature BASIC_GRASS = PlacementUtils.register("eco:basic_grass", ECOVegetationFeatures.BASIC_GRASS.placed(worldSurfaceSquaredWithCount(20)));
    public static final PlacedFeature TALL_GRASS = PlacementUtils.register("eco:tall_grass", ECOVegetationFeatures.TALL_GRASS.placed(worldSurfaceSquaredWithCount(2)));
    public static final PlacedFeature FERN_GRASS = PlacementUtils.register("eco:fern_grass", ECOVegetationFeatures.FERN_GRASS.placed(worldSurfaceSquaredWithCount(4)));

    public static List<PlacementModifier> worldSurfaceSquaredWithCount(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier pModifier) {
        return ImmutableList.<PlacementModifier>builder().add(pModifier).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pModifier) {
        return treePlacementBase(pModifier).build();
    }
}
