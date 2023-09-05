package com.Apothic0n.EcosphericalExpansion.mixin;

import com.Apothic0n.EcosphericalExpansion.core.events.EcoEventHandler;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.server.level.ServerLevel$EntityCallbacks")
abstract class ServerLevelEntityCallbacksMixin {
    @Inject(method = "onTrackingStart(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    private void invokeEntityLoadEvent(Entity entity, CallbackInfo ci) {
        EcoEventHandler.entityLoadEvent(entity, entity.level());
    }
}