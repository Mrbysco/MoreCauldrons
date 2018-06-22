package com.Mrbysco.MoreCauldrons.blocks;

import com.Mrbysco.MoreCauldrons.MoreCauldrons;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;

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
}
