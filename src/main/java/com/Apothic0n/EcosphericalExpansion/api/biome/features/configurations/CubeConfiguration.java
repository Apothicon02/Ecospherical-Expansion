package com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public class CubeConfiguration implements FeatureConfiguration {
    public static final Codec<CubeConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(BlockState.CODEC.listOf().fieldOf("validSurfaces").forGetter((v) -> {
            return v.validSurfaces;
        }), Codec.BOOL.fieldOf("doGradient").orElse(false).forGetter((v) -> {
            return v.doGradient;
        }), BlockState.CODEC.listOf().fieldOf("materials").forGetter((v) -> {
            return v.materials;
        }), IntProvider.codec(1, 32).fieldOf("cubeWidth").forGetter((v) -> {
            return v.cubeWidth;
        }), IntProvider.codec(1, 128).fieldOf("cubeHeight").forGetter((v) -> {
            return v.cubeHeight;
        }), Codec.BOOL.fieldOf("onCeiling").orElse(false).forGetter((v) -> {
            return v.onCeiling;
        })).apply(fields, CubeConfiguration::new);
    });
    public final List<BlockState> validSurfaces;
    public final boolean doGradient;
    public final List<BlockState> materials;
    private final IntProvider cubeWidth;
    private final IntProvider cubeHeight;
    public final boolean onCeiling;

    public CubeConfiguration(List<BlockState> validSurfaces, boolean doGradient, List<BlockState> materials, IntProvider cubeWidth, IntProvider cubeHeight, boolean onCeiling) {
        this.validSurfaces = validSurfaces;
        this.doGradient = doGradient;
        this.materials = materials;
        this.cubeWidth = cubeWidth;
        this.cubeHeight = cubeHeight;
        this.onCeiling = onCeiling;
    }

    public IntProvider getCubeWidth() {return this.cubeWidth;}
    public IntProvider getcubeHeight() {return this.cubeHeight;}
}
