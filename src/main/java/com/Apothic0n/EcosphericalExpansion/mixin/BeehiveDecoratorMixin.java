package com.Apothic0n.EcosphericalExpansion.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BeehiveDecorator.class)
public class BeehiveDecoratorMixin {

    /**
     * @author Apothic0n
     * @reason Fixes a very rare vanilla bug where beehives crash the game when generated.
     */
    @Inject(method = "place", at = @At("HEAD"), cancellable = true)
    public void place(TreeDecorator.Context p_226019_, CallbackInfo ci) {
        List<BlockPos> list = p_226019_.leaves();
        List<BlockPos> list1 = p_226019_.logs();
        if (list.isEmpty() || list1.isEmpty()) {
            ci.cancel();
        }
    }
}
