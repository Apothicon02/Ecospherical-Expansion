package com.Apothic0n.EcosphericalExpansion.core.events;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

@Mod.EventBusSubscriber(modid = EcosphericalExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonModEvents {
    @SubscribeEvent
    public static void entityLoaded(EntityLoadEvent event) {
        Level level = event.level;
        Entity entity = event.entity;
        BlockPos pos = entity.blockPosition();
        if (entity instanceof Player && level.dimension().equals(Level.OVERWORLD)) {
            int y = level.getMaxBuildHeight();
            if (pos.getY() == y && level.getBlockState(pos.below()).is(Blocks.BEDROCK)) {
                pos = pos.below(64);
                generateSquare(level, pos.below(2), Blocks.OAK_WOOD.defaultBlockState());
                generateSquare(level, pos.below(), Blocks.OAK_WOOD.defaultBlockState());
                generateSquare(level, pos, Blocks.AIR.defaultBlockState());
                generateSquare(level, pos.above(), Blocks.AIR.defaultBlockState());
                level.setBlock(pos, Blocks.TORCH.defaultBlockState(), UPDATE_ALL);
                entity.teleportRelative(0, -64, 0);
            } else {
                boolean overVoid = true;
                for (int i = level.getMinBuildHeight() - 1; i < level.getMaxBuildHeight(); i++) {
                    if (!level.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).isAir()) {
                        overVoid = false;
                    }
                }
                if (overVoid) {
                    pos = new BlockPos(pos.getX(), 64, pos.getZ());
                    level.setBlock(pos.below(5), Blocks.CAVE_VINES.defaultBlockState().setValue(BlockStateProperties.BERRIES, true), UPDATE_ALL);
                    generateSquare(level, pos.below(4), Blocks.STONE.defaultBlockState());
                    generateSquare(level, pos.below(3), Blocks.STONE.defaultBlockState());
                    generateSquare(level, pos.below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.north(3).below(3), Blocks.STONE.defaultBlockState());
                    generateSquare(level, pos.north(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.north(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.east(3).below(3), Blocks.STONE.defaultBlockState());
                    generateSquare(level, pos.east(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.east(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.south(3).below(3), Blocks.STONE.defaultBlockState());
                    generateSquare(level, pos.south(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.south(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.west(3).below(3), Blocks.STONE.defaultBlockState());
                    generateSquare(level, pos.west(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.west(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.north(3).east(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.north(3).east(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.north(3).west(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.north(3).west(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.south(3).east(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.south(3).east(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    generateSquare(level, pos.south(3).west(3).below(2), Blocks.DIRT.defaultBlockState());
                    generateSquare(level, pos.south(3).west(3).below(), Blocks.GRASS_BLOCK.defaultBlockState());
                    level.setBlock(pos.north(2).east(3), Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), UPDATE_ALL);
                    level.setBlock(pos.north(3).east(3), Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), UPDATE_ALL);
                    level.setBlock(pos.north(3).east(3).above(), Blocks.MOSS_CARPET.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.north(4).east(3), Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), UPDATE_ALL);
                    level.setBlock(pos.north(5).east(3), Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), UPDATE_ALL);
                    level.setBlock(pos.east(3).south(3), Blocks.OAK_SAPLING.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.south(3).west(3), Blocks.OAK_SAPLING.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.west(3).north(3), Blocks.OAK_SAPLING.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.north(1).east().below(), Blocks.WATER.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.north(1).east().south().below(2), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.north(1).east().west().below(2), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.south(1).west().below(), Blocks.LAVA.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.below(), Blocks.COBBLESTONE.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.north(1).west().below(), Blocks.COBBLESTONE.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.south(1).east().below(), Blocks.COBBLESTONE.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.north(1).below(), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.east(1).below(), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.south(1).below(), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                    level.setBlock(pos.west(1).below(), Blocks.AIR.defaultBlockState(), UPDATE_ALL);
                    entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
                }
            }
        }
    }

    private static void generateSquare(Level level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, UPDATE_ALL);
        level.setBlock(pos.north(), state, UPDATE_ALL);
        level.setBlock(pos.east(), state, UPDATE_ALL);
        level.setBlock(pos.south(), state, UPDATE_ALL);
        level.setBlock(pos.west(), state, UPDATE_ALL);
        level.setBlock(pos.north().east(), state, UPDATE_ALL);
        level.setBlock(pos.south().east(), state, UPDATE_ALL);
        level.setBlock(pos.north().west(), state, UPDATE_ALL);
        level.setBlock(pos.south().west(), state, UPDATE_ALL);
    }

    //@SubscribeEvent
    //public static void biomeLoading(@Nonnull BiomeModifiers event) {
        //if (ModList.get().isLoaded("darkerdepths")) {
        //    event.getGeneration().addFeature(GenerationStep.Decoration.STRONGHOLDS, DDPlacedFeatures.SILVER_ORE);
        //    event.getGeneration().addFeature(GenerationStep.Decoration.STRONGHOLDS, DDPlacedFeatures.MAGMA_ORE);
        //    if (event.getName().toString().equals("eco:submerged_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.GLIMMERING_VINES);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.PETRIFIED_BRANCH);
        //    } else if (event.getName().toString().equals("eco:tropical_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.AMBER);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.GLIMMERING_VINES);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.HUGE_GLOWSHROOM);
        //        final Holder<PlacedFeature> SPARSE_GRIME_SURFACE = BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation("eco", "sparse_grime_surface"), new PlacedFeature(Holder.hackyErase(DDConfiguredFeatures.GRIME_SURFACE), List.of(CountPlacement.of(UniformInt.of(112, 148)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome())));
        //        event.getGeneration().addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, SPARSE_GRIME_SURFACE);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.PETRIFIED_BRANCH);
        //    } else if (event.getName().toString().equals("eco:lush_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.AMBER);
        //        final Holder<PlacedFeature> DENSE_PETRIFIED_BRANCH = BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation("eco", "dense_petrified_branch"), new PlacedFeature(Holder.hackyErase(DDConfiguredFeatures.PETRIFIED_BRANCH), List.of(CountPlacement.of(UniformInt.of(236, 254)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome())));
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DENSE_PETRIFIED_BRANCH);
        //    } else if (event.getName().toString().equals("eco:dripping_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.AMBER);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, DDPlacedFeatures.ARID_SURFACE);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.ARID_BOULDER);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.PETRIFIED_BRANCH);
        //    } else if (event.getName().toString().equals("eco:deep_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, DDPlacedFeatures.SHALE_SURFACE);
        //    } else if (event.getName().toString().equals("eco:molten_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, DDPlacedFeatures.AMBER);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.LAKES, DDPlacedFeatures.MOLTEN_POOL);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.LAKES, DDPlacedFeatures.MOLTEN_SPRING);
        //    }
        //}
        //if (ModList.get().isLoaded("quark")) {
        //    if (event.getName().toString().equals("eco:tropical_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, placed_glow_shrooms);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, placed_glow_extras);
        //    }
        //}
        //if (ModList.get().isLoaded("galosphere")) {
        //    Holder<PlacedFeature> GALOSPHERE_LARGE_CEILING_ALLURITE_PLACED = PlacementUtils.register("large_ceiling_allurite", GConfiguredFeatures.LARGE_ALLURITE_CRYSTAL_CEILING, CountPlacement.of(UniformInt.of(60, 90)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        //    Holder<PlacedFeature> GALOSPHERE_CEILING_ALLURITE_PLACED = PlacementUtils.register("ceiling_allurite", GConfiguredFeatures.ALLURITE_CRYSTAL_CEILING, CountPlacement.of(UniformInt.of(90, 140)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        //    Holder<PlacedFeature> GALOSPHERE_LARGE_FLOOR_ALLURITE_PLACED = PlacementUtils.register("large_floor_allurite", GConfiguredFeatures.LARGE_ALLURITE_CRYSTAL_FLOOR, CountPlacement.of(UniformInt.of(48, 69)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        //    Holder<PlacedFeature> GALOSPHERE_FLOOR_ALLURITE_PLACED = PlacementUtils.register("floor_allurite", GConfiguredFeatures.ALLURITE_CRYSTAL_FLOOR, CountPlacement.of(UniformInt.of(45, 75)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

        //    Holder<PlacedFeature> GALOSPHERE_LARGE_FLOOR_LUMIERE_PLACED = PlacementUtils.register("large_floor_lumiere", GConfiguredFeatures.LARGE_LUMIERE_CRYSTAL_FLOOR, CountPlacement.of(UniformInt.of(6, 20)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        //    Holder<PlacedFeature> GALOSPHERE_FLOOR_LUMIERE_PLACED = PlacementUtils.register("floor_lumiere", GConfiguredFeatures.LUMIERE_CRYSTAL_FLOOR, CountPlacement.of(UniformInt.of(10, 16)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

        //    if (event.getName().toString().equals("eco:frozen_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GALOSPHERE_LARGE_CEILING_ALLURITE_PLACED);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, GALOSPHERE_CEILING_ALLURITE_PLACED);
        //    } else if (event.getName().toString().equals("eco:lush_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GALOSPHERE_LARGE_FLOOR_LUMIERE_PLACED);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, GALOSPHERE_FLOOR_LUMIERE_PLACED);
        //    } else if (event.getName().toString().equals("eco:submerged_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GALOSPHERE_LARGE_CEILING_ALLURITE_PLACED);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, GALOSPHERE_CEILING_ALLURITE_PLACED);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, GALOSPHERE_FLOOR_ALLURITE_PLACED);
        //    } else if (event.getName().toString().equals("eco:glacial_cavity")) {
        //        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GALOSPHERE_LARGE_FLOOR_ALLURITE_PLACED);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, GALOSPHERE_CEILING_ALLURITE_PLACED);
        //        event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, GALOSPHERE_FLOOR_ALLURITE_PLACED);
        //    }
        //}
    //}
}
