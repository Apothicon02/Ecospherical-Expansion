package com.Apothic0n.EcosphericalExpansion.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin {
    @Shadow public abstract BlockState getBlockState(BlockPos p_62923_);

    @Inject(at = @At("HEAD"), method = "promotePendingBlockEntity", cancellable = true)
    private void promotePendingBlockEntity(BlockPos blockPos, CompoundTag tag, CallbackInfoReturnable<BlockEntity> cir) {
        BlockState blockState = this.getBlockState(blockPos);
        if (!blockState.hasBlockEntity()) {
            cir.setReturnValue(null);
            cir.cancel();
        }
    }
}
