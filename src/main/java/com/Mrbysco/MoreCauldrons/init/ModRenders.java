package com.Mrbysco.MoreCauldrons.init;

import com.Mrbysco.MoreCauldrons.ModReference;
import com.Mrbysco.MoreCauldrons.MoreCauldrons;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ModRenders {

	@SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event)
    {
		for(Item item : ModItems.ITEMS)
        {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
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
		if(knightminer.inspirations.common.Config.enableBiggerCauldron) {
			ModelLoader.setCustomStateMapper(block, new knightminer.inspirations.library.client.NameStateMapper(getBiggerResource(block.getRegistryName().getResourcePath(), "_inspirations_bigger"), BlockCauldron.LEVEL));
		} 
		else 
		{
			ModelLoader.setCustomStateMapper(block, new knightminer.inspirations.library.client.NameStateMapper(getBiggerResource(block.getRegistryName().getResourcePath(), "_inspirations")));
		}
	}
	
	public static ResourceLocation getBiggerResource(String input, String addition)
	{
		return new ResourceLocation(ModReference.MOD_ID, input + addition);
	}
}
