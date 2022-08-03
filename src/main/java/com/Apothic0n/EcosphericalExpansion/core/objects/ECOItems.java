package com.Apothic0n.EcosphericalExpansion.core.objects;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ECOItems extends Items {
    private ECOItems() {}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EcosphericalExpansion.MODID);

    //Block Items
    public static final RegistryObject<Item> VOID_BERRIES = ITEMS.register("void_berries", () ->
            new ItemNameBlockItem(ECOBlocks.VOID_VINES.get(),
                    new Item.Properties().food(Foods.GLOW_BERRIES).tab(CreativeModeTab.TAB_FOOD)));
}
