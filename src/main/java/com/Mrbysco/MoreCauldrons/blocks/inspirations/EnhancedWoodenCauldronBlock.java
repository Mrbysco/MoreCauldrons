package com.mrbysco.morecauldrons.blocks.inspirations;

import knightminer.inspirations.recipes.block.EnhancedCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

@SuppressWarnings("deprecated")
public class EnhancedWoodenCauldronBlock extends EnhancedCauldronBlock implements ICauldron{

	public EnhancedWoodenCauldronBlock(Block.Properties properties){
		super(properties.sound(SoundType.WOOD).harvestTool(ToolType.AXE).hardnessAndResistance(2.0F, 3.0F));
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 10;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 5;
	}
}