package com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class OreSpikeConfiguration implements FeatureConfiguration {
    public static final Codec<OreSpikeConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(BlockStateProvider.CODEC.fieldOf("upperState").forGetter((v) -> {
            return v.upperState;
        }), BlockStateProvider.CODEC.fieldOf("lowerState").forGetter((v) -> {
            return v.lowerState;
        }), IntProvider.codec(1, 1024).fieldOf("blobMass").forGetter((v) -> {
            return v.blobMass;
        }), IntProvider.codec(1, 32).fieldOf("blobWidth").forGetter((v) -> {
            return v.blobWidth;
        }), IntProvider.codec(1, 128).fieldOf("blobHeight").forGetter((v) -> {
            return v.blobHeight;
        })).apply(fields, OreSpikeConfiguration::new);
    });
    public final BlockStateProvider upperState;
    public final BlockStateProvider lowerState;
    private final IntProvider blobMass;
    private final IntProvider blobWidth;
    private final IntProvider blobHeight;

    public OreSpikeConfiguration(BlockStateProvider upperState, BlockStateProvider lowerState, IntProvider blobMass, IntProvider blobWidth, IntProvider blobHeight) {
        this.upperState = upperState;
        this.lowerState = lowerState;
        this.blobMass = blobMass;
        this.blobWidth = blobWidth;
        this.blobHeight = blobHeight;
    }

    public IntProvider getBlobMass() {
        return this.blobMass;
    }

    public IntProvider getBlobWidth() {
        return this.blobWidth;
    }

    public IntProvider getBlobHeight() {
        return this.blobHeight;
    }
}
