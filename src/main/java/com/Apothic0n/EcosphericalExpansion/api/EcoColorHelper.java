package com.Apothic0n.EcosphericalExpansion.api;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.spongepowered.asm.mixin.Unique;

public class EcoColorHelper {
    @Unique
    private static final PerlinSimplexNoise SATURATION_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(2345L)), ImmutableList.of(0));
    @Unique
    private static final PerlinSimplexNoise BRIGHTNESS_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(5432L)), ImmutableList.of(0));

    public static int tintFoliageOrGrass(BlockState blockState, int x, int y, int z, double temperature, double humidity, boolean isFoliage) {
        double brighten = Mth.clamp(BRIGHTNESS_NOISE.getValue(x * 0.05, z * 0.01, false), -0.25, 0.25)+0.5;
        double dryShift = 0.2;
        double darkShift = 0;
        double autumnalShift = 0;
        double snowShift = 0;
        String name = blockState.getBlock().asItem().getDefaultInstance().getHoverName().getString();
        if (!isFoliage) {
            dryShift = 0.8;
            darkShift = 1.15;
        } else if (name.contains("Dark Oak")) {
            autumnalShift = 1.5-((Mth.clamp(Mth.abs((float) temperature), 0.75, 0.8)-0.75)*30);
        } else {
            snowShift = 0.33;
        }
        double snowFactor = getFactor(snowShift, 1.1, -10, -0.8, temperature);
        double autumnalFactor = getFactor(autumnalShift, 15, -10, -0.18, temperature);
        double darkFactor = getFactor(darkShift, 3, -10, 0, temperature);
        double warmFactor = getFactor(0.1, 4, 0.15, 10, temperature);
        double dryFactor =  Mth.clamp(getFactor(dryShift, dryShift*3.33, -10, -0.1, humidity) - Math.max(0, getFactor(0.7, 5, -10, 0.425, temperature)), 0, 0.7);
        float red = (float) (0.6 + warmFactor + snowFactor + dryFactor - darkFactor + autumnalFactor);
        float green = (float) (1.5 + warmFactor + snowFactor - (dryFactor*1.33) - darkFactor - autumnalFactor);
        float blue = (float) ((0.66 - Mth.clamp(temperature, -0.25, 0.25)*0.02) + snowFactor - dryFactor - darkFactor - autumnalFactor);
        float gray = (float) ((red+green+blue)/(3+brighten));
        double saturate = -(Mth.clamp(SATURATION_NOISE.getValue(x * 0.05, z * 0.01, false) * 0.33, -0.33, 0.33)+0.5+warmFactor);
        red = (float) Mth.clamp(red + (gray - red) * saturate, 0.1, 1);
        green = (float) Mth.clamp(green + (gray - green) * saturate, 0.1, 1);
        blue = (float) Mth.clamp(blue + (gray - blue) * saturate, 0.1, 1);
        return FastColor.ARGB32.color(1, (int) (red*255), (int) (green*255), (int) (blue*255));
    }

    private static double getFactor(double shift, double size, double min, double max, double temperature) {
        if (temperature >= max) {
            shift = Math.min(shift, Math.max(0, shift - Mth.abs((float) ((max - temperature)*size))));
        } else if (temperature <= min) {
            shift = Math.min(shift, Math.max(0, shift - Mth.abs((float) ((min - temperature)*size))));
        }
        return shift;
    }
}
