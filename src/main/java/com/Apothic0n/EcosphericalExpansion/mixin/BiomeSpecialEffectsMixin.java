package com.Apothic0n.EcosphericalExpansion.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.biome.BiomeSpecialEffects$GrassColorModifier$1")
public class BiomeSpecialEffectsMixin {
    private static final PerlinSimplexNoise GRASS_SATURATION_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(2345L)), ImmutableList.of(0));
    private static final PerlinSimplexNoise GRASS_BRIGHTNESS_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(5432L)), ImmutableList.of(0));
    @Inject(at = @At("RETURN"), method = "modifyColor", cancellable = true)
    private void eco_modifyColor(double x, double z, int grassColor, CallbackInfoReturnable<Integer> cir) {
        double saturate = -(Mth.clamp(GRASS_SATURATION_NOISE.getValue(x * 0.05, z * 0.01, false) * 0.33, -0.33, 0.33)+0.33);
        double brighten = Mth.clamp(GRASS_BRIGHTNESS_NOISE.getValue(x * 0.1, z * 0.075, false), -0.5, 0.5)+0.75;
        float red = (float) Mth.clamp(FastColor.ABGR32.red(grassColor), 1, 255)/255;
        float green = (float) Mth.clamp(FastColor.ABGR32.green(grassColor), 1, 255)/255;
        float blue = (float) Mth.clamp(FastColor.ABGR32.blue(grassColor), 1, 255)/255;
        float gray = (float) ((red+green+blue)/(3+brighten));
        red = (float) Mth.clamp(red + (gray - red) * saturate, 0.1, 1);
        green = (float) Mth.clamp(green + (gray - green) * saturate, 0.1, 1);
        blue = (float) Mth.clamp(blue + (gray - blue) * saturate, 0.1, 1);
        int newGrassColor = FastColor.ABGR32.color(FastColor.ABGR32.alpha(grassColor),(int) (blue*255), (int) (green*255), (int) (red*255));
        cir.setReturnValue(newGrassColor);
    }
}