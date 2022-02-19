package com.Apothic0n.EcosphericalExpansion.api.biome.features.caves;

import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SmallDripleafBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WaterloggedVegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Properties;

public class ECOCaveFeatures {
    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> DRIPLEAF = FeatureUtils.register("dripleaf", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeSmallDripleaf, () -> {
        return makeDripleaf(Direction.EAST);
    }, () -> {
        return makeDripleaf(Direction.WEST);
    }, () -> {
        return makeDripleaf(Direction.SOUTH);
    }, () -> {
        return makeDripleaf(Direction.NORTH);
    }))));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> BAMBOOAMETHYST = FeatureUtils.register("bamboo_amethyst", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeBambooAmethyst, () -> {
        return makeBambooAmethyst();
    }))));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> ROOTEDDIRT = FeatureUtils.register("rooted_dirt", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeRootedDirt, () -> {
        return makeRootedDirt();
    }))));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> GRASSYICE = FeatureUtils.register("grassy_ice", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeGrassyIce, () -> {
        return makeGrassyIce();
    }))));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> ICESTACKS = FeatureUtils.register("ice_stacks", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeIceStacks, () -> {
        return makeIceStacks();
    }))));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> GRASSYTERRACOTTA = FeatureUtils.register("grassy_terracotta", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeDrippingBamboo, () -> {
        return makeDrippingBamboo();
    }))));

    public static final ConfiguredFeature<SimpleRandomFeatureConfiguration, ?> SANDYTERRACOTTA = FeatureUtils.register("sandy_terracotta", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(List.of(ECOCaveFeatures::makeSandyAmethyst, () -> {
        return makeSandyAmethyst();
    }))));

    //TUFF
    public static final ConfiguredFeature<?, ?> TUFF_WITH_DRIPLEAVES = FeatureUtils.register("tuff_with_dripleaves", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE.getName(), BlockStateProvider.simple(Blocks.TUFF), () -> {
        return DRIPLEAF.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> TUFF_POOL_WITH_DRIPLEAVES = FeatureUtils.register("tuff_pool_with_dripleaves", Feature.WATERLOGGED_VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE.getName(), BlockStateProvider.simple(Blocks.TUFF), () -> {
        return DRIPLEAF.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> TUFF_CAVES_TUFF = FeatureUtils.register("tuff_caves_tuff", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return TUFF_WITH_DRIPLEAVES.placed();
    }, () -> {
        return TUFF_POOL_WITH_DRIPLEAVES.placed();
    })));

    //CALCITE
    public static final ConfiguredFeature<?, ?> CALCITE_WITH_BAMBOO = FeatureUtils.register("calcite_with_bambooamethyst", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), BlockStateProvider.simple(Blocks.GRASS_BLOCK), () -> {
        return BAMBOOAMETHYST.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> CALCITE_POOL_WITH_BAMBOO = FeatureUtils.register("calcite_pool_with_bambooamethyst", Feature.WATERLOGGED_VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), BlockStateProvider.simple(Blocks.CALCITE), () -> {
        return BAMBOOAMETHYST.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> CALCITE_CAVES_CALCITE = FeatureUtils.register("calcite_caves_calcite", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return CALCITE_WITH_BAMBOO.placed();
    }, () -> {
        return CALCITE_POOL_WITH_BAMBOO.placed();
    })));

    //BLACKSTONE
    public static final ConfiguredFeature<?, ?> BLACKSTONE_WITH_BAMBOO = FeatureUtils.register("blackstone_with_bambooamethyst", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ROOTED_DIRT.defaultBlockState(), 1).add(Blocks.MYCELIUM.defaultBlockState(), 9)), () -> {
        return BAMBOOAMETHYST.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> BLACKSTONE_POOL_WITH_BAMBOO = FeatureUtils.register("blackstone_pool_with_bambooamethyst", Feature.WATERLOGGED_VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), BlockStateProvider.simple(Blocks.BLACKSTONE), () -> {
        return BAMBOOAMETHYST.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> MUSHROOM_CAVES_BLACKSTONE = FeatureUtils.register("mushroom_caves_blackstone", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return BLACKSTONE_WITH_BAMBOO.placed();
    }, () -> {
        return BLACKSTONE_POOL_WITH_BAMBOO.placed();
    })));

    //ROOTED_DIRT
    public static final ConfiguredFeature<?, ?> ROOTED_DIRT = FeatureUtils.register("rooted_dirt", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ROOTED_DIRT.defaultBlockState(), 1).add(Blocks.DIRT.defaultBlockState(), 1).add(Blocks.COARSE_DIRT.defaultBlockState(), 2).add(Blocks.PODZOL.defaultBlockState(), 4).add(Blocks.GRASS_BLOCK.defaultBlockState(), 7)), () -> {
        return ROOTEDDIRT.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> ROOTED_DIRT_POOL = FeatureUtils.register("rooted_dirt_pool", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ROOTED_DIRT.defaultBlockState(), 1).add(Blocks.DIRT.defaultBlockState(), 1).add(Blocks.COARSE_DIRT.defaultBlockState(), 2).add(Blocks.PODZOL.defaultBlockState(), 4)), () -> {
        return ROOTEDDIRT.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> ROOTED_CAVES_ROOTED_DIRT = FeatureUtils.register("rooted_caves_rooted_dirt", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return ROOTED_DIRT.placed();
    }, () -> {
        return ROOTED_DIRT_POOL.placed();
    })));

    //GRASSY_ICE
    public static final ConfiguredFeature<?, ?> GRASSY_ICE = FeatureUtils.register("grassy_ice", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS_BLOCK.defaultBlockState(), 4).add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)), () -> {
        return GRASSYICE.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> GRASSY_ICE_POOL = FeatureUtils.register("grassy_ice_pool", WaterloggedVegetationPatchFeature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS_BLOCK.defaultBlockState(), 3).add(Blocks.COARSE_DIRT.defaultBlockState(), 1)), () -> {
        return GRASSYICE.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> GRASSY_ICE_CAVES_GRASSY_ICE = FeatureUtils.register("grassy_ice_caves_grassy_ice", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return GRASSY_ICE.placed();
    }, () -> {
        return GRASSY_ICE_POOL.placed();
    })));

    //GRASSY_TERRACOTTA
    public static final ConfiguredFeature<?, ?> GRASSY_TERRACOTTA = FeatureUtils.register("grassy_terracotta", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS_BLOCK.defaultBlockState(), 4).add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)), () -> {
        return GRASSYTERRACOTTA.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> GRASSY_TERRACOTTA_POOL = FeatureUtils.register("grassy_terracotta_pool", WaterloggedVegetationPatchFeature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS_BLOCK.defaultBlockState(), 2).add(Blocks.COARSE_DIRT.defaultBlockState(), 1).add(Blocks.MOSS_BLOCK.defaultBlockState(), 4)), () -> {
        return GRASSYTERRACOTTA.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> GRASSY_TERRACOTTA_CAVES_GRASSY_TERRACOTTA = FeatureUtils.register("grassy_terracotta_caves_grassy_terracotta", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return GRASSY_TERRACOTTA.placed();
    }, () -> {
        return GRASSY_TERRACOTTA_POOL.placed();
    })));

    //SANDY_TERRACOTTA
    public static final ConfiguredFeature<?, ?> SANDY_TERRACOTTA = FeatureUtils.register("sandy_terracotta", Feature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SMOOTH_BASALT.defaultBlockState(), 4).add(Blocks.GRASS_BLOCK.defaultBlockState(), 1)), () -> {
        return SANDYTERRACOTTA.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 2, 0.05F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<?, ?> SANDY_TERRACOTTA_POOL = FeatureUtils.register("sandy_terracotta_pool", WaterloggedVegetationPatchFeature.VEGETATION_PATCH.configured(new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE.getName(), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS_BLOCK.defaultBlockState(), 2).add(Blocks.COARSE_DIRT.defaultBlockState(), 1)), () -> {
        return SANDYTERRACOTTA.placed();
    }, CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F)));
    public static final ConfiguredFeature<RandomBooleanFeatureConfiguration, ?> SANDY_TERRACOTTA_CAVES_SANDY_TERRACOTTA = FeatureUtils.register("sandy_terracotta_caves_sandy_terracotta", Feature.RANDOM_BOOLEAN_SELECTOR.configured(new RandomBooleanFeatureConfiguration(() -> {
        return SANDY_TERRACOTTA.placed();
    }, () -> {
        return SANDY_TERRACOTTA_POOL.placed();
    })));

    private static PlacedFeature makeDripleaf(Direction p_194967_) {
        return Feature.BLOCK_COLUMN.configured(new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 4), 2).add(ConstantInt.of(0), 1).build()), BlockStateProvider.simple(Blocks.BIG_DRIPLEAF_STEM.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, p_194967_))), BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(Blocks.BIG_DRIPLEAF.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, p_194967_)))), Direction.UP, BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, true)).placed();
    }
    private static PlacedFeature makeSmallDripleaf() {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.EAST), 1).add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.WEST), 1).add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.NORTH), 1).add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.SOUTH), 1)))).placed();
    }
    private static PlacedFeature makeBambooAmethyst() {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BAMBOO.defaultBlockState(), 2).add(Blocks.BAMBOO_SAPLING.defaultBlockState(), 1).add(Blocks.DEAD_BUSH.defaultBlockState(), 1).add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 1).add(Blocks.GRASS.defaultBlockState(), 8)))).placed();
    }
    private static PlacedFeature makeRootedDirt() {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SMALL_DRIPLEAF.defaultBlockState(), 5).add(Blocks.BAMBOO_SAPLING.defaultBlockState(), 2).add(Blocks.PUMPKIN.defaultBlockState(), 2).add(Blocks.GRASS.defaultBlockState(), 28)))).placed();
    }
    private static PlacedFeature makeGrassyIce() {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 5).add(Blocks.AMETHYST_CLUSTER.defaultBlockState(), 2)))).placed();
    }
    private static PlacedFeature makeIceStacks() {
        return Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SMOOTH_BASALT.defaultBlockState(), 12).add(Blocks.BASALT.defaultBlockState(), 1)))).placed();
    }
    private static PlacedFeature makeDrippingBamboo() {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.EAST), 1).add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.WEST), 2).add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.NORTH), 1).add(Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.SOUTH), 2).add(Blocks.BAMBOO.defaultBlockState(), 2).add(Blocks.SUGAR_CANE.defaultBlockState(), 1).add(Blocks.MELON.defaultBlockState(), 2)))).placed();
    }
    private static PlacedFeature makeSandyAmethyst() {
        return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AMETHYST_CLUSTER.defaultBlockState(), 1).add(Blocks.LARGE_AMETHYST_BUD.defaultBlockState(), 1).add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 3).add(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(), 2)))).placed();
    }
}
