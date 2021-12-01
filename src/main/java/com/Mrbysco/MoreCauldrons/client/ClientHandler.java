package com.mrbysco.morecauldrons.client;

import com.mrbysco.morecauldrons.init.CauldronReg;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ClientHandler {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        setCauldronLayer(CauldronRegistry.ACACIA_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.DARK_OAK_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.BIRCH_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.JUNGLE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.OAK_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.SPRUCE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.GOLD_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.DIAMOND_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.COBBLE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.BRICK_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.OBSIDIAN_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.STONE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.GRANITE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.POLISHED_GRANITE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.DIORITE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.POLISHED_DIORITE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.ANDESITE_CAULDRON, RenderType.cutout());
        setCauldronLayer(CauldronRegistry.POLISHED_ANDESITE_CAULDRON, RenderType.cutout());

        setCauldronLayer(CauldronRegistry.GLASS_CAULDRON, RenderType.cutout());
    }

    public static void setCauldronLayer(CauldronReg reg, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(reg.getCauldron().get(), type);
        ItemBlockRenderTypes.setRenderLayer(reg.getWaterCauldron().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(reg.getLavaCauldron().get(), type);
        ItemBlockRenderTypes.setRenderLayer(reg.getPowderSnowCauldron().get(), type);
    }


    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        for(CauldronReg reg : CauldronRegistry.CAULDRON_REGS) {
            Block block = reg.getWaterCauldron().get();
            colors.register((state, tintGetter, pos, tintIndex) -> {
                return tintGetter != null && pos != null ? BiomeColors.getAverageWaterColor(tintGetter, pos) : -1;
            }, block);
        }
    }
}
