package com.Apothic0n.EcosphericalExpansion.api.biome.features;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;

import java.util.List;
import java.util.OptionalInt;

public class ECOTreeFeatures {
    public static final ConfiguredFeature<TreeConfiguration, ?> BRANCHING_JUNGLE_TREE = FeatureUtils.register("branching_jungle_tree", Feature.TREE.configured(createBranchingJungleTree().decorators(ImmutableList.of(new CocoaDecorator(0.2F), TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> MEGA_AZALEA_TREE = FeatureUtils.register("mega_azalea_tree", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new BendingTrunkPlacer(12, 5, 4, 7, UniformInt.of(1, 2)), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3).add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 0, 1))).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).forceDirt().build()));
    public static final ConfiguredFeature<?, ?> AZALEA_BUSH = FeatureUtils.register("azalea_bush", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new BendingTrunkPlacer(2, 1, 0, 2, UniformInt.of(1, 2)), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3).add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 0, 1))).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).forceDirt().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> TWISTED_OAK = FeatureUtils.register("twisted_oak", Feature.TREE.configured(createTwistedOak().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TOWERING_OAK = FeatureUtils.register("towering_oak", Feature.TREE.configured(createToweringOak().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BRANCHING_OAK = FeatureUtils.register("branching_oak", Feature.TREE.configured(createBranchingOak().decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> TOWERING_SPRUCE = FeatureUtils.register("towering_spruce", Feature.TREE.configured(createToweringSpruce().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> TILTED_DARK_OAK = FeatureUtils.register("tilted_dark_oak", Feature.TREE.configured(createTiltedDarkOak().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SHORT_DARK_OAK = FeatureUtils.register("short_dark_oak", Feature.TREE.configured(createDarkOak(5).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> NORMAL_DARK_OAK = FeatureUtils.register("normal_dark_oak", Feature.TREE.configured(createDarkOak(7).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_TALL_BIRCH = FeatureUtils.register("big_tall_birch", Feature.TREE.configured(createStraightBirch(14).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> NORMAL_TALL_BIRCH = FeatureUtils.register("normal_tall_birch", Feature.TREE.configured(createStraightBirch(10).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TILTED_TALL_BIRCH = FeatureUtils.register("tilted_tall_birch", Feature.TREE.configured(createSlantedBirch(7).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> MEGA_ACACIA = FeatureUtils.register("mega_acacia", Feature.TREE.configured(createAcacia(10).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> MULTI_ACACIA = FeatureUtils.register("multi_acacia", Feature.TREE.configured(createMultiAcacia(7).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE)).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> MEGA_SWAMP_SMALL = FeatureUtils.register("mega_swamp", Feature.TREE.configured(createSwamp(10).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> MEGA_SWAMP_BIG = FeatureUtils.register("mega_swamp_big", Feature.TREE.configured(createSwamp(17).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> MULTI_SWAMP = FeatureUtils.register("multi_swamp", Feature.TREE.configured(createMultiSwamp(7).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).build()));

    public static final ConfiguredFeature<?, ?> TILTED_TALL_MUSHROOM = FeatureUtils.register("tilted_tall_mushroom", Feature.HUGE_BROWN_MUSHROOM.configured(new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.UP, true).setValue(HugeMushroomBlock.DOWN, false)), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN,false)), 3)));
    public static final ConfiguredFeature<?, ?> NORMAL_TALL_MUSHROOM = FeatureUtils.register("normal_tall_mushroom", Feature.HUGE_RED_MUSHROOM.configured(new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)), 2)));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_TALL_MUSHROOM = FeatureUtils.register("big_tall_mushroom", Feature.TREE.configured(createMultiMushroom(14).decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE)).build()));

    public static final ConfiguredFeature<BlockPileConfiguration, ?> COPPER_DEEPSLATE_STACK = FeatureUtils.register("copper_deepslate_stack", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DEEPSLATE_COPPER_ORE.defaultBlockState(), 3).add(Blocks.COBBLED_DEEPSLATE.defaultBlockState(), 7)))));
    public static final ConfiguredFeature<BlockPileConfiguration, ?> DEEPSLATE_BLOCK_STACK = FeatureUtils.register("deepslate_block_stack", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DEEPSLATE_COAL_ORE.defaultBlockState(), 7).add(Blocks.COBBLED_DEEPSLATE.defaultBlockState(), 1)))));
    public static final ConfiguredFeature<BlockPileConfiguration, ?> MISC_AMETHSYT_GEODE = FeatureUtils.register("misc_amethyst_geode", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BASALT.defaultBlockState(), 2).add(Blocks.SMOOTH_BASALT.defaultBlockState(), 7)))));
    public static final ConfiguredFeature<BlockPileConfiguration, ?> MISC_AMETHSYT_CLUSTER = FeatureUtils.register("misc_amethyst_cluster", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BUDDING_AMETHYST.defaultBlockState(), 1).add(Blocks.AMETHYST_BLOCK.defaultBlockState(), 2).add(Blocks.AMETHYST_CLUSTER.defaultBlockState(), 5).add(Blocks.LARGE_AMETHYST_BUD.defaultBlockState(), 3).add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 1)))));
    public static final ConfiguredFeature<BlockPileConfiguration, ?> MISC_AMETHSYT_LARGE = FeatureUtils.register("misc_amethyst_large", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AMETHYST_BLOCK.defaultBlockState(), 1).add(Blocks.AMETHYST_CLUSTER.defaultBlockState(), 3).add(Blocks.LARGE_AMETHYST_BUD.defaultBlockState(), 9).add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 2)))));
    public static final ConfiguredFeature<BlockPileConfiguration, ?> MISC_AMETHSYT_NORMAL = FeatureUtils.register("misc_amethyst_normal", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.LARGE_AMETHYST_BUD.defaultBlockState(), 1).add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 5).add(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(), 2)))));
    public static final ConfiguredFeature<BlockPileConfiguration, ?> MISC_AMETHSYT_SMALL = FeatureUtils.register("misc_amethyst_small", Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 2).add(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(), 7)))));

    private static TreeConfiguration.TreeConfigurationBuilder createMultiMushroom(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.MUSHROOM_STEM),
                new ForkingTrunkPlacer(height, 1, 1),
                BlockStateProvider.simple(Blocks.RED_MUSHROOM_BLOCK),
                new DarkOakFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(24, 5, 3, 4, 5, OptionalInt.of(0)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createSwamp(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG),
                new FancyTrunkPlacer(height, 1, 0),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 2).add(Blocks.OAK_LEAVES.defaultBlockState(), 7)),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(0)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createMultiSwamp(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(height, 1, 1),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 2).add(Blocks.OAK_LEAVES.defaultBlockState(), 5)),
                new DarkOakFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(24, 5, 3, 4, 5, OptionalInt.of(0)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createAcacia(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.ACACIA_LOG),
                new ForkingTrunkPlacer(height, 1, 0),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ACACIA_LEAVES.defaultBlockState(), 8).add(Blocks.OAK_LEAVES.defaultBlockState(), 3)),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(0)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createMultiAcacia(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.ACACIA_LOG),
                new ForkingTrunkPlacer(height, 1, 1),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ACACIA_LEAVES.defaultBlockState(), 8).add(Blocks.OAK_LEAVES.defaultBlockState(), 2)),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(24, 5, 3, 4, 5, OptionalInt.of(0)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createTwistedOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_WOOD),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createToweringSpruce() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                new StraightTrunkPlacer(11, 6, 1), BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(6)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createToweringOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG),
                new BendingTrunkPlacer(5, 3, 2, 3, UniformInt.of(1, 2)),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 7).add(Blocks.OAK_LEAVES.defaultBlockState(), 2)),
                new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(4)),
                new TwoLayersFeatureSize(6, 3, 2, OptionalInt.of(4)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createBranchingOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_WOOD),
                new FancyTrunkPlacer(18, 23, 1),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 2).add(Blocks.OAK_LEAVES.defaultBlockState(), 7)),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createTiltedDarkOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(13, 6, 15),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 7).add(Blocks.OAK_LEAVES.defaultBlockState(), 3)),
                new DarkOakFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createDarkOak(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(height, 2, 3),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.DARK_OAK_LEAVES.defaultBlockState(), 7).add(Blocks.OAK_LEAVES.defaultBlockState(), 2)),
                new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 1, 1, 2, OptionalInt.empty())));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createBranchingJungleTree() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                new FancyTrunkPlacer(8, 20, 2), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBirch(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new StraightTrunkPlacer(height, 5, 2),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BIRCH_LEAVES.defaultBlockState(), 9)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createSlantedBirch(int height) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(height, 8, 5),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 11).add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(6, 3, 2, OptionalInt.of(4)))).ignoreVines();
    }
}
