package com.Mrbysco.MoreCauldrons.blocks.inspirations;

import java.util.Random;

import com.Mrbysco.MoreCauldrons.ModReference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEnhancedCobbleCauldron extends BlockEnhancedCauldronBase implements ICauldron{
	
	public BlockEnhancedCobbleCauldron(String registryName) {
		super();
		this.setSoundType(SoundType.STONE);
        
		this.setUnlocalizedName(ModReference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
	}
	
	@Override
	public Material getMaterial(IBlockState state) {
		return Material.ROCK;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}
}