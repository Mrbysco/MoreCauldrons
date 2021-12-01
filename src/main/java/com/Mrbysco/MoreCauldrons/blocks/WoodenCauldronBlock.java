package com.mrbysco.morecauldrons.blocks;

import com.mrbysco.morecauldrons.init.CauldronReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

@SuppressWarnings("deprecated")
public class WoodenCauldronBlock extends BaseCauldronBlock {

	public WoodenCauldronBlock(Block.Properties properties, Supplier<CauldronReg> regSupplier) {
		super(properties, regSupplier);
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 10;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}
}
