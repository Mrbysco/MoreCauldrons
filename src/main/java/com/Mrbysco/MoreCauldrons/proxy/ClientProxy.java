package com.Mrbysco.MoreCauldrons.proxy;

import com.Mrbysco.MoreCauldrons.ModReference;
import com.Mrbysco.MoreCauldrons.MoreCauldrons;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.ICauldron;
import com.Mrbysco.MoreCauldrons.init.ModBlocks;
import com.Mrbysco.MoreCauldrons.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void Preinit() {
		if(MoreCauldrons.inspirationsLoaded)
			MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void Init() {
		
	}
	
	@Optional.Method(modid = "inspirations")
	@SubscribeEvent
	public void registerBlockColors(ColorHandlerEvent.Block event) {
		BlockColors blockColors = event.getBlockColors();
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block instanceof ICauldron)
			{
				blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> {
					if(tintIndex == 1) {
						TileEntity te = world.getTileEntity(pos);
						if(te instanceof knightminer.inspirations.recipes.tileentity.TileCauldron) {
							return ((knightminer.inspirations.recipes.tileentity.TileCauldron) te).getColor();
						}
					}

					return -1;
				}, block);
			}
		}
	}
	
	@Optional.Method(modid = "inspirations")
	protected static void replaceTexturedModel(ModelBakeEvent event, ModelResourceLocation location, String key, boolean item) {
		IModel model = ModelLoaderRegistry.getModelOrLogError(location, "Error loading model for " + location);
		IBakedModel standard = event.getModelRegistry().getObject(location);
		IBakedModel finalModel = new knightminer.inspirations.shared.client.TextureModel(standard, model, DefaultVertexFormats.BLOCK, key, item);

		event.getModelRegistry().putObject(location, finalModel);
	}
	
	@Optional.Method(modid = "inspirations")
	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event) {
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block == null)
				return;
			
			boolean boiling = false;
			do {
				replaceTexturedModel(event, new ModelResourceLocation(getBiggerResource(block.getRegistryName().getResourcePath(), "_inspirations"), String.format("boiling=%s,contents=fluid,level=empty", boiling)), "water", false);
				for(int i = (knightminer.inspirations.common.Config.enableBiggerCauldron ? 0 : 1); i <= 3; i++) {
					replaceTexturedModel(event, new ModelResourceLocation(getBiggerResource(block.getRegistryName().getResourcePath(), "_inspirations"), String.format("boiling=%s,contents=fluid,level=%s", boiling, i)), "water", false);
				}
				boiling = !boiling;
			} while(boiling);
		}
	}
	
	@SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event)
    {
		for(Item item : ModItems.ITEMS)
        {
			if(MoreCauldrons.inspirationsLoaded)
        	{
				InspirationsBetterItem(event, item);
        	}
			else
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
        }
		
        for(Block block : ModBlocks.BLOCKS)
        {
        	Item item = Item.getItemFromBlock(block);
        	
        	if(MoreCauldrons.inspirationsLoaded)
        	{
        		InspirationsBlockRender(event, block);
        	}
        	else
        	{
            	ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        	}
        }
    }
	
	@Optional.Method(modid = "inspirations")
	public static void InspirationsBlockRender(ModelRegistryEvent event, Block block)
	{
		ModelLoader.setCustomStateMapper(block, new knightminer.inspirations.recipes.RecipesClientProxy.CauldronStateMapper(getBiggerResource(block.getRegistryName().getResourcePath(), "_inspirations")));
	}
	
	@Optional.Method(modid = "inspirations")
	public static void InspirationsBetterItem(ModelRegistryEvent event, Item item)
	{
		if(knightminer.inspirations.common.Config.betterCauldronItem) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getBiggerResource(item.getRegistryName().getResourcePath(), "_item"), "inventory"));
		}
		else
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
	
	public static ResourceLocation getBiggerResource(String input, String addition)
	{
		return new ResourceLocation(ModReference.MOD_ID, input + addition);
	}
}
