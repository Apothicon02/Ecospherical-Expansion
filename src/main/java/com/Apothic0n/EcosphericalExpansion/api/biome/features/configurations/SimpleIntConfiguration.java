package com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class SimpleIntConfiguration implements FeatureConfiguration {
    public static final Codec<SimpleIntConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(IntProvider.codec(1, 32).fieldOf("intValue").forGetter((v) -> {
            return v.intValue;
        })).apply(fields, SimpleIntConfiguration::new);
    });

    private final IntProvider intValue;

    public SimpleIntConfiguration(IntProvider intValue) {
        this.intValue = intValue;
    }

    public IntProvider getIntValue() {return this.intValue;}
}
