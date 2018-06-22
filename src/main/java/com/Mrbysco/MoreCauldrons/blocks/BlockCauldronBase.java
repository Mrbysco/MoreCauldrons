package com.Mrbysco.MoreCauldrons.blocks;

import com.Mrbysco.MoreCauldrons.MoreCauldrons;
import com.Mrbysco.MoreCauldrons.config.MoreCauldronsConfigGen;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCauldronBase extends BlockCauldron{
	
	public BlockCauldronBase(){
		super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, Integer.valueOf(0)));
		this.setCreativeTab(MoreCauldrons.cauldronTab);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {LEVEL});
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
		if(MoreCauldronsConfigGen.general.liquidDropping)
		{
			if(getWaterLevel(state) == 3)
			{
				worldIn.setBlockState(pos, Blocks.WATER.getDefaultState(), 6);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
}
