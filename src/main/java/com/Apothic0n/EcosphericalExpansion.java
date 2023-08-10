package com.Apothic0n;


import com.Apothic0n.api.biome.ECOSurfaceRuleData;
import com.Apothic0n.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.core.objects.EcoBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class EcosphericalExpansion implements ModInitializer, TerraBlenderApi {
    public static final String MODID = "eco";
    @Override
    public void onInitialize() {
        EcoBlocks.register();
        EcoFeatureRegistry.register();
        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.AFTER_BEDROCK, 100, ECOSurfaceRuleData.makeRules());

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
    }
}