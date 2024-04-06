package com.Apothic0n.EcosphericalExpansion.mixin;

import net.minecraft.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Util.class)
public class UtilMixin {
    @Inject(at = @At("HEAD"), method = "logAndPauseIfInIde(Ljava/lang/String;)V", cancellable = true)
    private static void logAndPauseIfInIde(String string, CallbackInfo ci) {
        if (string.contains("Detected setBlock in a far chunk")) {
            ci.cancel();
        }
    }
}