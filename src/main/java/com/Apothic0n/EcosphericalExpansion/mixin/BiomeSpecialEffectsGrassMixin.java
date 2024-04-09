package com.Apothic0n.EcosphericalExpansion.mixin;

import com.Apothic0n.EcosphericalExpansion.api.EcoDensityFunctions;
import com.Apothic0n.EcosphericalExpansion.api.EcoColorHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.biome.BiomeSpecialEffects$GrassColorModifier$1")
public class BiomeSpecialEffectsGrassMixin {

    /**
     * @author Apothicon
     * @reason Adds grass discoloration.
     */
    @Inject(at = @At("RETURN"), method = "modifyColor", cancellable = true)
    private void modifyColor(double x, double z, int unusedColor, CallbackInfoReturnable<Integer> cir) {
        if (EcoDensityFunctions.temperature != null && EcoDensityFunctions.humidity != null) {
            cir.setReturnValue(EcoColorHelper.tintFoliageOrGrass(Blocks.GRASS_BLOCK.defaultBlockState(), (int) x, 0, (int) z, EcoDensityFunctions.temperature.compute(new DensityFunction.SinglePointContext((int) x, 0, (int) z)),
                    EcoDensityFunctions.humidity.compute(new DensityFunction.SinglePointContext((int) x, 0, (int) z)), false));
        }
    }
}