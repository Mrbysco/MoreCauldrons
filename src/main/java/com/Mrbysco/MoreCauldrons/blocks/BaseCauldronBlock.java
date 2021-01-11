package com.mrbysco.morecauldrons.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class BaseCauldronBlock extends CauldronBlock {
	
	public BaseCauldronBlock(Block.Properties properties){
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack itemstack = player.getHeldItem(handIn);
		if (itemstack.isEmpty()) {
			return ActionResultType.PASS;
		} else {
			int i = state.get(LEVEL);
			if(FluidUtil.getFluidHandler(itemstack).isPresent()) {
				IFluidHandlerItem fluidHandler = FluidUtil.getFluidHandler(itemstack).orElse(null);
				FluidStack bucketFluid = new FluidStack(Fluids.WATER, 1000);
				if(FluidUtil.getFluidContained(itemstack).isPresent()) {
					if (i < 3 && !worldIn.isRemote && FluidUtil.getFluidContained(itemstack).get().getFluid() == Fluids.WATER) {
						FluidStack removed = fluidHandler.drain(bucketFluid, FluidAction.SIMULATE);
						if(removed.getAmount() == 1000) {
							if (!player.abilities.isCreativeMode) {
								fluidHandler.drain(new FluidStack(Fluids.WATER, 1000), FluidAction.EXECUTE);
							}

							player.addStat(Stats.FILL_CAULDRON);
							this.setWaterLevel(worldIn, pos, state, 3);
							worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				} else {
					if (i == 3 && !worldIn.isRemote) {
						int filled = fluidHandler.fill(bucketFluid, FluidAction.SIMULATE);
						if(filled == 1000) {
							if (!player.abilities.isCreativeMode) {
								fluidHandler.fill(new FluidStack(Fluids.WATER, 1000), FluidAction.EXECUTE);
							}

							player.addStat(Stats.USE_CAULDRON);
							this.setWaterLevel(worldIn, pos, state, 0);
							worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				}
				return ActionResultType.func_233537_a_(worldIn.isRemote);
			}
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}
}
