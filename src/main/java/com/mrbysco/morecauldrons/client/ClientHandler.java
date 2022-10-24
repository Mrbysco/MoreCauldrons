package com.mrbysco.morecauldrons.client;

import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.CauldronBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(CauldronRegistry.ACACIA_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.DARK_OAK_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.BIRCH_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.JUNGLE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.OAK_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.SPRUCE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.GOLD_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.DIAMOND_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.COBBLE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.BRICK_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.OBSIDIAN_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.STONE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.GRANITE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.POLISHED_GRANITE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.DIORITE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.POLISHED_DIORITE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.ANDESITE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CauldronRegistry.POLISHED_ANDESITE_CAULDRON.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(CauldronRegistry.GLASS_CAULDRON.get(), RenderType.cutout());
    }


    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        for(RegistryObject<Block> blockObject : CauldronRegistry.BLOCKS.getEntries()) {
            Block block = blockObject.get();
            if(block instanceof CauldronBlock) {
                colors.register((p_228060_0_, p_228060_1_, p_228060_2_, p_228060_3_) -> {
                    return p_228060_1_ != null && p_228060_2_ != null ? BiomeColors.getAverageWaterColor(p_228060_1_, p_228060_2_) : -1;
                }, block);
            }
        }
    }
}
