package com.mrbysco.morecauldrons.blocks.inspirations;

import java.util.Random;

import com.mrbysco.morecauldrons.ModReference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnhancedWoodenCauldron extends BlockEnhancedCauldronBase implements ICauldron{
	
	private final MapColor mapcolor;

	public BlockEnhancedWoodenCauldron(String registryName, MapColor color) {
		super();
		this.setSoundType(SoundType.WOOD);
		this.setHarvestLevel("axe",0);
		
		this.mapcolor=color;
        
		this.setUnlocalizedName(ModReference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
	}
	
	public MapColor getMapcolor() {
		return this.mapcolor;
	}
	
	@Override
	public Material getMaterial(IBlockState state) {
		return Material.WOOD;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}
	
	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 10;
	}
	
	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}
}