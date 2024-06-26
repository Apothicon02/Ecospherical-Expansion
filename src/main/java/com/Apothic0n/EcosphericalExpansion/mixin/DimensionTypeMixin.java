package com.Apothic0n.EcosphericalExpansion.mixin;

import com.Apothic0n.EcosphericalExpansion.api.EcoMath;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalLong;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {

    @Shadow @Final private float ambientLight;

    @Shadow @Final private boolean hasSkyLight;

    @Shadow @Final private OptionalLong fixedTime;
    @Unique
    public float eco$closenessToNight = 1.0F;

    /**
     * @author Apothicon
     * @reason Remove ambient light at night.
     */
    @Inject(method = "ambientLight", at = @At("HEAD"), cancellable = true)
    public void ambientLight(CallbackInfoReturnable<Float> ci) {
        Minecraft minecraft = Minecraft.getInstance();
        float ambient = this.ambientLight;
        if (this.hasSkyLight) {
            ambient = (float) Math.min(eco$closenessToNight - 0.33, ambient);
        }
        float skyMultiplier = 1;
        if (minecraft.level != null && minecraft.player != null && minecraft.player.blockPosition().getY() < 10) {
            skyMultiplier = minecraft.level.getBrightness(LightLayer.SKY, minecraft.player.blockPosition()) / 15F;
        }
        if (minecraft.player != null && minecraft.player.hasEffect(MobEffects.NIGHT_VISION)) {
            ci.setReturnValue(0F);
        } else {
            ci.setReturnValue(ambient * skyMultiplier);
        }
    }

    @Inject(method = "timeOfDay", at = @At("HEAD"))
    public void timeOfDay(long time, CallbackInfoReturnable<Float> cir) {
        double d0 = Mth.frac((double)this.fixedTime.orElse(time) / 24000.0 - 0.25);
        double d1 = 0.5 - Math.cos(d0 * Math.PI) / 2.0;
        double newTime = (float)(d0 * 2.0 + d1) / 3.0F;
        eco$closenessToNight = EcoMath.getClosenessToNight(newTime);
    }
}
