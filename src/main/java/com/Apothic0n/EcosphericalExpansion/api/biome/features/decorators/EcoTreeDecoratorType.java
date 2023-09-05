package com.Apothic0n.EcosphericalExpansion.api.biome.features.decorators;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.foliage_placers.GiantPineFoliagePlacer;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public abstract class EcoTreeDecoratorType<P extends TreeDecorator> {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPE = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, EcosphericalExpansion.MODID);

    public static final RegistryObject<TreeDecoratorType<MushroomsDecorator>> MUSHROOMS = TREE_DECORATOR_TYPE.register("mushrooms", () ->
            new TreeDecoratorType<>(MushroomsDecorator.CODEC));

    public static final RegistryObject<TreeDecoratorType<CaveVineDecorator>> CAVE_VINES = TREE_DECORATOR_TYPE.register("cave_vines", () ->
            new TreeDecoratorType<>(CaveVineDecorator.CODEC));
    public static void register(IEventBus eventBus) {
        TREE_DECORATOR_TYPE.register(eventBus);
    }
}