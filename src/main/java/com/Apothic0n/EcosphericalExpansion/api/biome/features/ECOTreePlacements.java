package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ECOTreePlacements {
    public static final PlacedFeature BRANCHING_JUNGLE_TREE_CHECKED = PlacementUtils.register("branching_jungle_tree_checked", ECOTreeFeatures.BRANCHING_JUNGLE_TREE.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING));

    public static final PlacedFeature MEGA_AZALEA_CHECKED = PlacementUtils.register("mega_azalea_checked", ECOTreeFeatures.MEGA_AZALEA_TREE.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature AZALEA_BUSH_CHECKED = PlacementUtils.register("azalea_bush_checked", ECOTreeFeatures.AZALEA_BUSH.filteredByBlockSurvival(Blocks.OAK_SAPLING));

    public static final PlacedFeature TOWERING_OAK_CHECKED = PlacementUtils.register("towering_oak_checked", ECOTreeFeatures.TOWERING_OAK.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature BRANCHING_OAK_CHECKED = PlacementUtils.register("branching_oak_checked", ECOTreeFeatures.BRANCHING_OAK.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature TWISTED_OAK_CHECKED = PlacementUtils.register("twisted_oak_checked", ECOTreeFeatures.TWISTED_OAK.filteredByBlockSurvival(Blocks.OAK_SAPLING));

    public static final PlacedFeature TOWERING_SPRUCE_CHECKED = PlacementUtils.register("towering_spruce_checked", ECOTreeFeatures.TOWERING_SPRUCE.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));

    public static final PlacedFeature TILTED_DARK_OAK_CHECKED = PlacementUtils.register("tilted_dark_oak_checked", ECOTreeFeatures.TILTED_DARK_OAK.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING));
    public static final PlacedFeature SHORT_DARK_OAK_CHECKED = PlacementUtils.register("short_dark_oak_checked", ECOTreeFeatures.SHORT_DARK_OAK.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING));
    public static final PlacedFeature NORMAL_DARK_OAK_CHECKED = PlacementUtils.register("normal_dark_oak_checked", ECOTreeFeatures.NORMAL_DARK_OAK.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING));

    public static final PlacedFeature TILTED_TALL_BIRCH_CHECKED = PlacementUtils.register("tilted_tall_birch_checked", ECOTreeFeatures.TILTED_TALL_BIRCH.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final PlacedFeature NORMAL_TALL_BIRCH_CHECKED = PlacementUtils.register("normal_tall_birch_checked", ECOTreeFeatures.NORMAL_TALL_BIRCH.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final PlacedFeature BIG_TALL_BIRCH_CHECKED = PlacementUtils.register("big_tall_birch_checked", ECOTreeFeatures.BIG_TALL_BIRCH.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));

    public static final PlacedFeature MEGA_ACACIA_CHECKED = PlacementUtils.register("mega_acacia_checked", ECOTreeFeatures.MEGA_ACACIA.filteredByBlockSurvival(Blocks.ACACIA_SAPLING));
    public static final PlacedFeature MULTI_ACACIA_CHECKED = PlacementUtils.register("multi_acacia_checked", ECOTreeFeatures.MULTI_ACACIA.filteredByBlockSurvival(Blocks.ACACIA_SAPLING));

    public static final PlacedFeature MEGA_SWAMP_BIG_CHECKED = PlacementUtils.register("mega_swamp_big_checked", ECOTreeFeatures.MEGA_SWAMP_BIG.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature MEGA_SWAMP_SMALL_CHECKED = PlacementUtils.register("mega_swamp_small_checked", ECOTreeFeatures.MEGA_SWAMP_SMALL.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature MULTI_SWAMP_CHECKED = PlacementUtils.register("multi_swamp_checked", ECOTreeFeatures.MULTI_SWAMP.filteredByBlockSurvival(Blocks.OAK_SAPLING));

    public static final PlacedFeature TILTED_TALL_MUSHROOM_CHECKED = PlacementUtils.register("tilted_tall_mushroom_checked", ECOTreeFeatures.TILTED_TALL_MUSHROOM.filteredByBlockSurvival(Blocks.BROWN_MUSHROOM));
    public static final PlacedFeature NORMAL_TALL_MUSHROOM_CHECKED = PlacementUtils.register("normal_tall_mushroom_checked", ECOTreeFeatures.NORMAL_TALL_MUSHROOM.filteredByBlockSurvival(Blocks.RED_MUSHROOM));
    public static final PlacedFeature BIG_TALL_MUSHROOM_CHECKED = PlacementUtils.register("big_tall_mushroom_checked", ECOTreeFeatures.BIG_TALL_MUSHROOM.filteredByBlockSurvival(Blocks.RED_MUSHROOM));

    public static final PlacedFeature COPPER_DEEPSLATE_STACK = PlacementUtils.register("copper_deepslate_stack", ECOTreeFeatures.COPPER_DEEPSLATE_STACK.filteredByBlockSurvival(Blocks.GRASS_BLOCK));
    public static final PlacedFeature DEEPSLATE_BLOCK_STACK = PlacementUtils.register("deepslate_block_stack", ECOTreeFeatures.DEEPSLATE_BLOCK_STACK.filteredByBlockSurvival(Blocks.DEEPSLATE));
    public static final PlacedFeature MISC_AMETHSYT_GEODE = PlacementUtils.register("misc_amethyst_geode", ECOTreeFeatures.MISC_AMETHSYT_GEODE.filteredByBlockSurvival(Blocks.AMETHYST_CLUSTER));
    public static final PlacedFeature MISC_AMETHYST_CLUSTER = PlacementUtils.register("misc_amethyst_cluster", ECOTreeFeatures.MISC_AMETHSYT_CLUSTER.filteredByBlockSurvival(Blocks.AMETHYST_CLUSTER));
    public static final PlacedFeature MISC_AMETHYST_LARGE = PlacementUtils.register("misc_amethyst_large", ECOTreeFeatures.MISC_AMETHSYT_LARGE.filteredByBlockSurvival(Blocks.LARGE_AMETHYST_BUD));
    public static final PlacedFeature MISC_AMETHYST_NORMAL = PlacementUtils.register("misc_amethyst_normal", ECOTreeFeatures.MISC_AMETHSYT_NORMAL.filteredByBlockSurvival(Blocks.MEDIUM_AMETHYST_BUD));
    public static final PlacedFeature MISC_AMETHYST_SMALL = PlacementUtils.register("misc_amethyst_small", ECOTreeFeatures.MISC_AMETHSYT_SMALL.filteredByBlockSurvival(Blocks.SMALL_AMETHYST_BUD));

}
