package com.Apothic0n.EcosphericalExpansion.core.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public final class EcoEventHandler {
    public static EntityLoadEvent entityLoadEvent(Entity entity, Level level) {
        EntityLoadEvent event = new EntityLoadEvent(entity, level);
        MinecraftForge.EVENT_BUS.post(event);
        return event;
    }
}
