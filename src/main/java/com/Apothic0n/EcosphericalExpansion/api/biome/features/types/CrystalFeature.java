package com.Apothic0n.EcosphericalExpansion.api.biome.features.types;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class CrystalFeature extends Feature<SimpleBlockConfiguration> {
    public CrystalFeature(Codec<SimpleBlockConfiguration> pContext) {
        super(pContext);
    }

    public static List<List<Block>> lootBlocks = List.of(
            List.of(Blocks.ICE, Blocks.PACKED_ICE),
            List.of(Blocks.PACKED_ICE, Blocks.BLUE_ICE));

    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> pContext) {
        WorldGenLevel worldGenLevel = pContext.level();
        BlockPos blockPos = pContext.origin();
        BlockPos origin = blockPos;
        RandomSource random = pContext.random();
        BlockState material = pContext.config().toPlace().getState(random, blockPos);
        List<BlockPos> positions = new ArrayList<>(List.of());

        positions.add(checkBlockPos(worldGenLevel, blockPos));
        positions.add(checkBlockPos(worldGenLevel, blockPos.above()));
        positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(2)));
        positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(3)));
        positions.addAll(generateMediumCircle(worldGenLevel, blockPos.above(4)));
        positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(5)));
        positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(6)));
        positions.add(checkBlockPos(worldGenLevel, blockPos.above(7)));
        positions.add(checkBlockPos(worldGenLevel, blockPos.above(8)));

        int randomNumber = (int)(random.nextFloat()*(3)+1);
        if (randomNumber < 2) { //33% chance to generate big crystal above small one
            blockPos = blockPos.above(15);
            origin = blockPos.above(6);
        } else if (randomNumber < 3) { //33% chance to generate big crystal below small one
            blockPos = blockPos.below(15);
            origin = blockPos.above(6);
        } else {
            origin = blockPos.above(22);
        }

        if (randomNumber < 3) { //66% chance to generate big crystal
            positions.add(checkBlockPos(worldGenLevel, blockPos));
            positions.add(checkBlockPos(worldGenLevel, blockPos.above()));
            positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(2)));
            positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(3)));
            positions.addAll(generateMediumCircle(worldGenLevel, blockPos.above(4)));
            positions.addAll(generateMediumCircle(worldGenLevel, blockPos.above(5)));
            positions.addAll(generateMediumCircle(worldGenLevel, blockPos.above(6)));
            positions.addAll(generateMediumCircle(worldGenLevel, blockPos.above(7)));
            positions.addAll(generateMediumCircle(worldGenLevel, blockPos.above(8)));
            positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(9)));
            positions.addAll(generateSmallCircle(worldGenLevel, blockPos.above(10)));
            positions.add(checkBlockPos(worldGenLevel, blockPos.above(11)));
            positions.add(checkBlockPos(worldGenLevel, blockPos.above(12)));
        }

        List<Block> insideMaterial = lootBlocks.get((int)(random.nextFloat()*(lootBlocks.size())));

        List<BlockPos> validPositions = new ArrayList<>(List.of());
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getX() == 0) {
                return false;
            } else {
                validPositions.add(positions.get(i));
            }
        }
        for (int i = 0; i < validPositions.size(); i++) {
            BlockState block = material;
            BlockPos pos = validPositions.get(i);
            if (pos.getX() - origin.getX() < 2 && pos.getY() - origin.getY() < 2 && pos.getZ() - origin.getZ() < 2 &&
                    pos.getX() - origin.getX() > -2 && pos.getY() - origin.getY() > -2 && pos.getZ() - origin.getZ() > -2) {
                block = insideMaterial.get((int)(random.nextFloat()*(insideMaterial.size()))).defaultBlockState();
            }
            worldGenLevel.setBlock(pos, block, UPDATE_ALL);
        }
        return true;
    }

    private BlockPos checkBlockPos(WorldGenLevel worldGenLevel, BlockPos blockPos) {
        if (worldGenLevel.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
            return blockPos;
        }
        return new BlockPos(0, 0, 0);
    }


    private List<BlockPos> generateSmallCircle(WorldGenLevel worldGenLevel, BlockPos blockPos) {
        List<BlockPos> positions = new ArrayList<>(List.of());

        positions.add(checkBlockPos(worldGenLevel, blockPos));
        positions.add(checkBlockPos(worldGenLevel, blockPos.north()));
        positions.add(checkBlockPos(worldGenLevel, blockPos).east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south());
        positions.add(checkBlockPos(worldGenLevel, blockPos).west());
        positions.add(checkBlockPos(worldGenLevel, blockPos).north().east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).north().west());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south().east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south().west());

        return positions;
    }
    private List<BlockPos> generateMediumCircle(WorldGenLevel worldGenLevel, BlockPos blockPos) {
        List<BlockPos> positions = generateSmallCircle(worldGenLevel, blockPos);

        positions.add(checkBlockPos(worldGenLevel, blockPos.north(2)));
        positions.add(checkBlockPos(worldGenLevel, blockPos).east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).west(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(2).east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(2).west());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2).east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2).west());
        positions.add(checkBlockPos(worldGenLevel, blockPos).north().east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north().west(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south().east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south().west(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(2).east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(2).west(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2).east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2).west(2));

        return positions;
    }

    private List<BlockPos> generateLargeCircle(WorldGenLevel worldGenLevel, BlockPos blockPos) {
        List<BlockPos> positions = generateMediumCircle(worldGenLevel, blockPos);

        positions.add(checkBlockPos(worldGenLevel, blockPos.north(3)));
        positions.add(checkBlockPos(worldGenLevel, blockPos).east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).west(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(3).east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(3).west());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3).east());
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3).west());
        positions.add(checkBlockPos(worldGenLevel, blockPos).north().east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north().west(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south().east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south().west(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(3).east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(3).west(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3).east(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3).west(2));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(2).east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(2).west(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2).east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(2).west(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(3).east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).north(3).west(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3).east(3));
        positions.add(checkBlockPos(worldGenLevel, blockPos).south(3).west(3));

        return positions;
    }
}