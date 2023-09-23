package com.Apothic0n.EcosphericalExpansion.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrowingPlantBlock.class)
public abstract class GrowingPlantBlockMixin {
    @Shadow @Final protected Direction growthDirection;
    @Shadow protected abstract GrowingPlantHeadBlock getHeadBlock();
    @Shadow protected abstract Block getBodyBlock();

    @Inject(at = @At("RETURN"), method = "canSurvive", cancellable = true)
    private void eco_canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable ci) {
        BlockPos blockpos = blockPos.relative(growthDirection.getOpposite());
        BlockState blockstate = levelReader.getBlockState(blockpos);
        ci.setReturnValue(blockstate.is(Blocks.OAK_LEAVES) || blockstate.is(Blocks.DARK_OAK_LEAVES) || blockstate.is(Blocks.BIRCH_LEAVES)  || blockstate.is(Blocks.ACACIA_LEAVES) || blockstate.is(Blocks.SPRUCE_LEAVES)  ||
                blockstate.is(Blocks.JUNGLE_LEAVES) || blockstate.is(Blocks.DARK_OAK_LEAVES) || blockstate.is(Blocks.DARK_OAK_LEAVES) || blockstate.is(Blocks.DARK_OAK_LEAVES) || blockstate.is(Blocks.DARK_OAK_LEAVES) ||
                        blockstate.is(Blocks.MANGROVE_LEAVES) || blockstate.is(Blocks.CHERRY_LEAVES) || blockstate.is(Blocks.AZALEA_LEAVES) || blockstate.is(Blocks.FLOWERING_AZALEA_LEAVES) ||
                blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) || blockstate.isFaceSturdy(levelReader, blockpos, this.growthDirection));
    }
}
