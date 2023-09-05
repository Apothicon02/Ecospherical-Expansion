package com.Apothic0n;


import com.Apothic0n.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.api.biome.features.decorators.EcoTreeDecoratorType;
import com.Apothic0n.api.biome.features.foliage_placers.EcoFoliagePlacerType;
import com.Apothic0n.api.biome.features.trunk_placers.EcoTrunkPlacerType;
import com.Apothic0n.core.objects.EcoBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static net.minecraft.world.level.block.Block.UPDATE_NONE;

public class EcosphericalExpansion implements ModInitializer {
    public static final String MODID = "eco";
    @Override
    public void onInitialize() {
        EcoBlocks.register();
        EcoFeatureRegistry.register();
        EcoTrunkPlacerType.init();
        EcoFoliagePlacerType.init();
        EcoTreeDecoratorType.init();

        UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
            BlockState state = world.getBlockState(hit.getBlockPos());
            BlockPos pos = hit.getBlockPos();
            ItemStack stack = player.getMainHandItem();
            if (!player.isSpectator() && state.getBlock().equals(Blocks.AMETHYST_CLUSTER) && stack.is(Items.GLOW_INK_SAC)) {
                world.setBlock(pos, EcoBlocks.GLOWING_AMETHYST.withPropertiesOf(state), 2);
                float f = Mth.randomBetween(world.random, 0.8F, 1.2F);
                world.playSound((Player)null, pos, SoundEvents.DOLPHIN_EAT, SoundSource.BLOCKS, 1.0F, f);
                player.swing(hand, true);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
            }
            return InteractionResult.PASS;
        });

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            Level level = entity.level();
            BlockPos pos = entity.blockPosition();
            if (entity instanceof Player) {
                if (pos.getY() >= level.getMaxBuildHeight() && level.dimension().equals(Level.OVERWORLD) && level.getBlockState(pos.below()).is(Blocks.BEDROCK)) {
                    pos = pos.below(64);
                    generateSquare(level, pos.below(2), Blocks.OAK_WOOD.defaultBlockState());
                    generateSquare(level, pos.below(), Blocks.OAK_WOOD.defaultBlockState());
                    generateSquare(level, pos, Blocks.AIR.defaultBlockState());
                    generateSquare(level, pos.above(), Blocks.AIR.defaultBlockState());
                    level.setBlock(pos, Blocks.TORCH.defaultBlockState(), UPDATE_ALL);
                    entity.teleportRelative(0, -64, 0);
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