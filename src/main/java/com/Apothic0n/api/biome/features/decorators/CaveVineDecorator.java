package com.Apothic0n.api.biome.features.decorators;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class CaveVineDecorator extends TreeDecorator {
    public static final Codec<CaveVineDecorator> CODEC = Codec.floatRange(0.0f, 1.0f).fieldOf("probability").xmap(CaveVineDecorator::new, caveVineDecorator -> Float.valueOf(caveVineDecorator.probability)).codec();
    private final float probability;

    public CaveVineDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return EcoTreeDecoratorType.CAVE_VINES;
    }

    @Override
    public void place(Context context) {
        LevelSimulatedReader level = context.level();
        RandomSource randomSource = context.random();
        ObjectArrayList<BlockPos> list = context.leaves();
        list.forEach(blockPos -> {
            if (randomSource.nextFloat() >= 0.1) {
                for (int i = 1; i < 32; i++) {
                    if (level.isStateAtPosition(blockPos.below(i), BlockStatePredicate.forBlock(Blocks.AIR)) && !level.isStateAtPosition(blockPos.below(i).above(), BlockStatePredicate.forBlock(Blocks.AIR)) &&
                            level.isStateAtPosition(blockPos.below(i), BlockStatePredicate.forBlock(Blocks.AIR)) && !level.isStateAtPosition(blockPos.below(i).above(), BlockStatePredicate.forBlock(Blocks.CAVE_VINES))) {
                        context.setBlock(blockPos.below(i), randomVine());
                    } else {
                        i=32;
                    }
                }
            }
        });
    }

    private BlockState randomVine() {
        int randomNumber = (int)(Math.random()*(11));
        if (randomNumber == 0) {
            return Blocks.CAVE_VINES_PLANT.defaultBlockState().setValue(BlockStateProperties.BERRIES, true);
        } else if (randomNumber < (this.probability)*10) {
            return Blocks.CAVE_VINES.defaultBlockState();
        }
        return Blocks.CAVE_VINES_PLANT.defaultBlockState();
    }
}