package com.Apothic0n.EcosphericalExpansion.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CommonConfig {
    public static ForgeConfigSpec.IntValue weight;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> allowedBiomes;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("General settings for Ecospherical Expansion").push("common");

        weight = COMMON_BUILDER
                .comment("Weight of Ecospherical biomes. Higher numbers mean all biomes added by this mod are more common. Default: 2")
                .defineInRange("weight", 2, 1, Integer.MAX_VALUE);

        allowedBiomes = COMMON_BUILDER
                .comment("Remove biomes from this list to prevent them from generating. \n" +
                        "Default: \n"+
                        "\"eco:lush_oak\", \"eco:lush_jungle\", \"eco:enriched_roofed_forest\", \"eco:frozen_roofed_forest\",\n" +
                        "\"lush_birch\", \"eco:glacial_plains\", \"eco:mega_savanna\", \"eco:mega_swamp\", \"eco:mushroom_grove\", \"eco:deepslate_cliffs\",\n" +
                        "\"calcite_cliffs\", \"eco:mushroom_plains\", \"eco:lush_desert\", \"eco:icy_taiga\", \"eco:floral_beach\", \"eco:oversnowed_taiga\"")
                .defineList("allowedBiomes", List.of("eco:lush_oak", "eco:lush_jungle", "eco:enriched_roofed_forest", "eco:frozen_roofed_forest",
                        "lush_birch", "eco:glacial_plains", "eco:mega_savanna", "eco:mega_swamp", "eco:mushroom_grove", "eco:deepslate_cliffs",
                        "calcite_cliffs", "eco:mushroom_plains", "eco:lush_desert", "eco:icy_taiga", "eco:floral_beach", "eco:oversnowed_taiga"), string -> string instanceof String);

        COMMON_BUILDER.pop();
    }
}
