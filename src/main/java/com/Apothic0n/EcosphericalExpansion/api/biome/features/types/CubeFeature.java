package com.Apothic0n.EcosphericalExpansion.api.biome.features.types;

import com.Apothic0n.EcosphericalExpansion.api.biome.features.configurations.CubeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;
import java.util.Random;

public class CubeFeature extends Feature<CubeConfiguration> {
    public CubeFeature(Codec<CubeConfiguration> pContext) {super(pContext);}

    public boolean place(FeaturePlaceContext<CubeConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        Random random = pContext.random();
        CubeConfiguration config = pContext.config();
        List<BlockState> validSurfaces = config.validSurfaces;
        Boolean doGradient = config.doGradient;
        List<BlockState> materials = config.materials;
        BlockState solidMaterial = (BlockState) materials.toArray()[random.nextInt(materials.size())];
        Integer cubeWidth = config.getCubeWidth().sample(random);
        Integer cubeHeight = config.getcubeHeight().sample(random);
        Boolean onCeiling = config.onCeiling;
        if (validSurfaces.contains(worldgenlevel.getBlockState(blockpos.offset(0, getSurfaceDirection(onCeiling), 0)))) {
            for (int currentLayer = 0; currentLayer <= cubeHeight;) {
                if (doGradient) {
                    for (int stage = materials.size()-1; stage >= 0;) {
                        currentLayer++;
                        createLayer(currentLayer, materials.get(stage), cubeWidth, onCeiling, blockpos, worldgenlevel);
                        if (currentLayer > cubeHeight/(stage+1)) {
                            stage--;
                        }
                    }
                } else {
                    currentLayer++;
                    createLayer(currentLayer, solidMaterial, cubeWidth, onCeiling, blockpos, worldgenlevel);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    private void createLayer(Integer layer, BlockState material, Integer width, Boolean onCeiling, BlockPos pos, WorldGenLevel level) {
        for (int w = -width; w <= width; ++w) {
            for (int l = -width; l <= width; ++l) {
                level.setBlock(pos.offset(w, -getSurfaceDirection(onCeiling)*layer, l), material, 2);
            }
        }
    }

    private int getSurfaceDirection(Boolean bool) {
        if (bool) {
            return 1;
        }
        return -1;
    }
}
