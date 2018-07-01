package com.Mrbysco.MoreCauldrons.init;

import java.util.ArrayList;

import com.Mrbysco.MoreCauldrons.MoreCauldrons;
import com.Mrbysco.MoreCauldrons.blocks.BlockBrickCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockCobbleCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockDiamondCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockGlassCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockGoldCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockObsidianCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockStoneCauldron;
import com.Mrbysco.MoreCauldrons.blocks.BlockWoodenCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedBrickCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedCobbleCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedDiamondCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedGlassCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedGoldCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedObsidianCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedStoneCauldron;
import com.Mrbysco.MoreCauldrons.blocks.inspirations.BlockEnhancedWoodenCauldron;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class ModBlocks {
	public static Block acacia_cauldron;
	public static Block big_oak_cauldron;
	public static Block birch_cauldron;
	public static Block jungle_cauldron;
	public static Block oak_cauldron;
	public static Block spruce_cauldron;
	
	public static Block gold_cauldron;
	public static Block diamond_cauldron;
	public static Block cobble_cauldron;
	public static Block brick_cauldron;
	public static Block obsidian_cauldron;
	public static Block glass_cauldron;
	
	public static Block stone_cauldron;
	public static Block granite_cauldron;
	public static Block smooth_granite_cauldron;
	public static Block diorite_cauldron;
	public static Block smooth_diorite_cauldron;
	public static Block andesite_cauldron;
	public static Block smooth_andesite_cauldron;
	
	public static ArrayList<Block> BLOCKS = new ArrayList<>();
	 
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
	    IForgeRegistry<Block> registry = event.getRegistry();
	    
	    if(MoreCauldrons.inspirationsLoaded)
	    {
	    	registerCompat();
	    }
	    else
	    {
	    	acacia_cauldron = registerBlock(new BlockWoodenCauldron("acacia_cauldron", MapColor.ADOBE));
	    	big_oak_cauldron = registerBlock(new BlockWoodenCauldron("big_oak_cauldron", MapColor.BROWN));
	    	birch_cauldron = registerBlock(new BlockWoodenCauldron("birch_cauldron", MapColor.SAND));
	    	jungle_cauldron = registerBlock(new BlockWoodenCauldron("jungle_cauldron", MapColor.DIRT));
	    	oak_cauldron = registerBlock(new BlockWoodenCauldron("oak_cauldron", MapColor.WOOD));
	    	spruce_cauldron = registerBlock(new BlockWoodenCauldron("spruce_cauldron", MapColor.OBSIDIAN));
	    	
	    	gold_cauldron = registerBlock(new BlockGoldCauldron("gold_cauldron"));
	    	diamond_cauldron = registerBlock(new BlockDiamondCauldron("diamond_cauldron"));
		    cobble_cauldron = registerBlock(new BlockCobbleCauldron("cobble_cauldron"));
		    glass_cauldron = registerBlock(new BlockGlassCauldron("glass_cauldron"));
		    obsidian_cauldron = registerBlock(new BlockObsidianCauldron("obsidian_cauldron"));
		    brick_cauldron = registerBlock(new BlockBrickCauldron("brick_cauldron"));
		    
		    stone_cauldron = registerBlock(new BlockStoneCauldron("stone_cauldron", MapColor.STONE));
		    granite_cauldron = registerBlock(new BlockStoneCauldron("granite_cauldron", MapColor.DIRT));
		    smooth_granite_cauldron = registerBlock(new BlockStoneCauldron("smooth_granite_cauldron", MapColor.DIRT));
		    diorite_cauldron = registerBlock(new BlockStoneCauldron("diorite_cauldron", MapColor.QUARTZ));
		    smooth_diorite_cauldron = registerBlock(new BlockStoneCauldron("smooth_diorite_cauldron", MapColor.QUARTZ));
		    andesite_cauldron = registerBlock(new BlockStoneCauldron("andesite_cauldron", MapColor.STONE));
		    smooth_andesite_cauldron = registerBlock(new BlockStoneCauldron("smooth_andesite_cauldron", MapColor.STONE));
	    }
	    
	    registry.registerAll(BLOCKS.toArray(new Block[0]));
	}
	
	@Optional.Method(modid = "inspirations")
	public static void registerCompat()
	{
    	if(knightminer.inspirations.common.Config.enableExtendedCauldron)
    	{
    		acacia_cauldron = registerBlock(new BlockEnhancedWoodenCauldron("acacia_cauldron", MapColor.ADOBE));
	    	big_oak_cauldron = registerBlock(new BlockEnhancedWoodenCauldron("big_oak_cauldron", MapColor.BROWN));
	    	birch_cauldron = registerBlock(new BlockEnhancedWoodenCauldron("birch_cauldron", MapColor.SAND));
	    	jungle_cauldron = registerBlock(new BlockEnhancedWoodenCauldron("jungle_cauldron", MapColor.DIRT));
	    	oak_cauldron = registerBlock(new BlockEnhancedWoodenCauldron("oak_cauldron", MapColor.WOOD));
	    	spruce_cauldron = registerBlock(new BlockEnhancedWoodenCauldron("spruce_cauldron", MapColor.OBSIDIAN));

	    	gold_cauldron = registerBlock(new BlockEnhancedGoldCauldron("gold_cauldron"));
	    	diamond_cauldron = registerBlock(new BlockEnhancedDiamondCauldron("diamond_cauldron"));
		    cobble_cauldron = registerBlock(new BlockEnhancedCobbleCauldron("cobble_cauldron"));
		    glass_cauldron = registerBlock(new BlockEnhancedGlassCauldron("glass_cauldron"));
		    obsidian_cauldron = registerBlock(new BlockEnhancedObsidianCauldron("obsidian_cauldron"));
		    brick_cauldron = registerBlock(new BlockEnhancedBrickCauldron("brick_cauldron"));

		    stone_cauldron = registerBlock(new BlockEnhancedStoneCauldron("stone_cauldron", MapColor.STONE));
		    granite_cauldron = registerBlock(new BlockEnhancedStoneCauldron("granite_cauldron", MapColor.DIRT));
		    smooth_granite_cauldron = registerBlock(new BlockEnhancedStoneCauldron("smooth_granite_cauldron", MapColor.DIRT));
		    diorite_cauldron = registerBlock(new BlockEnhancedStoneCauldron("diorite_cauldron", MapColor.QUARTZ));
		    smooth_diorite_cauldron = registerBlock(new BlockEnhancedStoneCauldron("smooth_diorite_cauldron", MapColor.QUARTZ));
		    andesite_cauldron = registerBlock(new BlockEnhancedStoneCauldron("andesite_cauldron", MapColor.STONE));
		    smooth_andesite_cauldron = registerBlock(new BlockEnhancedStoneCauldron("smooth_andesite_cauldron", MapColor.STONE));
    	}
	}
	
	public static <T extends Block> T registerBlock(T block)
    {
        return registerBlock(block, new ItemBlock(block));
    }
    
    public static <T extends Block> T registerBlock(T block, ItemBlock item)
    {
    	item.setRegistryName(block.getRegistryName());
    	ModItems.ITEMS.add(item);
        BLOCKS.add(block);
        return block;
    }
}
