package com.Apothic0n.EcosphericalExpansion.core.events;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks;
import com.mojang.serialization.JsonOps;
import commoble.databuddy.datagen.BlockStateFile;
import commoble.databuddy.datagen.SimpleModel;
import net.minecraft.Util;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks.*;

@Mod.EventBusSubscriber(modid = EcosphericalExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        // models
        SimpleModel.addDataProvider(event, EcosphericalExpansion.MODID, JsonOps.INSTANCE, Util.make(new HashMap<>(), map ->
        {
            for (int i = 0; i < blocksWithStairsSlabsAndWalls.size(); i++) {
                Block baseBlockBlock = blocksWithStairsSlabsAndWalls.get(i);
                String name = baseBlockBlock.toString();
                String finalName = name.substring(16, name.length() - 1);
                ResourceLocation baseBlock = new ResourceLocation("minecraft", "block/" + finalName);
                map = makeWallModels(map, baseBlockBlock, baseBlock, new ResourceLocation("minecraft", "solid"));
                map = makeStairsModels(map, baseBlockBlock, baseBlock);
                map = makeSlabModels(map, baseBlockBlock, baseBlock);
            }
            for (int i = 0; i < blocksWithWalls.size(); i++) {
                Block baseBlockBlock = blocksWithWalls.get(i);
                String name = baseBlockBlock.toString();
                String finalName = name.substring(16, name.length() - 1);
                if (finalName.contains("wood")) {
                    finalName = finalName.substring(0, finalName.length() - 4) + "log";
                }
                ResourceLocation baseBlock = new ResourceLocation("minecraft", "block/" + finalName);
                map = makeWallModels(map, baseBlockBlock, baseBlock, new ResourceLocation("minecraft", "solid"));
            }
            for (int i = 0; i < blocksWithFragileWalls.size(); i++) {
                Block baseBlockBlock = blocksWithFragileWalls.get(i);
                String name = baseBlockBlock.toString();
                String finalName = name.substring(16, name.length() - 1);
                ResourceLocation baseBlock = new ResourceLocation("minecraft", "block/" + finalName);
                map = makeWallModels(map, baseBlockBlock, baseBlock, new ResourceLocation("minecraft", "cutout"));
            }
            for (int i = 0; i < blocksWithPiles.size(); i++) {
                Block baseBlockBlock = blocksWithPiles.get(i);
                String name = baseBlockBlock.toString();
                String finalName = name.substring(16, name.length() - 1);
                ResourceLocation baseBlock = new ResourceLocation("minecraft", "block/" + finalName);
                map = makePileModels(map, baseBlockBlock, baseBlock, new ResourceLocation("minecraft", "cutout"));
            }
        }));
        // blockstates
        BlockStateFile.addDataProvider(event, EcosphericalExpansion.MODID, JsonOps.INSTANCE, Util.make(new HashMap<>(), map -> {
            for (int i = 0; i < blocksWithStairsSlabsAndWalls.size(); i++) {
                Block baseBlockBlock = blocksWithStairsSlabsAndWalls.get(i);
                map = makeWallBlockstates(map, baseBlockBlock);
                map = makeStairsBlockstates(map, baseBlockBlock);
                map = makeSlabBlockstates(map, baseBlockBlock);
            }
            for (int i = 0; i < blocksWithWalls.size(); i++) {
                Block baseBlockBlock = blocksWithWalls.get(i);
                map = makeWallBlockstates(map, baseBlockBlock);
            }
            for (int i = 0; i < blocksWithFragileWalls.size(); i++) {
                Block baseBlockBlock = blocksWithFragileWalls.get(i);
                map = makeWallBlockstates(map, baseBlockBlock);
            }
            for (int i = 0; i < blocksWithPiles.size(); i++) {
                Block baseBlockBlock = blocksWithPiles.get(i);
                map = makePileBlockstates(map, baseBlockBlock);
            }
        }));
    }

    private static HashMap makePileModels(HashMap map, Block baseBlockBlock, ResourceLocation baseBlock, ResourceLocation renderType) {
        ResourceLocation tempPileBlock2 = new ResourceLocation("block/failure2");
        ResourceLocation tempPileBlock4 = new ResourceLocation("block/failure4");
        ResourceLocation tempPileBlock6 = new ResourceLocation("block/failure6");
        ResourceLocation tempPileBlock8 = new ResourceLocation("block/failure8");
        ResourceLocation tempPileBlock10 = new ResourceLocation("block/failure10");
        ResourceLocation tempPileBlock12 = new ResourceLocation("block/failure12");
        ResourceLocation tempPileBlock14 = new ResourceLocation("block/failure14");
        ResourceLocation tempPileBlock16 = new ResourceLocation("block/failure16");
        ResourceLocation tempPileBlockItem = new ResourceLocation("block/failure_block_item");
        for (int o = 0; o < pileBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> pileBlockMap = pileBlocks.get(o);
            if (pileBlockMap.containsKey(baseBlockBlock)) {
                tempPileBlock2 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height2");
                tempPileBlock4 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height4");
                tempPileBlock6 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height6");
                tempPileBlock8 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height8");
                tempPileBlock10 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height10");
                tempPileBlock12 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height12");
                tempPileBlock14 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height14");
                tempPileBlock16 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height16");
                tempPileBlockItem = new ResourceLocation(EcosphericalExpansion.MODID, "item/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4));
            }
        }
        ResourceLocation pileBlock2 = tempPileBlock2;
        ResourceLocation pileBlock4 = tempPileBlock4;
        ResourceLocation pileBlock6 = tempPileBlock6;
        ResourceLocation pileBlock8 = tempPileBlock8;
        ResourceLocation pileBlock10 = tempPileBlock10;
        ResourceLocation pileBlock12 = tempPileBlock12;
        ResourceLocation pileBlock14 = tempPileBlock14;
        ResourceLocation pileBlock16 = tempPileBlock16;
        ResourceLocation pileBlockItem = tempPileBlockItem;
        map.put(pileBlock2,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height2"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock4,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height4"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock6,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height6"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock8,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height8"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock10,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height10"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock12,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height12"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock14,
                SimpleModel.create(new ResourceLocation("eco", "block/leaves_height14"), renderType)
                        .addTexture("texture", baseBlock));
        map.put(pileBlock16,
                SimpleModel.create(new ResourceLocation("block/cube_all"), renderType)
                        .addTexture("all", baseBlock));
        map.put(pileBlockItem,
                SimpleModel.create(pileBlock2, renderType));
        return map;
    }

    private static HashMap makeWallModels(HashMap map, Block baseBlockBlock, ResourceLocation baseBlock, ResourceLocation renderType) {
        ResourceLocation tempWallBlock = new ResourceLocation("block/failure");
        ResourceLocation tempWallBlockSide = new ResourceLocation("block/failure_side");
        ResourceLocation tempWallBlockSideTall = new ResourceLocation("block/failure_side_tall");
        ResourceLocation tempWallBlockItem = new ResourceLocation("block/failure_block_item");
        for (int o = 0; o < EcoBlocks.wallBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> wallBlockMap = EcoBlocks.wallBlocks.get(o);
            if (wallBlockMap.containsKey(baseBlockBlock)) {
                tempWallBlock = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_post");
                tempWallBlockSide = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_side");
                tempWallBlockSideTall = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_side_tall");
                tempWallBlockItem = new ResourceLocation(EcosphericalExpansion.MODID, "item/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4));
            }
        }
        ResourceLocation wallBlock = tempWallBlock;
        ResourceLocation wallBlockSide = tempWallBlockSide;
        ResourceLocation wallBlockSideTall = tempWallBlockSideTall;
        ResourceLocation wallBlockItem = tempWallBlockItem;
        map.put(wallBlock,
                SimpleModel.create(new ResourceLocation("block/template_wall_post"), renderType)
                        .addTexture("wall", baseBlock));
        map.put(wallBlockSide,
                SimpleModel.create(new ResourceLocation("block/template_wall_side"), renderType)
                        .addTexture("wall", baseBlock));
        map.put(wallBlockSideTall,
                SimpleModel.create(new ResourceLocation("block/template_wall_side_tall"), renderType)
                        .addTexture("wall", baseBlock));
        map.put(wallBlockItem,
                SimpleModel.create(new ResourceLocation("block/wall_inventory"), renderType)
                        .addTexture("wall", baseBlock));
        return map;
    }

    private static HashMap makeStairsModels(HashMap map, Block baseBlockBlock, ResourceLocation baseBlock) {
        ResourceLocation tempStairsBlock = new ResourceLocation("block/failure");
        ResourceLocation tempStairsBlockInner = new ResourceLocation("block/failure_inner");
        ResourceLocation tempStairsBlockOuter = new ResourceLocation("block/failure_outer");
        ResourceLocation tempStairsBlockItem = new ResourceLocation("block/failure_block_item");
        for (int o = 0; o < EcoBlocks.stairBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> stairBlockMap = EcoBlocks.stairBlocks.get(o);
            if (stairBlockMap.containsKey(baseBlockBlock)) {
                tempStairsBlock = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempStairsBlockInner = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_inner");
                tempStairsBlockOuter = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_outer");
                tempStairsBlockItem = new ResourceLocation(EcosphericalExpansion.MODID, "item/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4));
            }
        }
        ResourceLocation stairsBlock = tempStairsBlock;
        ResourceLocation stairsBlockInner = tempStairsBlockInner;
        ResourceLocation stairsBlockOuter = tempStairsBlockOuter;
        ResourceLocation stairsBlockItem = tempStairsBlockItem;
        map.put(stairsBlock,
                SimpleModel.create(new ResourceLocation("block/stairs"))
                        .addTexture("bottom", baseBlock)
                        .addTexture("side", baseBlock)
                        .addTexture("top", baseBlock));
        map.put(stairsBlockInner,
                SimpleModel.create(new ResourceLocation("block/inner_stairs"))
                        .addTexture("bottom", baseBlock)
                        .addTexture("side", baseBlock)
                        .addTexture("top", baseBlock));
        map.put(stairsBlockOuter,
                SimpleModel.create(new ResourceLocation("block/outer_stairs"))
                        .addTexture("bottom", baseBlock)
                        .addTexture("side", baseBlock)
                        .addTexture("top", baseBlock));
        map.put(stairsBlockItem,
                SimpleModel.create(stairsBlock));
        return map;
    }

    private static HashMap makeSlabModels(HashMap map, Block baseBlockBlock, ResourceLocation baseBlock) {
        ResourceLocation tempSlabBlock = new ResourceLocation("block/failure");
        ResourceLocation tempSlabBlockTop = new ResourceLocation("block/failure_top");
        ResourceLocation tempSlabBlockItem = new ResourceLocation("block/failure_block_item");
        for (int o = 0; o < EcoBlocks.slabBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> slabBlockMap = EcoBlocks.slabBlocks.get(o);
            if (slabBlockMap.containsKey(baseBlockBlock)) {
                tempSlabBlock = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + slabBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempSlabBlockTop = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + slabBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_top");
                tempSlabBlockItem = new ResourceLocation(EcosphericalExpansion.MODID, "item/" + slabBlockMap.get(baseBlockBlock).getId().toString().substring(4));
            }
        }
        ResourceLocation slabBlock = tempSlabBlock;
        ResourceLocation slabBlockTop = tempSlabBlockTop;
        ResourceLocation slabBlockItem = tempSlabBlockItem;
        map.put(slabBlock,
                SimpleModel.create(new ResourceLocation("block/slab"))
                        .addTexture("bottom", baseBlock)
                        .addTexture("side", baseBlock)
                        .addTexture("top", baseBlock));
        map.put(slabBlockTop,
                SimpleModel.create(new ResourceLocation("block/slab_top"))
                        .addTexture("bottom", baseBlock)
                        .addTexture("side", baseBlock)
                        .addTexture("top", baseBlock));
        map.put(slabBlockItem,
                SimpleModel.create(slabBlock));
        return map;
    }

    private static HashMap makePileBlockstates(HashMap map, Block baseBlockBlock) {
        ResourceLocation tempBlockstate = new ResourceLocation("block/failure_blockstate");
        ResourceLocation tempPileBlock2 = new ResourceLocation("block/failure2");
        ResourceLocation tempPileBlock4 = new ResourceLocation("block/failure4");
        ResourceLocation tempPileBlock6 = new ResourceLocation("block/failure6");
        ResourceLocation tempPileBlock8 = new ResourceLocation("block/failure8");
        ResourceLocation tempPileBlock10 = new ResourceLocation("block/failure10");
        ResourceLocation tempPileBlock12 = new ResourceLocation("block/failure12");
        ResourceLocation tempPileBlock14 = new ResourceLocation("block/failure14");
        ResourceLocation tempPileBlock16 = new ResourceLocation("block/failure16");
        for (int o = 0; o < pileBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> pileBlockMap = pileBlocks.get(o);
            if (pileBlockMap.containsKey(baseBlockBlock)) {
                tempBlockstate = new ResourceLocation(EcosphericalExpansion.MODID, pileBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempPileBlock2 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height2");
                tempPileBlock4 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height4");
                tempPileBlock6 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height6");
                tempPileBlock8 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height8");
                tempPileBlock10 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height10");
                tempPileBlock12 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height12");
                tempPileBlock14 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height14");
                tempPileBlock16 = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + pileBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_height16");
            }
        }
        ResourceLocation blockstate = tempBlockstate;
        ResourceLocation pileBlock2 = tempPileBlock2;
        ResourceLocation pileBlock4 = tempPileBlock4;
        ResourceLocation pileBlock6 = tempPileBlock6;
        ResourceLocation pileBlock8 = tempPileBlock8;
        ResourceLocation pileBlock10 = tempPileBlock10;
        ResourceLocation pileBlock12 = tempPileBlock12;
        ResourceLocation pileBlock14 = tempPileBlock14;
        ResourceLocation pileBlock16 = tempPileBlock16;
        map.put(blockstate,
                BlockStateFile.variants(BlockStateFile.Variants.builder()
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 1),
                                BlockStateFile.Model.create(pileBlock2))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 2),
                                BlockStateFile.Model.create(pileBlock4))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 3),
                                BlockStateFile.Model.create(pileBlock6))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 4),
                                BlockStateFile.Model.create(pileBlock8))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 5),
                                BlockStateFile.Model.create(pileBlock10))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 6),
                                BlockStateFile.Model.create(pileBlock12))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 7),
                                BlockStateFile.Model.create(pileBlock14))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SnowLayerBlock.LAYERS, 8),
                                BlockStateFile.Model.create(pileBlock16))));
        return map;
    }

    private static HashMap makeWallBlockstates(HashMap map, Block baseBlockBlock) {
        ResourceLocation tempWallState = new ResourceLocation("failure");
        ResourceLocation tempWallBlock = new ResourceLocation("block/failure");
        ResourceLocation tempWallBlockSide = new ResourceLocation("block/failure_side");
        ResourceLocation tempWallBlockSideTall = new ResourceLocation("block/failure_side_tall");
        for (int o = 0; o < EcoBlocks.wallBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> wallBlockMap = EcoBlocks.wallBlocks.get(o);
            if (wallBlockMap.containsKey(baseBlockBlock)) {
                tempWallState = new ResourceLocation(EcosphericalExpansion.MODID, wallBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempWallBlock = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_post");
                tempWallBlockSide = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_side");
                tempWallBlockSideTall = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + wallBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_side_tall");
            }
        }
        ResourceLocation wallState = tempWallState;
        ResourceLocation wallBlock = tempWallBlock;
        ResourceLocation wallBlockSide = tempWallBlockSide;
        ResourceLocation wallBlockSideTall = tempWallBlockSideTall;
        map.put(wallState,
                BlockStateFile.multipart(BlockStateFile.Multipart.builder()
                        .addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(WallBlock.UP, true),
                                BlockStateFile.Model.create(wallBlock)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.NORTH_WALL, WallSide.LOW),
                                BlockStateFile.Model.create(wallBlockSide)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.EAST_WALL, WallSide.LOW),
                                BlockStateFile.Model.create(wallBlockSide, BlockModelRotation.X0_Y90)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.SOUTH_WALL, WallSide.LOW),
                                BlockStateFile.Model.create(wallBlockSide, BlockModelRotation.X0_Y180)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.WEST_WALL, WallSide.LOW),
                                BlockStateFile.Model.create(wallBlockSide, BlockModelRotation.X0_Y270)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.NORTH_WALL, WallSide.TALL),
                                BlockStateFile.Model.create(wallBlockSideTall)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.EAST_WALL, WallSide.TALL),
                                BlockStateFile.Model.create(wallBlockSideTall, BlockModelRotation.X0_Y90)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.SOUTH_WALL, WallSide.TALL),
                                BlockStateFile.Model.create(wallBlockSideTall, BlockModelRotation.X0_Y180)
                        )).addWhenApply(BlockStateFile.WhenApply.when(
                                BlockStateFile.Case.create(BlockStateProperties.WEST_WALL, WallSide.TALL),
                                BlockStateFile.Model.create(wallBlockSideTall, BlockModelRotation.X0_Y270)
                        ))));
        return map;
    }

    private static HashMap makeStairsBlockstates(HashMap map, Block baseBlockBlock) {
        ResourceLocation tempStairState = new ResourceLocation("failure");
        ResourceLocation tempStairBlock = new ResourceLocation("block/failure");
        ResourceLocation tempStairBlockInner = new ResourceLocation("block/failure_inner");
        ResourceLocation tempStairBlockOuter = new ResourceLocation("block/failure_outer");
        for (int o = 0; o < EcoBlocks.stairBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> stairBlockMap = EcoBlocks.stairBlocks.get(o);
            if (stairBlockMap.containsKey(baseBlockBlock)) {
                tempStairState = new ResourceLocation(EcosphericalExpansion.MODID, stairBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempStairBlock = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempStairBlockInner = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_inner");
                tempStairBlockOuter = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + stairBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_outer");
            }
        }
        ResourceLocation stairState = tempStairState;
        ResourceLocation stairBlock = tempStairBlock;
        ResourceLocation stairBlockInner = tempStairBlockInner;
        ResourceLocation stairBlockOuter = tempStairBlockOuter;
        BlockStateFile.Variants variants = BlockStateFile.Variants.builder();
        for (Direction facing : StairBlock.FACING.getPossibleValues()) {
            for (Half half : StairBlock.HALF.getPossibleValues()) {
                for (StairsShape shape : StairBlock.SHAPE.getPossibleValues()) {
                    ResourceLocation model =
                            shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairBlockInner
                                    : shape == StairsShape.OUTER_LEFT || shape == StairsShape.OUTER_RIGHT ? stairBlockOuter
                                    : stairBlock;
                    int x = half == Half.TOP ? 180 : 0;
                    int y = ((int) facing.toYRot() + 90
                            + (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT ? 270 : 0)
                            + (half == Half.TOP && shape != StairsShape.STRAIGHT ? 90 : 0))
                            % 360;
                    boolean uvlock = x != 0 || y != 0;
                    variants.addVariant(List.of(BlockStateFile.PropertyValue.create(StairBlock.FACING, facing), BlockStateFile.PropertyValue.create(StairBlock.HALF, half), BlockStateFile.PropertyValue.create(StairBlock.SHAPE, shape)),
                            BlockStateFile.Model.create(model, BlockModelRotation.by(x, y), uvlock, 1));
                }
            }
        }
        map.put(stairState, BlockStateFile.variants(variants));
        return map;
    }

    private static HashMap makeSlabBlockstates(HashMap map, Block baseBlockBlock) {
        String name = baseBlockBlock.toString();
        ResourceLocation baseBlock = new ResourceLocation("minecraft", "block/" + name.substring(16, name.length() - 1));
        ResourceLocation tempSlabState = new ResourceLocation("failure");
        ResourceLocation tempSlabBlock = new ResourceLocation("block/failure");
        ResourceLocation tempSlabBlockTop = new ResourceLocation("block/failure_top");
        for (int o = 0; o < EcoBlocks.slabBlocks.size(); o++) {
            Map<Block, RegistryObject<Block>> slabBlockMap = EcoBlocks.slabBlocks.get(o);
            if (slabBlockMap.containsKey(baseBlockBlock)) {
                tempSlabState = new ResourceLocation(EcosphericalExpansion.MODID, slabBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempSlabBlock = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + slabBlockMap.get(baseBlockBlock).getId().toString().substring(4));
                tempSlabBlockTop = new ResourceLocation(EcosphericalExpansion.MODID, "block/" + slabBlockMap.get(baseBlockBlock).getId().toString().substring(4) + "_top");
            }
        }
        ResourceLocation slabState = tempSlabState;
        ResourceLocation slabBlock = tempSlabBlock;
        ResourceLocation slabBlockTop = tempSlabBlockTop;
        if (name.contains("red_mushroom_block")) {
            baseBlock = new ResourceLocation("eco", "block/red_mushroom_block_double_slab");
        } else if (name.contains("brown_mushroom_block")) {
            baseBlock = new ResourceLocation("eco", "block/brown_mushroom_block_double_slab");
        }
        map.put(slabState,
                BlockStateFile.variants(BlockStateFile.Variants.builder()
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SlabBlock.TYPE, SlabType.BOTTOM),
                                BlockStateFile.Model.create(slabBlock))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SlabBlock.TYPE, SlabType.DOUBLE),
                                BlockStateFile.Model.create(baseBlock))
                        .addVariant(
                                BlockStateFile.PropertyValue.create(SlabBlock.TYPE, SlabType.TOP),
                                BlockStateFile.Model.create(slabBlockTop))));
        return map;
    }
}
