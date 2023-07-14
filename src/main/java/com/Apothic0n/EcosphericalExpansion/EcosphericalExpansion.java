package com.Apothic0n.EcosphericalExpansion;

import com.Apothic0n.EcosphericalExpansion.api.biome.*;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.SurfaceRuleManager;

@Mod(EcosphericalExpansion.MODID)
public class EcosphericalExpansion {
    public static final String MODID = "eco";

    public EcosphericalExpansion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        EcoFeatureRegistry.register(eventBus);
        EcoBlocks.BLOCKS.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.setDefaultSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ECOSurfaceRuleData.makeRules());
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EcoBlocks.fixBlockRenderLayers();
    }
}