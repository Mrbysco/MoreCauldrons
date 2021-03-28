package com.mrbysco.morecauldrons.compat.inspirations.blocks;

import com.mrbysco.morecauldrons.compat.inspirations.tile.EnhancedCauldronTile;
import knightminer.inspirations.recipes.block.EnhancedCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class EnhancedCauldronBase extends EnhancedCauldronBlock {
	public EnhancedCauldronBase(Properties properties){
		super(properties);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new EnhancedCauldronTile(this);
	}
}
