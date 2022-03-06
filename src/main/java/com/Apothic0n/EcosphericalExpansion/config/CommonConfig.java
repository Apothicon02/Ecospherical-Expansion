package com.Apothic0n.EcosphericalExpansion.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static ForgeConfigSpec.IntValue WEIGHT;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("General settings for Ecospherical Expansion").push("common");

        WEIGHT = COMMON_BUILDER
                .comment("Weight of Ecospherical biomes. Higher numbers mean all biomes added by this mod are more common.")
                .defineInRange("weight", 2, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
}
