package com.mrbysco.morecauldrons.blocks;

import com.mrbysco.morecauldrons.init.CauldronReg;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.PowderSnowCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CustomPowderSnowCauldronBlock extends PowderSnowCauldronBlock {
	private final Supplier<CauldronReg> cauldronReg;

	public CustomPowderSnowCauldronBlock(Properties properties, Supplier<CauldronReg> regSupplier, Predicate<Precipitation> predicate, Map<Item, CauldronInteraction> interactionMap) {
		super(properties, predicate, interactionMap);
		this.cauldronReg = regSupplier;
	}

	public CauldronReg getCauldronReg() {
		return cauldronReg.get();
	}

	@Override
	protected void handleEntityOnFireInside(BlockState state, Level level, BlockPos pos) {
		lowerFillLevel(getCauldronReg().getWaterCauldron().get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL)), level, pos);
	}
}
