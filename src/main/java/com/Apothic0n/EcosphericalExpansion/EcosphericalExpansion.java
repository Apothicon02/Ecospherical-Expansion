package com.Apothic0n.EcosphericalExpansion;

import com.Apothic0n.EcosphericalExpansion.api.EcoDensityFunctions;
import com.Apothic0n.EcosphericalExpansion.api.EcoJsonReader;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.EcoFeatureRegistry;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.decorators.EcoTreeDecoratorType;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.foliage_placers.EcoFoliagePlacerType;
import com.Apothic0n.EcosphericalExpansion.api.biome.features.trunk_placers.EcoTrunkPlacerType;
import com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks;
import com.Apothic0n.EcosphericalExpansion.core.objects.EcoItems;
import com.Apothic0n.EcosphericalExpansion.core.objects.EcoParticleTypes;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
@Mod(EcosphericalExpansion.MODID)
public class EcosphericalExpansion {
    public static final String MODID = "eco";

    public EcosphericalExpansion() throws Exception {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        EcoJsonReader.main();
        EcoDensityFunctions.register(eventBus);
        if (!EcoJsonReader.serverSidedOnlyMode) {
            EcoParticleTypes.PARTICLE_TYPES.register(eventBus);
            EcoBlocks.BLOCKS.register(eventBus);
            EcoBlocks.generateStairsSlabsWalls();
            EcoItems.ITEMS.register(eventBus);
            EcoItems.generateStairsSlabsWalls();
        }
        EcoFeatureRegistry.register(eventBus);
        EcoTrunkPlacerType.register(eventBus);
        EcoFoliagePlacerType.register(eventBus);
        EcoTreeDecoratorType.register(eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            addLight(Blocks.SUNFLOWER.getStateDefinition().getPossibleStates(), 2);
            addLight(Blocks.SPORE_BLOSSOM.getStateDefinition().getPossibleStates(), 4);
            addLight(Blocks.BLUE_ICE.getStateDefinition().getPossibleStates(), 7);
            addLight(Blocks.TALL_GRASS.getStateDefinition().getPossibleStates(), 8);
            addLight(Blocks.TORCHFLOWER.getStateDefinition().getPossibleStates(), 9);
            addLight(Blocks.TORCHFLOWER_CROP.getStateDefinition().getPossibleStates(), 9);
            addLight(Blocks.RED_MUSHROOM.getStateDefinition().getPossibleStates(), 9);
            addLight(Blocks.RED_MUSHROOM_BLOCK.getStateDefinition().getPossibleStates(), 13);
            addLight(Blocks.MAGMA_BLOCK.getStateDefinition().getPossibleStates(), 13);
        });
    }

    private void addLight(ImmutableList<BlockState> blockStates, int light) {
        for (int i = 0; i < blockStates.size(); i++) {
            blockStates.get(i).lightEmission = light;
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {EcoBlocks.fixBlockRenderLayers();}
}