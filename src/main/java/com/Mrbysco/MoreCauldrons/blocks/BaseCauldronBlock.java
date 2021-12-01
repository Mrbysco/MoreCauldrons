package com.mrbysco.morecauldrons.blocks;

import com.mrbysco.morecauldrons.init.CauldronReg;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Supplier;

public class BaseCauldronBlock extends CauldronBlock {
	private final Supplier<CauldronReg> cauldronReg;

	public BaseCauldronBlock(Block.Properties properties, Supplier<CauldronReg> regSupplier) {
		super(properties);
		this.cauldronReg = regSupplier;
	}

	public CauldronReg getCauldronReg() {
		return cauldronReg.get();
	}

	@Override
	public void handlePrecipitation(BlockState state, Level level, BlockPos pos, Precipitation precipitation) {
		if (shouldHandlePrecipitation(level, precipitation)) {
			if (precipitation == Biome.Precipitation.RAIN) {
				level.setBlockAndUpdate(pos, getCauldronReg().getWaterCauldron().get().defaultBlockState());
				level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
			} else if (precipitation == Biome.Precipitation.SNOW) {
				level.setBlockAndUpdate(pos, getCauldronReg().getPowderSnowCauldron().get().defaultBlockState());
				level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
			}
		}
	}

	@Override
	protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
		if (fluid == Fluids.WATER) {
			level.setBlockAndUpdate(pos, getCauldronReg().getWaterCauldron().get().defaultBlockState());
			level.levelEvent(1047, pos, 0);
			level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
		} else if (fluid == Fluids.LAVA) {
			level.setBlockAndUpdate(pos, getCauldronReg().getLavaCauldron().get().defaultBlockState());
			level.levelEvent(1046, pos, 0);
			level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
		}
	}
}
