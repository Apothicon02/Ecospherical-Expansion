package com.Apothic0n.core.objects;

import com.Apothic0n.EcosphericalExpansion;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Display;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.Blocks.AMETHYST_CLUSTER;

public final class EcoBlocks {
    public static final Block AMETHYST_VINES = new AmethystVinesBlock(BlockBehaviour.Properties.copy(AMETHYST_CLUSTER)
                    .randomTicks().strength(0.2F).noCollission().sound(SoundType.MEDIUM_AMETHYST_BUD));

    public static final Block AMETHYST_VINES_PLANT = new AmethystVinesBlock(BlockBehaviour.Properties.copy(AMETHYST_CLUSTER)
                    .noCollission().strength(0.2F).sound(SoundType.LARGE_AMETHYST_BUD));

    public static final Block GLOWING_AMETHYST = new GlowingAmethystBlock(3, 4, BlockBehaviour.Properties.copy(AMETHYST_CLUSTER)
                    .lightLevel(brightness -> {return 11;}));

    public static void register() {
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EcosphericalExpansion.MODID, "amethyst_vines"), AMETHYST_VINES);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EcosphericalExpansion.MODID, "amethyst_vines_plant"), AMETHYST_VINES_PLANT);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EcosphericalExpansion.MODID, "glowing_amethyst"), GLOWING_AMETHYST);
    }
}