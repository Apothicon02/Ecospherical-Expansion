package com.Apothic0n.api.biome.features.trunk_placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class GiantBranchingTrunkPlacer extends TrunkPlacer {
    private static final Codec<UniformInt> BRANCH_START_CODEC = ExtraCodecs.validate(UniformInt.CODEC, uniformInt -> {
        if (uniformInt.getMaxValue() - uniformInt.getMinValue() < 1) {
            return DataResult.error(() -> "Need at least 2 blocks variation for the branch starts to fit both branches");
        }
        return DataResult.success(uniformInt);
    });
    public static final Codec<GiantBranchingTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> GiantBranchingTrunkPlacer.trunkPlacerParts(instance).and(instance.group(
            (IntProvider.codec(1, 12).fieldOf("amount_of_branches")).forGetter(giantBranchingTrunkPlacer -> giantBranchingTrunkPlacer.amountOfBranches),
            (IntProvider.codec(1, 3).fieldOf("branch_count")).forGetter(giantBranchingTrunkPlacer -> giantBranchingTrunkPlacer.branchCount),
            (IntProvider.codec(2, 16).fieldOf("branch_horizontal_length")).forGetter(giantBranchingTrunkPlacer -> giantBranchingTrunkPlacer.branchHorizontalLength),
            (IntProvider.codec(-48, 0, BRANCH_START_CODEC).fieldOf("branch_start_offset_from_top")).forGetter(giantBranchingTrunkPlacer -> giantBranchingTrunkPlacer.branchStartOffsetFromTop),
            (IntProvider.codec(-48, 48).fieldOf("branch_end_height")).forGetter(giantBranchingTrunkPlacer -> giantBranchingTrunkPlacer.branchEndHeight))
    ).apply(instance, GiantBranchingTrunkPlacer::new));

    private final IntProvider amountOfBranches;
    private final IntProvider branchCount;
    private final IntProvider branchHorizontalLength;
    private final UniformInt branchStartOffsetFromTop;
    private final UniformInt secondBranchStartOffsetFromTop;
    private final IntProvider branchEndHeight;

    public GiantBranchingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider amountOfBranches, IntProvider branchCount, IntProvider branchHorizontalLength, UniformInt branchStartOffsetFromTop, IntProvider branchEndHeight) {
        super(baseHeight, heightRandA, heightRandB);
        this.amountOfBranches = branchCount;
        this.branchCount = branchCount;
        this.branchHorizontalLength = branchHorizontalLength;
        this.branchStartOffsetFromTop = branchStartOffsetFromTop;
        this.secondBranchStartOffsetFromTop = UniformInt.of(branchStartOffsetFromTop.getMinValue(), branchStartOffsetFromTop.getMaxValue() - 1);
        this.branchEndHeight = branchEndHeight;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EcoTrunkPlacerType.GIANT_BRANCHING_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        freeTreeHeight = freeTreeHeight*2;
        boolean bl2;
        int k;
        GiantBranchingTrunkPlacer.setDirtAt(level, blockSetter, random, pos.below(2), config);
        int i = Math.max(0, freeTreeHeight - 1 + this.branchStartOffsetFromTop.sample(random));
        int j = Math.max(0, freeTreeHeight - 1 + this.secondBranchStartOffsetFromTop.sample(random));
        if (j >= i) {
            ++j;
        }
        boolean bl = (k = this.branchCount.sample(random)) == 3;
        boolean bl3 = bl2 = k >= 2;
        int l = bl ? freeTreeHeight : (bl2 ? Math.max(i, j) + 1 : i + 1);
        for (int m = 0; m <= 4; ++m) {
            this.placeLog(level, blockSetter, random, pos.below(m), config);
            this.placeLog(level, blockSetter, random, pos.below(m).north(), config);
            this.placeLog(level, blockSetter, random, pos.below(m).east(), config);
            this.placeLog(level, blockSetter, random, pos.below(m).north().east(), config);
        }
        for (int m = 0; m < l; ++m) {
            this.placeLog(level, blockSetter, random, pos.above(m), config);

            this.placeLog(level, blockSetter, random, pos.above(m).north(), config);
            this.placeLog(level, blockSetter, random, pos.above(m).east(), config);
            this.placeLog(level, blockSetter, random, pos.above(m).north().east(), config);
        }
        ArrayList<FoliagePlacer.FoliageAttachment> list = new ArrayList<FoliagePlacer.FoliageAttachment>();
        list.add(new FoliagePlacer.FoliageAttachment(pos.above(l), 0, false));
        int amountOfBranches = this.amountOfBranches.sample(random);
        int branchCount = this.branchCount.sample(random);
        for (int b = 1; b <= amountOfBranches; b++) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            Function<BlockState, BlockState> function = blockState -> (BlockState) blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
            int randomNumber = (int)(Math.random()*(-branchStartOffsetFromTop.sample(random))+1);
            list.add(this.generateBranch(level, blockSetter, random, freeTreeHeight, pos.below(randomNumber), config, function, direction, i, i < l - 1, mutableBlockPos));
            if (branchCount > 1) {
                list.add(this.generateBranch(level, blockSetter, random, freeTreeHeight, pos.below(randomNumber), config, function, direction.getClockWise(), j, j < l - 1, mutableBlockPos));
            }
            if (branchCount > 2) {
                list.add(this.generateBranch(level, blockSetter, random, freeTreeHeight, pos.below(randomNumber), config, function, direction.getCounterClockWise(), j, j < l - 1, mutableBlockPos));
            }
        }
        return list;
    }

    private FoliagePlacer.FoliageAttachment generateBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> posSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config, Function<BlockState, BlockState> propertySetter, Direction direction, int secondBranchStartOffsetFromTop, boolean bl, BlockPos.MutableBlockPos mutableBlockPos) {
        int m;
        Direction direction2;
        mutableBlockPos.set(pos).move(Direction.UP, secondBranchStartOffsetFromTop);
        int i = secondBranchStartOffsetFromTop+this.branchEndHeight.sample(random);
        boolean bl2 = bl || i < secondBranchStartOffsetFromTop;
        int j = this.branchHorizontalLength.sample(random) + (bl2 ? 1 : 0);
        BlockPos blockPos = pos.relative(direction, j).above(i);
        int k = bl2 ? 2 : 1;
        for (int l = 0; l < k; ++l) {
            this.placeLog(level, posSetter, random, mutableBlockPos.move(direction), config, propertySetter);
        }
        Direction direction3 = direction2 = blockPos.getY() > mutableBlockPos.getY() ? Direction.UP : Direction.DOWN;
        while ((m = mutableBlockPos.distManhattan(blockPos)) != 0) {
            float f = (float)Math.abs(blockPos.getY() - mutableBlockPos.getY()) / (float)m;
            boolean bl3 = random.nextFloat() < f;
            mutableBlockPos.move(bl3 ? direction2 : direction);
            this.placeLog(level, posSetter, random, mutableBlockPos, config, bl3 ? Function.identity() : propertySetter);

            this.placeLog(level, posSetter, random, mutableBlockPos.north(), config, bl3 ? Function.identity() : propertySetter);
            this.placeLog(level, posSetter, random, mutableBlockPos.east(), config, bl3 ? Function.identity() : propertySetter);
            this.placeLog(level, posSetter, random, mutableBlockPos.north().east(), config, bl3 ? Function.identity() : propertySetter);
        }
        return new FoliagePlacer.FoliageAttachment(blockPos.above(), 0, false);
    }
}
