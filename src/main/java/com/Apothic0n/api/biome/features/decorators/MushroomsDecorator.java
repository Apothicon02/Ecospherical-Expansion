package com.Apothic0n.api.biome.features.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.levelgen.blockpredicates.SolidPredicate;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Predicate;

public class MushroomsDecorator extends TreeDecorator {
    public static final Codec<MushroomsDecorator> CODEC = Codec.floatRange(0.0f, 1.0f).fieldOf("probability").xmap(MushroomsDecorator::new, mushroomsDecorator -> Float.valueOf(mushroomsDecorator.probability)).codec();
    private final float probability;

    public MushroomsDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return EcoTreeDecoratorType.MUSHROOMS;
    }

    @Override
    public void place(TreeDecorator.Context context) {
        LevelSimulatedReader level = context.level();
        RandomSource randomSource = context.random();
        ObjectArrayList<BlockPos> list = context.roots();
        if (randomSource.nextFloat() >= this.probability) {
            return;
        }
        if (list.isEmpty()) {
            list = ObjectArrayList.of(context.logs().get(0), context.logs().get(1), context.logs().get(2), context.logs().get(3));
        }
        list.forEach(blockPos -> {
            if (level.isStateAtPosition(blockPos.north(2).east(2).below(), BlockStatePredicate.forBlock(Blocks.AIR)) && !level.isStateAtPosition(blockPos.north(2).east(2).below(2), BlockStatePredicate.forBlock(Blocks.AIR))) {
                context.setBlock(blockPos.north(2).east(2).below(), randomMushroom());
            }
            if (level.isStateAtPosition(blockPos.north().west(), BlockStatePredicate.forBlock(Blocks.AIR)) && !level.isStateAtPosition(blockPos.north().west().below(), BlockStatePredicate.forBlock(Blocks.AIR))) {
                context.setBlock(blockPos.north().west(), randomMushroom());
            }
            if (level.isStateAtPosition(blockPos.south().east(), BlockStatePredicate.forBlock(Blocks.AIR)) && !level.isStateAtPosition(blockPos.south().east().below(), BlockStatePredicate.forBlock(Blocks.AIR))) {
                context.setBlock(blockPos.south().east(), randomMushroom());
            }
            if (level.isStateAtPosition(blockPos.south(2).west(2).below(), BlockStatePredicate.forBlock(Blocks.AIR)) && !level.isStateAtPosition(blockPos.south(2).west(2).below(2), BlockStatePredicate.forBlock(Blocks.AIR))) {
                context.setBlock(blockPos.south(2).west(2).below(), randomMushroom());
            }
        });
    }

    private BlockState randomMushroom() {
        int randomNumber = (int)(Math.random()*(2)+1);
        if (randomNumber < 2) {
            return Blocks.RED_MUSHROOM.defaultBlockState();
        }
        return Blocks.BROWN_MUSHROOM.defaultBlockState();
    }
}
