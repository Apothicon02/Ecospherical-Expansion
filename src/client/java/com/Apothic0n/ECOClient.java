package com.Apothic0n;

import com.Apothic0n.core.objects.EcoBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ECOClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), EcoBlocks.AMETHYST_VINES, EcoBlocks.AMETHYST_VINES_PLANT, EcoBlocks.GLOWING_AMETHYST);
    }
}
