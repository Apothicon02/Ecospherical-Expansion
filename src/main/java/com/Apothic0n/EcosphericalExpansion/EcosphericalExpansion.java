package com.Apothic0n.EcosphericalExpansion;

import com.Apothic0n.EcosphericalExpansion.api.biome.ECOBiomeProvider;
import com.Apothic0n.EcosphericalExpansion.config.CommonConfig;
import com.Apothic0n.EcosphericalExpansion.config.Configs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.BiomeProviders;

@Mod(EcosphericalExpansion.MODID)
public class EcosphericalExpansion {
    public static final String MODID = "eco";

    public EcosphericalExpansion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        Configs.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BiomeProviders.register(new ECOBiomeProvider(new ResourceLocation(MODID, "biome_provider"), CommonConfig.weight.get()));
        });
    }
}
