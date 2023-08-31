package com.Apothic0n.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class WaterloggableRandomPatchConfiguration implements FeatureConfiguration {
    public static final Codec<WaterloggableRandomPatchConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter((v) -> {
            return v.tries;
        }), ExtraCodecs.POSITIVE_INT.fieldOf("xz_spread").orElse(7).forGetter((v) -> {
            return v.xz_spread;
        }), ExtraCodecs.POSITIVE_INT.fieldOf("y_spread").orElse(3).forGetter((v) -> {
            return v.y_spread;
        }), BlockStateProvider.CODEC.fieldOf("to_place").forGetter((v) -> {
            return v.to_place;
        })).apply(fields, WaterloggableRandomPatchConfiguration::new);
    });

    public final Integer tries;
    public final Integer xz_spread;
    public final Integer y_spread;
    public final BlockStateProvider to_place;

    public WaterloggableRandomPatchConfiguration(Integer tries, Integer xz_spread, Integer y_spread, BlockStateProvider to_place) {
        this.tries = tries;
        this.xz_spread = xz_spread;
        this.y_spread = y_spread;
        this.to_place = to_place;
    }
}
