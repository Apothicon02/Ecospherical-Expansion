package com.Apothic0n.EcosphericalExpansion.core.events;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.core.objects.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

import static com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks.pileBlocks;
import static com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks.wallBlocks;

@Mod.EventBusSubscriber(modid = EcosphericalExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.accept(EcoItems.GLOWING_AMETHYST.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(EcoItems.AQUATIC_LICHEN.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(EcoItems.DRY_GRASS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            List<List<RegistryObject<Item>>> buildingBlockItems = List.of(EcoItems.wallItems, EcoItems.stairItems, EcoItems.slabItems, EcoItems.pileItems);
            for (int i = 0; i < buildingBlockItems.size(); i++) {
                List<RegistryObject<Item>> blockItemTypeList = buildingBlockItems.get(i);
                for (int o = 0; o < blockItemTypeList.size(); o++) {
                    event.accept(blockItemTypeList.get(o).get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerSpriteSet(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(EcoParticleTypes.OAK_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new LeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.DARK_OAK_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new LeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.BIRCH_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new BirchLeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.SPRUCE_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new SpruceLeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.JUNGLE_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new LeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.ACACIA_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new LeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.MANGROVE_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new LeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.AZALEA_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new AzaleaLeavesParticle(level, x, y, z, spriteSet);
            };
        });
        event.registerSpriteSet(EcoParticleTypes.FLOWERING_AZALEA_LEAVES.get(), (spriteSet) -> {
            return (particleType, level, x, y, z, p_277222_, p_277223_, p_277224_) -> {
                return new AzaleaLeavesParticle(level, x, y, z, spriteSet);
            };
        });
    }

    private static final PerlinSimplexNoise SATURATION_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(2345L)), ImmutableList.of(0));
    private static final PerlinSimplexNoise BRIGHTNESS_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(5432L)), ImmutableList.of(0));

    @SubscribeEvent
    public static void onBlockColors(RegisterColorHandlersEvent.Block event) {
        Block spruceLeaves = Blocks.SPRUCE_LEAVES;
        Block birchLeaves = Blocks.BIRCH_LEAVES;
        Block oakLeaves = Blocks.OAK_LEAVES;
        Block jungleLeaves = Blocks.JUNGLE_LEAVES;
        Block acaciaLeaves = Blocks.ACACIA_LEAVES;
        Block darkOakLeaves = Blocks.DARK_OAK_LEAVES;
        Block mangroveLeaves = Blocks.MANGROVE_LEAVES;
        Block sprucePile = Blocks.SPRUCE_LEAVES;
        Block birchPile = Blocks.BIRCH_LEAVES;
        Block oakPile = Blocks.OAK_LEAVES;
        Block junglePile = Blocks.JUNGLE_LEAVES;
        Block acaciaPile = Blocks.ACACIA_LEAVES;
        Block darkOakPile = Blocks.DARK_OAK_LEAVES;
        Block mangrovePile = Blocks.MANGROVE_LEAVES;

        for (int i = 0; i < wallBlocks.size(); i++) {
            Map<Block, RegistryObject<Block>> map = wallBlocks.get(i);
            if (map.containsKey(Blocks.SPRUCE_LEAVES)) {
                spruceLeaves = map.get(Blocks.SPRUCE_LEAVES).get();
            } else if (map.containsKey(Blocks.BIRCH_LEAVES)) {
                birchLeaves = map.get(Blocks.BIRCH_LEAVES).get();
            } else if (map.containsKey(Blocks.OAK_LEAVES)) {
                oakLeaves = map.get(Blocks.OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.JUNGLE_LEAVES)) {
                jungleLeaves = map.get(Blocks.JUNGLE_LEAVES).get();
            } else if (map.containsKey(Blocks.ACACIA_LEAVES)) {
                acaciaLeaves = map.get(Blocks.ACACIA_LEAVES).get();
            } else if (map.containsKey(Blocks.DARK_OAK_LEAVES)) {
                darkOakLeaves = map.get(Blocks.DARK_OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.MANGROVE_LEAVES)) {
                mangroveLeaves = map.get(Blocks.MANGROVE_LEAVES).get();
            }
        }
        for (int i = 0; i < pileBlocks.size(); i++) {
            Map<Block, RegistryObject<Block>> map = pileBlocks.get(i);
            if (map.containsKey(Blocks.SPRUCE_LEAVES)) {
                sprucePile = map.get(Blocks.SPRUCE_LEAVES).get();
            } else if (map.containsKey(Blocks.BIRCH_LEAVES)) {
                birchPile = map.get(Blocks.BIRCH_LEAVES).get();
            } else if (map.containsKey(Blocks.OAK_LEAVES)) {
                oakPile = map.get(Blocks.OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.JUNGLE_LEAVES)) {
                junglePile = map.get(Blocks.JUNGLE_LEAVES).get();
            } else if (map.containsKey(Blocks.ACACIA_LEAVES)) {
                acaciaPile = map.get(Blocks.ACACIA_LEAVES).get();
            } else if (map.containsKey(Blocks.DARK_OAK_LEAVES)) {
                darkOakPile = map.get(Blocks.DARK_OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.MANGROVE_LEAVES)) {
                mangrovePile = map.get(Blocks.MANGROVE_LEAVES).get();
            }
        }

        event.register((p_92636_, p_92637_, p_92638_, p_92639_) -> {
            return FoliageColor.getEvergreenColor();
        }, spruceLeaves, sprucePile);
        event.register((p_92631_, p_92632_, p_92633_, p_92634_) -> {
            return FoliageColor.getBirchColor();
        }, birchLeaves, birchPile);
        event.register((p_92626_, p_92627_, p_92628_, p_92629_) -> {
            return p_92627_ != null && p_92628_ != null ? BiomeColors.getAverageFoliageColor(p_92627_, p_92628_) : FoliageColor.getDefaultColor();
        }, oakLeaves, oakPile, jungleLeaves, junglePile, acaciaLeaves, acaciaPile, darkOakLeaves, darkOakPile, mangroveLeaves, mangrovePile);

        event.register((blockState, blockAndTintGetter, blockPos, tint) -> {
                    if (blockPos != null) {
                        int x = blockPos.getX();
                        int z = blockPos.getZ();
                        int color = -328966;
                        double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.077, z * 0.09, false) * 0.33, -0.03, 0.03) + 1;
                        double brighten = Mth.clamp(BRIGHTNESS_NOISE.getValue(x * 0.05, z * 0.01, false) * 0.11, -0.1, 0.1);
                        float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255) / 255;
                        float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255) / 255;
                        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255) / 255;
                        float gray = (float) ((red + green + blue) / (3 + brighten));
                        if (Minecraft.getInstance().level.getBiome(blockPos).toString().contains("himalayan")) {
                            return -9729;
                        }
                        return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                                (int) (Mth.clamp((blue + (gray - blue)) * saturate, 0, 1) * 255),
                                (int) (Mth.clamp((green + (gray - green)) * saturate, 0, 1) * 255),
                                (int) (Mth.clamp((red + (gray - red)) * saturate, 0, 1) * 255));
                    } else {
                        return -328966;
                    }
                },
                Blocks.SAND);

        event.register((blockState, blockAndTintGetter, blockPos, tint) -> {
                    int color = blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter, blockPos) : GrassColor.getDefaultColor();
                    if (blockPos != null) {
                        int x = blockPos.getX();
                        int z = blockPos.getZ();
                        double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.66, z * 0.6, false) * 0.1, -0.2, 0.22) + 0.9;
                        float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255) / 255;
                        float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255) / 255;
                        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255) / 255;
                        return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                                (int) (Mth.clamp(blue * saturate, 0, 1) * 255),
                                (int) (Mth.clamp(green * saturate, 0, 1) * 255),
                                (int) (Mth.clamp(red * saturate, 0, 1) * 255));
                    }
                    return color;
                },
                EcoBlocks.AQUATIC_LICHEN.get());
        
        event.register((blockState, blockAndTintGetter, blockPos, tint) -> {
            if (blockPos != null) {
                int x = blockPos.getX();
                int z = blockPos.getZ();
                int color = -328966;
                double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.077, z * 0.09, false) * 0.33, -0.03, 0.03) + 1;
                double brighten = Mth.clamp(BRIGHTNESS_NOISE.getValue(x * 0.05, z * 0.01, false) * 0.55, -0.5, 0.5) + 0.75;
                float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255) / 255;
                float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255) / 255;
                float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255) / 255;
                float gray = (float) ((red + green + blue) / (3 + brighten));
                return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                        (int) (Mth.clamp((blue + (gray - blue)) * saturate, 0, 1) * 255),
                        (int) (Mth.clamp((green + (gray - green)) * saturate, 0, 1) * 255),
                        (int) (Mth.clamp((red + (gray - red)) * saturate, 0, 1) * 255));
            } else {
                return -328966;
            }
        },
                Blocks.NETHERRACK, Blocks.GRAVEL, Blocks.CLAY, Blocks.TUFF, Blocks.CALCITE,
                Blocks.ANDESITE, Blocks.ANDESITE_SLAB, Blocks.ANDESITE_STAIRS, Blocks.ANDESITE_WALL,
                Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_ANDESITE_STAIRS,
                Blocks.SMOOTH_BASALT, Blocks.BASALT, Blocks.POLISHED_BASALT,
                Blocks.GRANITE, Blocks.GRANITE_SLAB, Blocks.GRANITE_STAIRS, Blocks.GRANITE_WALL,
                Blocks.POLISHED_GRANITE, Blocks.POLISHED_GRANITE_SLAB, Blocks.POLISHED_GRANITE_STAIRS,
                Blocks.DIORITE, Blocks.DIORITE_SLAB, Blocks.DIORITE_STAIRS,
                Blocks.POLISHED_DIORITE, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_DIORITE_STAIRS,
                Blocks.COBBLESTONE, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_WALL,
                Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_WALL,
                Blocks.STONE, Blocks.STONE_STAIRS, Blocks.STONE_SLAB,
                Blocks.STONE_BRICKS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL, Blocks.CRACKED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS,
                Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL, Blocks.INFESTED_MOSSY_STONE_BRICKS,
                Blocks.COAL_ORE, Blocks.COPPER_ORE, Blocks.IRON_ORE, Blocks.LAPIS_ORE, Blocks.REDSTONE_ORE, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE,
                Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_WALL,
                Blocks.DEEPSLATE, Blocks.INFESTED_DEEPSLATE,
                Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_WALL, Blocks.CRACKED_DEEPSLATE_BRICKS,
                Blocks.DEEPSLATE_TILES, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_WALL, Blocks.CRACKED_DEEPSLATE_TILES,
                Blocks.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE_WALL,
                Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_LAPIS_ORE, Blocks.DEEPSLATE_REDSTONE_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_EMERALD_ORE);

        event.register((blockState, blockAndTintGetter, blockPos, tint) -> {
                    if (blockPos != null) {
                        int x = blockPos.getX();
                        int z = blockPos.getZ();
                        int color = -328966;
                        double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.077, z * 0.09, false) * 0.33, -0.03, 0.03) + 1;
                        double brighten = Mth.clamp(BRIGHTNESS_NOISE.getValue(x * 0.05, z * 0.01, false) * 0.11, -0.1, 0.1);
                        float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255) / 255;
                        float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255) / 255;
                        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255) / 255;
                        float gray = (float) ((red + green + blue) / (3 + brighten));
                        return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                                (int) (Mth.clamp((blue + (gray - blue)) * saturate, 0, 1) * 255),
                                (int) (Mth.clamp((green + (gray - green)) * saturate, 0, 1) * 255),
                                (int) (Mth.clamp((red + (gray - red)) * saturate, 0, 1) * 255));
                    } else {
                        return -328966;
                    }
                },
                Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.POWDER_SNOW);

        event.register((blockState, blockAndTintGetter, blockPos, tint) -> {
                    if (blockPos != null) {
                        int x = blockPos.getX();
                        int z = blockPos.getZ();
                        int color = -328966;
                        double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.33, z * 0.3, false) * 0.3, -0.6, 0.66) + 0.9;
                        double brighten = Mth.clamp(BRIGHTNESS_NOISE.getValue(x * 0.05, z * 0.01, false) * 0.3, -0.66, 0.66);
                        float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255) / 255;
                        float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255) / 255;
                        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255) / 255;
                        float gray = (float) ((red + green + blue) / (3 + brighten));
                        return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                                (int) (Mth.clamp((blue + (gray - blue)) * saturate, 0, 1) * 255),
                                (int) (Mth.clamp((green + (gray - green)) * saturate, 0, 1) * 255),
                                (int) (Mth.clamp((red + (gray - red)) * saturate, 0, 1) * 255));
                    } else {
                        return -328966;
                    }
                },
                Blocks.MUD, Blocks.PACKED_MUD, Blocks.MUD_BRICKS, Blocks.MUD_BRICK_STAIRS, Blocks.MUD_BRICK_SLAB, Blocks.MUD_BRICK_WALL,
                Blocks.MANGROVE_ROOTS);

        event.register((blockState, blockAndTintGetter, blockPos, tint) -> {
                    if (blockPos != null && Minecraft.getInstance().level != null) {
                        int x = blockPos.getX();
                        int z = blockPos.getZ();
                        int color = -328966;
                        int maxHeight = Minecraft.getInstance().level.getMaxBuildHeight();
                        int midHeight = maxHeight/2;
                        int minHeight = Minecraft.getInstance().level.getMinBuildHeight();
                        int offset = 0;
                        if (minHeight < 0) {
                            offset = -(minHeight);
                            maxHeight = maxHeight + offset;
                            midHeight = (maxHeight/2);
                        }
                        BlockPos offsetPos = blockPos.above(offset);
                        double temperature = 0;
                        if (offsetPos.getY() < midHeight) {
                            temperature = (offsetPos.getY()-midHeight) * 0.005;
                        } else {
                            temperature = Mth.clamp(offsetPos.getY()-midHeight * 0.005, -1, 0.05);
                        }
                        double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.1, z * 0.1, false) * 0.33, -0.03, 0.03)+1.1;
                        double brighten = Mth.clamp(BRIGHTNESS_NOISE.getValue(x * 0.025, z * 0.025, false) * 0.3, -0.33, 0.33);
                        float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255)/255;
                        float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255)/255;
                        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255)/255;
                        float gray = (float) ((red + green + blue) / (3 + brighten));
                        return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                                (int) (Mth.clamp(((blue + (gray - blue)) * saturate) + temperature, 0, 1) * 255),
                                (int) (Mth.clamp(((green + (gray - green)) * saturate) + temperature, 0, 1) * 255),
                                (int) (Mth.clamp(((red + (gray - red)) * saturate) - temperature, 0, 1) * 255));
                    } else {
                        return -328966;
                    }
                },
                Blocks.END_STONE, Blocks.END_STONE_BRICKS, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICK_WALL);
    }

    @SubscribeEvent
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {
        Block spruceLeaves = Blocks.SPRUCE_LEAVES;
        Block birchLeaves = Blocks.BIRCH_LEAVES;
        Block oakLeaves = Blocks.OAK_LEAVES;
        Block jungleLeaves = Blocks.JUNGLE_LEAVES;
        Block acaciaLeaves = Blocks.ACACIA_LEAVES;
        Block darkOakLeaves = Blocks.DARK_OAK_LEAVES;
        Block mangroveLeaves = Blocks.MANGROVE_LEAVES;
        Block sprucePile = Blocks.SPRUCE_LEAVES;
        Block birchPile = Blocks.BIRCH_LEAVES;
        Block oakPile = Blocks.OAK_LEAVES;
        Block junglePile = Blocks.JUNGLE_LEAVES;
        Block acaciaPile = Blocks.ACACIA_LEAVES;
        Block darkOakPile = Blocks.DARK_OAK_LEAVES;
        Block mangrovePile = Blocks.MANGROVE_LEAVES;
        for (int i = 0; i < wallBlocks.size(); i++) {
            Map<Block, RegistryObject<Block>> map = wallBlocks.get(i);
            if (map.containsKey(Blocks.SPRUCE_LEAVES)) {
                spruceLeaves = map.get(Blocks.SPRUCE_LEAVES).get();
            } else if (map.containsKey(Blocks.BIRCH_LEAVES)) {
                birchLeaves = map.get(Blocks.BIRCH_LEAVES).get();
            } else if (map.containsKey(Blocks.OAK_LEAVES)) {
                oakLeaves = map.get(Blocks.OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.JUNGLE_LEAVES)) {
                jungleLeaves = map.get(Blocks.JUNGLE_LEAVES).get();
            } else if (map.containsKey(Blocks.ACACIA_LEAVES)) {
                acaciaLeaves = map.get(Blocks.ACACIA_LEAVES).get();
            } else if (map.containsKey(Blocks.DARK_OAK_LEAVES)) {
                darkOakLeaves = map.get(Blocks.DARK_OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.MANGROVE_LEAVES)) {
                mangroveLeaves = map.get(Blocks.MANGROVE_LEAVES).get();
            }
        }
        for (int i = 0; i < pileBlocks.size(); i++) {
            Map<Block, RegistryObject<Block>> map = pileBlocks.get(i);
            if (map.containsKey(Blocks.SPRUCE_LEAVES)) {
                sprucePile = map.get(Blocks.SPRUCE_LEAVES).get();
            } else if (map.containsKey(Blocks.BIRCH_LEAVES)) {
                birchPile = map.get(Blocks.BIRCH_LEAVES).get();
            } else if (map.containsKey(Blocks.OAK_LEAVES)) {
                oakPile = map.get(Blocks.OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.JUNGLE_LEAVES)) {
                junglePile = map.get(Blocks.JUNGLE_LEAVES).get();
            } else if (map.containsKey(Blocks.ACACIA_LEAVES)) {
                acaciaPile = map.get(Blocks.ACACIA_LEAVES).get();
            } else if (map.containsKey(Blocks.DARK_OAK_LEAVES)) {
                darkOakPile = map.get(Blocks.DARK_OAK_LEAVES).get();
            } else if (map.containsKey(Blocks.MANGROVE_LEAVES)) {
                mangrovePile = map.get(Blocks.MANGROVE_LEAVES).get();
            }
        }
        event.register((p_92636_, p_92637_) -> {
            return FoliageColor.getEvergreenColor();
        }, spruceLeaves, sprucePile);
        event.register((p_92631_, p_92632_) -> {
            return FoliageColor.getBirchColor();
        }, birchLeaves, birchPile);
        event.register((p_92626_, p_92627_) -> {
            return Minecraft.getInstance().level != null && Minecraft.getInstance().player != null ? BiomeColors.getAverageFoliageColor(Minecraft.getInstance().level, Minecraft.getInstance().player.blockPosition()) : FoliageColor.getDefaultColor();
        }, oakLeaves, oakPile, jungleLeaves, junglePile, acaciaLeaves, acaciaPile, darkOakLeaves, darkOakPile, mangroveLeaves, mangrovePile);

        event.register((itemStack, tint) -> {
                    if (Minecraft.getInstance().level != null) {
                        BlockPos blockPos = Minecraft.getInstance().player.blockPosition();
                        int color = Minecraft.getInstance().level != null && Minecraft.getInstance().player != null ? BiomeColors.getAverageFoliageColor(Minecraft.getInstance().level, Minecraft.getInstance().player.blockPosition()) : FoliageColor.getDefaultColor();
                        int x = blockPos.getX();
                        int z = blockPos.getZ();
                        double saturate = Mth.clamp(SATURATION_NOISE.getValue(x * 0.66, z * 0.6, false) * 0.1, -0.2, 0.22) + 0.9;
                        float red = (float) Mth.clamp(FastColor.ABGR32.red(color), 1, 255) / 255;
                        float green = (float) Mth.clamp(FastColor.ABGR32.green(color), 1, 255) / 255;
                        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(color), 1, 255) / 255;
                        return FastColor.ABGR32.color(FastColor.ABGR32.alpha(color),
                                (int) (Mth.clamp(blue * saturate, 0, 1) * 255),
                                (int) (Mth.clamp(green * saturate, 0, 1) * 255),
                                (int) (Mth.clamp(red * saturate, 0, 1) * 255));
                    } else {
                        return FastColor.ABGR32.color(1, 0, 179, 89);
                    }
                },
                EcoBlocks.AQUATIC_LICHEN.get());
    }
}