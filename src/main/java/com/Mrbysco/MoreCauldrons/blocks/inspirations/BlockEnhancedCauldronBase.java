package com.Mrbysco.MoreCauldrons.blocks.inspirations;

import com.Mrbysco.MoreCauldrons.MoreCauldrons;

import knightminer.inspirations.library.recipe.cauldron.ICauldronRecipe.CauldronState;
import knightminer.inspirations.recipes.block.BlockEnhancedCauldron;
import knightminer.inspirations.recipes.tileentity.TileCauldron;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEnhancedCauldronBase extends BlockEnhancedCauldron{
	public BlockEnhancedCauldronBase() {
		super();
		this.setCreativeTab(MoreCauldrons.cauldronTab);
	}
	
	protected int getWaterLevel(IBlockState state)
    {
        return ((Integer)state.getValue(this.getLevelProperty())).intValue();
    }
	
	protected PropertyInteger getLevelProperty()
    {
        return LEVEL;
    }
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileCauldron)
		{
			TileCauldron cauldron = (TileCauldron)te;
			CauldronState cState = CauldronState.fromNBT(cauldron.serializeNBT().getCompoundTag(cauldron.TAG_STATE));
			
			if(cauldron.getContentType() != CauldronContents.DYE)
			{
				if(getWaterLevel(state) == 3 && cState.getFluid().getBlock() != null)
					worldIn.setBlockState(pos, cState.getFluid().getBlock().getDefaultState(), 6);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
}
