package com.Apothic0n.EcosphericalExpansion.core.objects;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.Apothic0n.EcosphericalExpansion.core.objects.EcoBlocks.*;

public final class EcoItems extends Items {
    private EcoItems() {}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EcosphericalExpansion.MODID);

    public static final RegistryObject<Item> GLOWING_AMETHYST = ITEMS.register("glowing_amethyst", () ->
            new BlockItem(EcoBlocks.GLOWING_AMETHYST.get(), new Item.Properties()));
    public static final RegistryObject<Item> AQUATIC_LICHEN = ITEMS.register("aquatic_lichen", () ->
            new PlaceOnWaterBlockItem(EcoBlocks.AQUATIC_LICHEN.get(), new Item.Properties()));
    public static final RegistryObject<Item> DRY_GRASS = ITEMS.register("dry_grass", () ->
            new BlockItem(EcoBlocks.DRY_GRASS.get(), new Item.Properties()));

    public static final List<RegistryObject<Item>> wallItems = new ArrayList<>(List.of());
    public static final List<RegistryObject<Item>> stairItems = new ArrayList<>(List.of());
    public static final List<RegistryObject<Item>> slabItems = new ArrayList<>(List.of());
    public static final List<RegistryObject<Item>> pileItems = new ArrayList<>(List.of());

    public static void generateStairsSlabsWalls() {
        for (int i = 0; i < blocksWithStairsSlabsAndWalls.size(); i++) {
            Block baseBlock = blocksWithStairsSlabsAndWalls.get(i);
            wallItems.add(createWallItems(baseBlock));
            stairItems.add(createStairItems(baseBlock));
            slabItems.add(createSlabItems(baseBlock));
        }
        for (int i = 0; i < blocksWithWalls.size(); i++) {
            Block baseBlock = blocksWithWalls.get(i);
            wallItems.add(createWallItems(baseBlock));
        }
        for (int i = 0; i < blocksWithFragileWalls.size(); i++) {
            Block baseBlock = blocksWithFragileWalls.get(i);
            wallItems.add(createWallItems(baseBlock));
        }
        for (int i = 0; i < blocksWithPiles.size(); i++) {
            Block baseBlock = blocksWithPiles.get(i);
            pileItems.add(createPileItems(baseBlock));
        }
    }

    public static RegistryObject<Item> createPileItems(Block baseBlock) {
        RegistryObject<Block> block = getBlock(baseBlock, pileBlocks);
        return ITEMS.register(block.getId().toString().substring(4), () ->
                new BlockItem(block.get(), new Item.Properties())
        );
    }

    public static RegistryObject<Item> createWallItems(Block baseBlock) {
        RegistryObject<Block> block = getBlock(baseBlock, wallBlocks);
        return ITEMS.register(block.getId().toString().substring(4), () ->
                new BlockItem(block.get(), new Item.Properties())
        );
    }

    public static RegistryObject<Item> createStairItems(Block baseBlock) {
        RegistryObject<Block> block = getBlock(baseBlock, stairBlocks);
        return ITEMS.register(block.getId().toString().substring(4), () ->
                new BlockItem(block.get(), new Item.Properties())
        );
    }

    public static RegistryObject<Item> createSlabItems(Block baseBlock) {
        RegistryObject<Block> block = getBlock(baseBlock, slabBlocks);
        return ITEMS.register(block.getId().toString().substring(4), () ->
                new BlockItem(block.get(), new Item.Properties())
        );
    }

    public static RegistryObject<Block> getBlock(Block block, List<Map<Block, RegistryObject<Block>>> blockList) {
        for (int i = 0; i < blockList.size(); i++) {
            if (blockList.get(i).containsKey(block)) {
                return blockList.get(i).get(block);
            }
        }
        return EcoBlocks.AQUATIC_LICHEN; //this means it messed up
    }
}