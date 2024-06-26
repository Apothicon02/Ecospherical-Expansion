package com.Apothic0n.EcosphericalExpansion.core.objects;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public interface AmethystVines {
    VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    BooleanProperty BERRIES = BlockStateProperties.BERRIES;

    static InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos) {
        if (!pState.getValue(BERRIES)) {
            Block.popResource(pLevel, pPos, new ItemStack(Items.AMETHYST_SHARD, 1));
            float f = Mth.randomBetween(pLevel.random, 0.8F, 1.2F);
            pLevel.playSound((Player)null, pPos, SoundEvents.AMETHYST_BLOCK_FALL, SoundSource.BLOCKS, 1.0F, f);
            pLevel.setBlock(pPos, pState.setValue(BERRIES, Boolean.valueOf(true)), 2);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    static boolean hasGlowBerries(BlockState pState) {
        return pState.hasProperty(BERRIES) && pState.getValue(BERRIES);
    }

    static ToIntFunction<BlockState> emission(int p_181218_) {
        return (p_181216_) -> {
            return p_181216_.getValue(BlockStateProperties.BERRIES) ? p_181218_ : 0;
        };
    }
}
