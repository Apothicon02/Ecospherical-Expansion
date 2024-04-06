package com.Apothic0n.EcosphericalExpansion.core.events;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.Apothic0n.EcosphericalExpansion.api.EcoDensityFunctions;
import com.Apothic0n.EcosphericalExpansion.api.EcoMath;
import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EcosphericalExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {
    @SubscribeEvent
    public static void renderFog(ViewportEvent.RenderFog event) {
        Minecraft instance = Minecraft.getInstance();
        ClientLevel level = instance.level;
        if (event.getType() == FogType.NONE && level != null && level.dimension().location().toString().contains("overworld")) {
            float distance = event.getNearPlaneDistance() / getTimeOffset(level, 32);
            float y = (float) event.getCamera().getPosition().y();
            if (y < 48) {
                event.setFarPlaneDistance(event.getFarPlaneDistance() / (EcoMath.invLerp(y, 0.15F, 48, 16) + 1));
                distance = distance / (EcoMath.invLerp(y, 32, 48, 16) + 1);
            }
            event.setNearPlaneDistance(distance);
            event.setFogShape(FogShape.SPHERE);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void computeFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft instance = Minecraft.getInstance();
        ClientLevel level = instance.level;
        if (level != null && level.dimension().location().toString().contains("overworld") && event.getCamera().getFluidInCamera() == FogType.NONE) {
            float y = (float) event.getCamera().getPosition().y();
            float temp = (float) EcoDensityFunctions.temperature.compute(new DensityFunction.SinglePointContext((int) event.getCamera().getPosition().x(), (int) y, (int) event.getCamera().getPosition().z()));
            event.setRed(event.getRed() + (((temp - 0.8F) / 25)));
            event.setGreen(event.getGreen() - (((temp - 0.8F) / 20)));
            event.setBlue(event.getBlue() - (((temp - 0.8F) / 15)));
            if (y < 48) {
                float yScale = EcoMath.invLerp(Math.min(Math.max(y, 16), 48), 1, 48, 16);
                float invYScale = EcoMath.invLerp(Math.min(Math.max(y, 16), 48), 0.8F, 16, 48);
                event.setRed((Math.max(yScale, event.getRed()) - (Math.min(yScale, event.getRed()) * yScale) + Math.min(yScale, event.getRed())) * (invYScale + 0.2F));
                event.setGreen((Math.max(yScale, event.getGreen()) - (Math.min(yScale, event.getGreen()) * yScale) + Math.min(yScale, event.getGreen())) * (invYScale + 0.2F));
                event.setBlue((Math.max(yScale, event.getBlue()) - (Math.min(yScale, event.getBlue()) * yScale) + Math.min(yScale, event.getBlue())) * (invYScale + 0.2F));
            }
        } else if (event.getCamera().getFluidInCamera() == FogType.WATER) {
            event.setRed((float) EcoMath.getMiddleDouble(event.getRed(), 0.025));
            event.setGreen((float) EcoMath.getMiddleDouble(event.getGreen(), 0.175));
            event.setBlue((float) EcoMath.getMiddleDouble(event.getBlue(), 0.175));
        }
    }

    private static float getTimeOffset(ClientLevel level, int scale) {
        float offset = 3;
        long dayTime = level.getDayTime();
        if (dayTime >= 11800 && dayTime <= 13000) { //dusk
            offset += EcoMath.invLerp(dayTime, scale, 11800, 13000);
        } else if (dayTime > 13000 && dayTime < 22000) { //night
            offset += scale;
        } else if (dayTime >= 22000 && dayTime <= 23500) { //dawn
            offset += EcoMath.invLerp(dayTime, scale, 22000, 23500);
        }
        return offset;
    }
}
