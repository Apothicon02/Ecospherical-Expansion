package com.Apothic0n.EcosphericalExpansion.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    /**
     * @author Apothic0n
     * @reason Shifts lava aquifers down to Y-116.
     */
    @Inject(method = "createFluidPicker", at = @At("HEAD"), cancellable = true)
    private static void createFluidPicker(NoiseGeneratorSettings noiseGeneratorSettings, CallbackInfoReturnable<Aquifer.FluidPicker> ci) {
        int y = -116;
        Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(y, Blocks.LAVA.defaultBlockState());
        int sea = noiseGeneratorSettings.seaLevel();
        Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(sea, noiseGeneratorSettings.defaultFluid());
        ci.setReturnValue((p_224274_, p_224275_, p_224276_) -> {
            return p_224275_ < Math.min(y, sea) ? aquifer$fluidstatus : aquifer$fluidstatus1;
        });
    }
}
