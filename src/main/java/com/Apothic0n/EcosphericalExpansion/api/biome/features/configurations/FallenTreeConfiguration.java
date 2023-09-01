package com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class FallenTreeConfiguration implements FeatureConfiguration {
    public static final Codec<FallenTreeConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(BlockState.CODEC.fieldOf("material").forGetter((v) -> {
            return v.material;
        }), IntProvider.codec(1, 32).fieldOf("length").forGetter((v) -> {
            return v.length;
        })).apply(fields, FallenTreeConfiguration::new);
    });

    public final BlockState material;
    private final IntProvider length;

    public FallenTreeConfiguration(BlockState material, IntProvider length) {
        this.material = material;
        this.length = length;
    }

    public IntProvider getLength() {return this.length;}
}
