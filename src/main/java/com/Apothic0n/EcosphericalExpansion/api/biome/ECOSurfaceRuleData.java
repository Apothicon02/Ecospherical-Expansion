package com.Apothic0n.EcosphericalExpansion.api.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ECOSurfaceRuleData {
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource AMETHYST_BLOCK = makeStateRule(Blocks.AMETHYST_BLOCK);
    private static final SurfaceRules.RuleSource SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);

    private static final SurfaceRules.RuleSource MOSS_BLOCK = makeStateRule(Blocks.MOSS_BLOCK);
    private static final SurfaceRules.RuleSource ROOTED_DIRT = makeStateRule(Blocks.ROOTED_DIRT);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource GRANITE = makeStateRule(Blocks.GRANITE);
    private static final SurfaceRules.RuleSource DIORITE = makeStateRule(Blocks.DIORITE);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DRIPSTONE_BLOCK = makeStateRule(Blocks.DRIPSTONE_BLOCK);
    private static final SurfaceRules.RuleSource TUFF = makeStateRule(Blocks.TUFF);

    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final SurfaceRules.RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);

    private static final SurfaceRules.RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final SurfaceRules.RuleSource RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final SurfaceRules.RuleSource ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
    private static final SurfaceRules.RuleSource YELLOW_TERRACOTTA = makeStateRule(Blocks.YELLOW_TERRACOTTA);
    private static final SurfaceRules.RuleSource CYAN_TERRACOTTA = makeStateRule(Blocks.CYAN_TERRACOTTA);
    private static final SurfaceRules.RuleSource BROWN_TERRACOTTA = makeStateRule(Blocks.BROWN_TERRACOTTA);
    private static final SurfaceRules.RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final SurfaceRules.RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);


    protected static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource snowyGlacialCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(-63), VerticalAnchor.absolute(56)), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("packed_ice", VerticalAnchor.absolute(50), VerticalAnchor.absolute(68)), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(67), 5), GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("snow_block", VerticalAnchor.absolute(70), VerticalAnchor.absolute(76)), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(72), 5), GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(77), 4), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(81), 7), GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(88), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 2), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(98), 5), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(103), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(108), 5), BLUE_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(110), 2), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(115), 5), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(120), 3), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(123), 5), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(128), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(133), 8), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(141), 5), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(146), 6), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("lowercalcite", VerticalAnchor.absolute(149), VerticalAnchor.absolute(170)), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("tuff", VerticalAnchor.absolute(160), VerticalAnchor.absolute(300)), TUFF),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("uppercalcite", VerticalAnchor.absolute(235), VerticalAnchor.absolute(304)), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(304), 15), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.steep(), CALCITE));

        SurfaceRules.RuleSource frozenGlacialCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(-63), VerticalAnchor.absolute(56)), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("packed_ice", VerticalAnchor.absolute(50), VerticalAnchor.absolute(68)), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(67), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("snow_block", VerticalAnchor.absolute(70), VerticalAnchor.absolute(76)), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(72), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(77), 4), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(81), 7), BLUE_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(88), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 2), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(98), 5), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(103), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(108), 5), BLUE_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(110), 2), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(115), 5), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(120), 3), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(123), 5), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(128), 5), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(133), 8), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(141), 5), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(146), 6), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("lowercalcite", VerticalAnchor.absolute(149), VerticalAnchor.absolute(170)), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("tuff", VerticalAnchor.absolute(160), VerticalAnchor.absolute(300)), TUFF),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("uppercalcite", VerticalAnchor.absolute(235), VerticalAnchor.absolute(304)), CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(304), 15), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.steep(), CALCITE));

        SurfaceRules.RuleSource grassyTerracottaMesa = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(-63), VerticalAnchor.absolute(56)), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("terracotta", VerticalAnchor.absolute(50), VerticalAnchor.absolute(64)), BROWN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 5), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(67), 3), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(70), 2), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(72), 4), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(76), 2), YELLOW_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(78), 6), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(84), 4), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(88), 7), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(95), 8), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(103), 3), YELLOW_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(106), 7), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(113), 7), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(120), 3), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(123), 5), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(128), 5), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(133), 8), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(141), 5), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(146), 6), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("lower_terracotta", VerticalAnchor.absolute(149), VerticalAnchor.absolute(170)), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("rooted_dirt", VerticalAnchor.absolute(160), VerticalAnchor.absolute(300)), ROOTED_DIRT),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("upper_red_terracotta", VerticalAnchor.absolute(235), VerticalAnchor.absolute(304)), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(304), 15), ORANGE_TERRACOTTA));

        SurfaceRules.RuleSource sandyTerracottaMesa = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, RED_SAND),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(-63), VerticalAnchor.absolute(56)), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("terracotta", VerticalAnchor.absolute(50), VerticalAnchor.absolute(60)), BROWN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("terracotta", VerticalAnchor.absolute(59), VerticalAnchor.absolute(64)), MOSS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 5), RED_SAND),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(67), 3), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(70), 2), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(72), 4), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(76), 2), YELLOW_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(78), 6), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(84), 4), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(88), 7), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(95), 8), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(103), 3), YELLOW_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(106), 7), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(113), 7), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(120), 3), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(123), 5), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(128), 5), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(133), 8), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(141), 5), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(146), 6), CYAN_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("lower_terracotta", VerticalAnchor.absolute(149), VerticalAnchor.absolute(170)), TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("rooted_dirt", VerticalAnchor.absolute(160), VerticalAnchor.absolute(300)), ROOTED_DIRT),
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("upper_red_terracotta", VerticalAnchor.absolute(235), VerticalAnchor.absolute(304)), RED_TERRACOTTA),
                SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(304), 15), ORANGE_TERRACOTTA));

        SurfaceRules.RuleSource deepslateCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SMOOTH_BASALT),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.steep(), DEEPSLATE));

        SurfaceRules.RuleSource calciteCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CALCITE),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, AMETHYST_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.steep(), CALCITE));

        SurfaceRules.RuleSource mossyCalciteCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOSS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIORITE),
                SurfaceRules.ifTrue(SurfaceRules.steep(), CALCITE));

        SurfaceRules.RuleSource grassyDripstoneCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRIPSTONE_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.steep(), TUFF));

        SurfaceRules.RuleSource grassyGraniteCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GRANITE),
                SurfaceRules.ifTrue(SurfaceRules.steep(), TUFF));

        SurfaceRules.RuleSource rootedCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ROOTED_DIRT),
                SurfaceRules.ifTrue(SurfaceRules.steep(), TUFF));

        SurfaceRules.RuleSource myceliumBlackstoneCliffs = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(-63), VerticalAnchor.absolute(300)), DEEPSLATE),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MYCELIUM),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRIPSTONE_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.steep(), BLACKSTONE));

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.LUSH_OAK), grassyGraniteCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.LUSH_JUNGLE), grassyDripstoneCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.ENRICHED_ROOFED_FOREST), rootedCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.FROZEN_ROOFED_FOREST), snowyGlacialCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.LUSH_BIRCH), mossyCalciteCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.GLACIAL_PLAINS), frozenGlacialCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.MEGA_SAVANNA), grassyTerracottaMesa),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.MEGA_SWAMP), grassyDripstoneCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.MUSHROOM_GROVE), myceliumBlackstoneCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.DEEPSLATE_CLIFFS), deepslateCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.CALCITE_CLIFFS), calciteCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.MUSHROOM_PLAINS), grassyGraniteCliffs),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ECOBiomeCreator.LUSH_DESERT), sandyTerracottaMesa)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double p_194809_) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, p_194809_ / 8.25D, Double.MAX_VALUE);
    }
}
