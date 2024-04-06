package com.Apothic0n.EcosphericalExpansion.core.objects;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EcoParticleTypes {
    private EcoParticleTypes() {}

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EcosphericalExpansion.MODID);

    public static final RegistryObject<SimpleParticleType> OAK_LEAVES = PARTICLE_TYPES.register("oak_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> DARK_OAK_LEAVES = PARTICLE_TYPES.register("dark_oak_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BIRCH_LEAVES = PARTICLE_TYPES.register("birch_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SPRUCE_LEAVES = PARTICLE_TYPES.register("spruce_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> JUNGLE_LEAVES = PARTICLE_TYPES.register("jungle_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ACACIA_LEAVES = PARTICLE_TYPES.register("acacia_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MANGROVE_LEAVES = PARTICLE_TYPES.register("mangrove_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> AZALEA_LEAVES = PARTICLE_TYPES.register("azalea_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FLOWERING_AZALEA_LEAVES = PARTICLE_TYPES.register("flowering_azalea_leaves", () -> new SimpleParticleType(false));

}
