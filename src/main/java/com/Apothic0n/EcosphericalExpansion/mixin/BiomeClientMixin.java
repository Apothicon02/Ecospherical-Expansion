package com.Apothic0n.EcosphericalExpansion.mixin;

import com.Apothic0n.EcosphericalExpansion.api.EcoDensityFunctions;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import org.spongepowered.asm.mixin.*;

@Mixin(Biome.class)
public abstract class BiomeClientMixin {
    @Shadow
    @Final
    private BiomeSpecialEffects specialEffects;
    @Shadow protected abstract int getGrassColorFromTexture();

    @Unique
    private int biox$getGrassColorFromTexture() {
        if (EcoDensityFunctions.temperature != null) {
            return GrassColor.get(1D, 1D);
        } else {
            return getGrassColorFromTexture();
        }
    }

    /**
     * @author Apothicon
     * @reason Infinite color blending not dependant on biomes.
     */
    @Overwrite
    public int getGrassColor(double posX, double posZ) {
        int i = this.specialEffects.getGrassColorOverride().orElseGet(this::biox$getGrassColorFromTexture);
        return this.specialEffects.getGrassColorModifier().modifyColor(posX, posZ, i);
    }
}
