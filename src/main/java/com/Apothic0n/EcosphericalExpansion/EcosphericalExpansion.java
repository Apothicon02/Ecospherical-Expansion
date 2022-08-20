package com.Apothic0n.EcosphericalExpansion;

import com.Apothic0n.EcosphericalExpansion.api.biome.*;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.EcosphericalExpansion.core.objects.ECOBlocks;
import com.Apothic0n.EcosphericalExpansion.core.objects.ECOItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(EcosphericalExpansion.MODID)
public class EcosphericalExpansion {
    public static final String MODID = "eco";

    public EcosphericalExpansion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        EcoFeatureRegistry.register(eventBus);
        ECOBlocks.BLOCKS.register(eventBus);
        ECOItems.ITEMS.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.setDefaultSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ECOSurfaceRuleData.makeRules());
            Regions.register(new GeodeialCavesProvider(new ResourceLocation(MODID, "geodeial_caves_biome_provider"), RegionType.OVERWORLD, 6));
            Regions.register(new MoltenSeepingCavesProvider(new ResourceLocation(MODID, "molten_seeping_caves_biome_provider"), RegionType.OVERWORLD, 9));
            //Regions.register(new ObsidianSpirallingCavesProvider(new ResourceLocation(MODID, "obsidian_spiralling_caves_biome_provider"), RegionType.OVERWORLD, 4));
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ECOBlocks.fixBlockRenderLayers();
    }
}