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
     * @reason Prevents lava aquifers from generating above Y-116 on the amplified preset and slightly raises the lava aquifer altitude otherwise.
     */
    @Inject(method = "createFluidPicker", at = @At("HEAD"), cancellable = true)
    private static void createFluidPicker(NoiseGeneratorSettings p_249264_, CallbackInfoReturnable<Aquifer.FluidPicker> cir) {
        int y = -51;
        if (p_249264_.noiseSettings().minY() < -64) {
            y = -116;
        }
        Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(y, Blocks.KELP_PLANT.defaultBlockState());
        int i = p_249264_.seaLevel();
        Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(i, p_249264_.defaultFluid());
        int finalY = y;
        cir.setReturnValue((p_224274_, p_224275_, p_224276_) -> {
            return p_224275_ < Math.min(finalY, i) ? aquifer$fluidstatus : aquifer$fluidstatus1;
        });
    }
}
