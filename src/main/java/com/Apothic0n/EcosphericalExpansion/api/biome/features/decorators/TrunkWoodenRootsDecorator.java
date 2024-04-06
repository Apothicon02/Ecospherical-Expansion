package com.Apothic0n.EcosphericalExpansion.api.biome.features.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class TrunkWoodenRootsDecorator extends TreeDecorator {
    public static final Codec<TrunkWoodenRootsDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(TrunkWoodenRootsDecorator::getProbability),
            BlockStateProvider.CODEC.fieldOf("wall_state").forGetter(TrunkWoodenRootsDecorator::getWallBlock)
    ).apply(instance, TrunkWoodenRootsDecorator::new));
    private final float probability;
    private final BlockStateProvider wallBlock;
    public float getProbability() {
        return probability;
    }
    public BlockStateProvider getWallBlock() {
        return wallBlock;
    }

    public TrunkWoodenRootsDecorator(float probability, BlockStateProvider wallBlock) {
        this.probability = probability;
        this.wallBlock = wallBlock;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return EcoTreeDecoratorType.TRUNK_WOODEN_ROOTS.get();
    }

    @Override
    public void place(Context context) {
        LevelSimulatedReader level = context.level();
        RandomSource random = context.random();
        ObjectArrayList<BlockPos> list = context.roots();
        if (list.isEmpty() && context.logs().size() > 6) {
            list = ObjectArrayList.of(context.logs().get(0), context.logs().get(1), context.logs().get(2), context.logs().get(3), context.logs().get(4), context.logs().get(5), context.logs().get(6));
        }
        BlockState woodWall = this.wallBlock.getState(random, new BlockPos(0, 0, 0));
        if (!woodWall.isAir()) {
            list.forEach(blockPos -> {
                int randomNumber = (int) (random.nextFloat() * (this.probability * 100) + 1);
                if (randomNumber < 2) {
                    placeRoot(context, level, blockPos.north(), woodWall);
                } else if (randomNumber < 3) {
                    placeRoot(context, level, blockPos.east(), woodWall);
                } else if (randomNumber < 4) {
                    placeRoot(context, level, blockPos.south(), woodWall);
                } else if (randomNumber < 5) {
                    placeRoot(context, level, blockPos.west(), woodWall);
                }
            });
        }
    }

    private void placeRoot(Context context, LevelSimulatedReader level, BlockPos blockPos, BlockState blockState) {
        Boolean isGroundBelow = false;
        for (int i = 0; i < 7; i++) {
            if (level.isStateAtPosition(blockPos.below(i), BlockBehaviour.BlockStateBase::isSolid)) {
                isGroundBelow = true;
            }
        }
        if (isGroundBelow) {
            for (int i = 0; i < 7; i++) {
                if (level.isStateAtPosition(blockPos.below(i), BlockBehaviour.BlockStateBase::canBeReplaced)) {
                    context.setBlock(blockPos.below(i), blockState);
                } else {
                    break;
                }
            }
        }
    }
}
