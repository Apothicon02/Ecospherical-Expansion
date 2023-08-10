package com.Apothic0n.mixin;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import org.spongepowered.asm.mixin.*;

@Mixin(value = Biome.class, priority = 69420)
public abstract class BiomeMixin {
    @Shadow
    @Final
    private BiomeSpecialEffects specialEffects;

    @Shadow protected abstract int getGrassColorFromTexture();

    @Overwrite
    public int getGrassColor(double posX, double posZ) {
        int i = this.specialEffects.getGrassColorOverride().orElseGet(this::getGrassColorFromTexture);
        return this.specialEffects.getGrassColorModifier().modifyColor(posX, posZ, i);
    }
}
