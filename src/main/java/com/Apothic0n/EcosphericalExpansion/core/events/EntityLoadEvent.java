package com.Apothic0n.EcosphericalExpansion.core.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public final class EntityLoadEvent extends Event {
    public EntityLoadEvent(Entity entity, Level level) {
        this.entity = entity;
        this.level = level;
    }
    public final Entity entity;
    public final Level level;
}