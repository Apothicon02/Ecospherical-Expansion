package com.Apothic0n;


import com.Apothic0n.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.api.biome.features.decorators.EcoTreeDecoratorType;
import com.Apothic0n.api.biome.features.foliage_placers.EcoFoliagePlacerType;
import com.Apothic0n.api.biome.features.trunk_placers.EcoTrunkPlacerType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.LevelResource;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class EcosphericalExpansion implements ModInitializer {
    public static final String MODID = "eco";
    @Override
    public void onInitialize() {
        EcoFeatureRegistry.register();
        EcoTrunkPlacerType.init();
        EcoFoliagePlacerType.init();
        EcoTreeDecoratorType.init();


        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            Level level = entity.level();
            BlockPos pos = entity.blockPosition();
            if (entity instanceof Player) {
                if (level.dimension().equals(Level.OVERWORLD)) {
                    boolean hasSpawnPlatformGeneratedBefore = true;
                    final Path hasSpawnPlatformGenerated = Path.of(world.getServer().getWorldPath(LevelResource.LEVEL_DATA_FILE).getParent().toString() + "/hasSpawnPlatformGenerated");
                    Gson gson = new Gson();
                    if (!Files.exists(hasSpawnPlatformGenerated)) {
                        JsonWriter writer = null;
                        try {
                            writer = new JsonWriter(new FileWriter(hasSpawnPlatformGenerated.toString()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        JsonObject defaultData = gson.fromJson("{\"values\":[\"delete\",\"file\",\"to\",\"regenerate\",\"spawn\",\"platform\"]}", JsonObject.class);
                        gson.toJson(defaultData, writer);
                        try {
                            writer.close();
                            hasSpawnPlatformGeneratedBefore = false;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (pos.getY() >= level.getMaxBuildHeight() && level.getBlockState(pos.below()).is(Blocks.BEDROCK)) {
                        pos = pos.below(64);
                        generateSquare(level, pos.below(2), Blocks.OAK_WOOD.defaultBlockState());
                        generateSquare(level, pos.below(), Blocks.OAK_WOOD.defaultBlockState());
                        generateSquare(level, pos, Blocks.AIR.defaultBlockState());
                        generateSquare(level, pos.above(), Blocks.AIR.defaultBlockState());
                        level.setBlock(pos, Blocks.TORCH.defaultBlockState(), UPDATE_ALL);
                        entity.teleportRelative(0, -64, 0);
                    } else if (!hasSpawnPlatformGeneratedBefore) {
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
        });
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
}