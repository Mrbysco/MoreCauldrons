package com.mrbysco.morecauldrons.blocks.inspirations;

import com.mrbysco.morecauldrons.ModReference;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockEnhancedGlassCauldron extends BlockEnhancedCauldronBase implements ICauldron{
	
	public BlockEnhancedGlassCauldron(String registryName) {
		super();
		this.setSoundType(SoundType.GLASS);
		this.setHardness(0.3F);
        
		this.setUnlocalizedName(ModReference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
	}
	
	@Override
	public Material getMaterial(IBlockState state) {
		return Material.GLASS;
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
	public int quantityDropped(Random random)
    {
        return 0;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.TRANSLUCENT;
    }
    
    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
	
    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
    	return layer == BlockRenderLayer.TRANSLUCENT;
    }
}