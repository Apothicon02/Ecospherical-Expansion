package com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class FloodConfiguration implements FeatureConfiguration {
    public static final Codec<FloodConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(BlockStateProvider.CODEC.fieldOf("material").forGetter((v) -> {
            return v.material;
        }), BlockStateProvider.CODEC.optionalFieldOf("frozenMaterial", BlockStateProvider.simple(Blocks.VOID_AIR.defaultBlockState())).forGetter((v) -> {
            return v.frozenMaterial;
        }), IntProvider.codec(-420, 318).fieldOf("elevation").forGetter((v) -> {
            return v.elevation;
        }), Codec.BOOL.fieldOf("frozen").orElse(false).forGetter((v) -> {
            return v.frozen;
        })).apply(fields, FloodConfiguration::new);
    });
    public final BlockStateProvider material;
    public final BlockStateProvider frozenMaterial;
    private final IntProvider elevation;
    public final Boolean frozen;

    public FloodConfiguration(BlockStateProvider material, BlockStateProvider frozenMaterial, IntProvider elevation, Boolean frozen) {
        this.material = material;
        this.frozenMaterial = frozenMaterial;
        this.elevation = elevation;
        this.frozen = frozen;
    }

    public IntProvider getElevation() {return this.elevation;}

}
