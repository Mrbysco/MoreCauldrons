package com.Mrbysco.MoreCauldrons.blocks.inspirations;

import java.util.Random;

import com.Mrbysco.MoreCauldrons.ModReference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnhancedObsidianCauldron extends BlockEnhancedCauldronBase implements ICauldron{
	
	public BlockEnhancedObsidianCauldron(String registryName) {
		super();
		this.setSoundType(SoundType.STONE);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
        
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

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.BLACK;
    }
}