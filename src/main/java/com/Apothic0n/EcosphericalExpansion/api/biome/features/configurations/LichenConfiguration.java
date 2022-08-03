package com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.Collections;
import java.util.List;

public class LichenConfiguration implements FeatureConfiguration {
    public static final Codec<LichenConfiguration> CODEC = RecordCodecBuilder.create((fields) -> {
        return fields.group(BlockState.CODEC.fieldOf("material").forGetter((v) -> {
            return v.material;
        }), Codec.intRange(1, 64).fieldOf("search_range").orElse(10).forGetter((v) -> {
            return v.searchRange;
        }), Codec.BOOL.fieldOf("can_place_on_floor").orElse(false).forGetter((v) -> {
            return v.canPlaceOnFloor;
        }), Codec.BOOL.fieldOf("can_place_on_ceiling").orElse(false).forGetter((v) -> {
            return v.canPlaceOnCeiling;
        }), Codec.BOOL.fieldOf("can_place_on_wall").orElse(false).forGetter((v) -> {
            return v.canPlaceOnWall;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_spreading").orElse(0.5F).forGetter((v) -> {
            return v.chanceOfSpreading;
        }), Registry.BLOCK.byNameCodec().listOf().fieldOf("can_be_placed_on").forGetter((v) -> {
            return v.canBePlacedOn;
        })).apply(fields, LichenConfiguration::new);
    });
    public final int searchRange;
    public final boolean canPlaceOnFloor;
    public final boolean canPlaceOnCeiling;
    public final boolean canPlaceOnWall;
    public final float chanceOfSpreading;
    public final List<Block> canBePlacedOn;
    public final List<Direction> validDirections;
    public final BlockState material;

    public LichenConfiguration(BlockState material, int p_160879_, boolean p_160880_, boolean p_160881_, boolean p_160882_, float p_160883_, List<Block> p_160884_) {
        this.searchRange = p_160879_;
        this.canPlaceOnFloor = p_160880_;
        this.canPlaceOnCeiling = p_160881_;
        this.canPlaceOnWall = p_160882_;
        this.chanceOfSpreading = p_160883_;
        this.canBePlacedOn = p_160884_;
        List<Direction> list = Lists.newArrayList();
        if (p_160881_) {
            list.add(Direction.UP);
        }

        if (p_160880_) {
            list.add(Direction.DOWN);
        }

        if (p_160882_) {
            Direction.Plane.HORIZONTAL.forEach(list::add);
        }

        this.validDirections = Collections.unmodifiableList(list);
        this.material = material;
    }
}