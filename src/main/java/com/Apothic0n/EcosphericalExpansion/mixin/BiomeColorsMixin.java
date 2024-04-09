package com.Apothic0n.EcosphericalExpansion.mixin;

import com.Apothic0n.EcosphericalExpansion.api.EcoColorHelper;
import com.Apothic0n.EcosphericalExpansion.api.EcoDensityFunctions;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeColors.class)
public abstract class BiomeColorsMixin {

    /**
     * @author Apothicon
     * @reason Custom foliage tinting
     */
    @Inject(method = "getAverageFoliageColor", at = @At("HEAD"), cancellable = true)
    private static void getAverageFoliageColor(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos, CallbackInfoReturnable<Integer> ci) {
        if (EcoDensityFunctions.temperature != null && EcoDensityFunctions.humidity != null) {
            int x = blockPos.getX();
            int y = blockPos.getY();
            int z = blockPos.getZ();
            ci.setReturnValue(EcoColorHelper.tintFoliageOrGrass(blockAndTintGetter.getBlockState(blockPos), x, y, z, EcoDensityFunctions.temperature.compute(new DensityFunction.SinglePointContext(x, y, z)),
                    EcoDensityFunctions.humidity.compute(new DensityFunction.SinglePointContext(x, y, z)), true));
        }
    }
}
