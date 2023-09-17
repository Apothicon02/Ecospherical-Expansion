package com.Apothic0n.EcosphericalExpansion;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.decorators.EcoTreeDecoratorType;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.foliage_placers.EcoFoliagePlacerType;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.trunk_placers.EcoTrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EcosphericalExpansion.MODID)
public class EcosphericalExpansion {
    public static final String MODID = "eco";

    public EcosphericalExpansion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EcoFeatureRegistry.register(eventBus);
        EcoTrunkPlacerType.register(eventBus);
        EcoFoliagePlacerType.register(eventBus);
        EcoTreeDecoratorType.register(eventBus);
    }
}