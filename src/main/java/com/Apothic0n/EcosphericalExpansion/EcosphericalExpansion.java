package com.Apothic0n.EcosphericalExpansion;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.decorators.EcoTreeDecoratorType;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.foliage_placers.EcoFoliagePlacerType;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.trunk_placers.EcoTrunkPlacerType;
import com.Apothic0n.EcosphericalExpansion.core.events.EntityLoadEvent;
import com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EcosphericalExpansion.MODID)
public class EcosphericalExpansion {
    public static final String MODID = "eco";

    public EcosphericalExpansion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        EcoBlocks.BLOCKS.register(eventBus);
        EcoFeatureRegistry.register(eventBus);
        EcoTrunkPlacerType.register(eventBus);
        EcoFoliagePlacerType.register(eventBus);
        EcoTreeDecoratorType.register(eventBus);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EcoBlocks.fixBlockRenderLayers();
    }
}