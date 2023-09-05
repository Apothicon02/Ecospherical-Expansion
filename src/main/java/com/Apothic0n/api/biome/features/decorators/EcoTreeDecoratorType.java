package com.Apothic0n.api.biome.features.decorators;

import com.Apothic0n.EcosphericalExpansion;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.treedecorators.*;

public abstract class EcoTreeDecoratorType<P extends TreeDecorator> {
    public static final TreeDecoratorType<MushroomsDecorator> MUSHROOMS = EcoTreeDecoratorType.register("mushrooms", MushroomsDecorator.CODEC);

    public static final TreeDecoratorType<CaveVineDecorator> CAVE_VINES = EcoTreeDecoratorType.register("cave_vines", CaveVineDecorator.CODEC);

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String key, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, new ResourceLocation(EcosphericalExpansion.MODID, key), new TreeDecoratorType<P>(codec));
    }

    public static void init() {

    }
}