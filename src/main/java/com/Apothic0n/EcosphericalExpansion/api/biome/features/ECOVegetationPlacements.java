package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ECOVegetationPlacements {
    public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);
    public static final PlacedFeature TREES_LUSH_JUNGLE = PlacementUtils.register("trees_lush_jungle", ECOVegetationFeatures.TREES_LUSH_JUNGLE.placed(treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))));
    public static final PlacedFeature TREES_AZALEA = PlacementUtils.register("trees_azalea", ECOVegetationFeatures.TREES_AZALEA.placed(treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))));
    public static final PlacedFeature TREES_LUSH_OAK = PlacementUtils.register("trees_lush_oak", ECOVegetationFeatures.TREES_LUSH_OAK.placed(treePlacement(PlacementUtils.countExtra(3, 0.2F, 1))));
    public static final PlacedFeature TREES_MEGA_ACACIA = PlacementUtils.register("trees_mega_acacia", ECOVegetationFeatures.TREES_MEGA_ACACIA.placed(treePlacement(PlacementUtils.countExtra(2, 0.2F, 2))));
    public static final PlacedFeature TREES_MEGA_SWAMP = PlacementUtils.register("trees_mega_swamp", ECOVegetationFeatures.TREES_MEGA_SWAMP.placed(treePlacement(PlacementUtils.countExtra(1, 0.2F, 2))));
    public static final PlacedFeature TREES_THIN_SPRUCE = PlacementUtils.register("trees_thin_spruce", ECOVegetationFeatures.TREES_THIN_SPRUCE.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_BIG_DARK_OAK = PlacementUtils.register("trees_big_dark_oak", ECOVegetationFeatures.TREES_BIG_DARK_OAK.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_SMALL_DARK_OAK = PlacementUtils.register("trees_small_dark_oak", ECOVegetationFeatures.TREES_SMALL_DARK_OAK.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_NORMAL_DARK_OAK = PlacementUtils.register("trees_normal_dark_oak", ECOVegetationFeatures.TREES_NORMAL_DARK_OAK.placed(treePlacement(PlacementUtils.countExtra(5, 1F, 5))));
    public static final PlacedFeature TREES_TALL_BIRCH = PlacementUtils.register("trees_tall_birch", ECOVegetationFeatures.TREES_TALL_BIRCH.placed(treePlacement(PlacementUtils.countExtra(3, 1F, 2))));
    public static final PlacedFeature TREES_TALL_MUSHROOMS = PlacementUtils.register("trees_tall_mushrooms", ECOVegetationFeatures.TREES_TALL_MUSHROOMS.placed(treePlacement(PlacementUtils.countExtra(1, 1F, 1))));
    public static final PlacedFeature MISC_AMETHYSTS = PlacementUtils.register("misc_amethysts", ECOVegetationFeatures.MISC_AMETHYSTS.placed(treePlacement(PlacementUtils.countExtra(3, 1F, 2))));
    public static final PlacedFeature OAK_BUSH = PlacementUtils.register("oak_bush", ECOVegetationFeatures.OAK_BUSH.placed(treePlacement(PlacementUtils.countExtra(3, 0.2F, 1))));
    public static final PlacedFeature TALL_CACTI = PlacementUtils.register("tall_cacti", ECOVegetationFeatures.TALL_CACTI.placed(treePlacement(PlacementUtils.countExtra(3, 0.2F, 1))));

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier p_195485_) {
        return ImmutableList.<PlacementModifier>builder().add(p_195485_).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier p_195480_) {
        return treePlacementBase(p_195480_).build();
    }
}
