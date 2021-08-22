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

import java.util.Optional;
import java.util.function.Supplier;

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
			if (itemstack.getMaxStackSize() == 1 && FluidUtil.getFluidHandler(itemstack).isPresent()) {
				IFluidHandlerItem fluidHandler = FluidUtil.getFluidHandler(itemstack).orElse(null);
				Optional<FluidStack> containedFluid = FluidUtil.getFluidContained(itemstack);
				Supplier<FluidStack> waterStackSupplier = () -> new FluidStack(Fluids.WATER, 1000);
				if (containedFluid.isPresent() && i < 3 && !worldIn.isRemote) {
					//Container has liquid
					FluidStack fluidStack = containedFluid.get();
					if (fluidStack.getFluid() == Fluids.WATER && fluidStack.getAmount() >= 1000) {
						FluidStack drainedStack = fluidHandler.drain(waterStackSupplier.get(), FluidAction.SIMULATE);
						if (drainedStack.getAmount() == 1000) {
							fluidHandler.drain(waterStackSupplier.get(), FluidAction.EXECUTE);
							player.setHeldItem(handIn, fluidHandler.getContainer());
							player.addStat(Stats.FILL_CAULDRON);
							this.setWaterLevel(worldIn, pos, state, 3);
							worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				} else {
					//Container is empty
					if (i == 3 && !worldIn.isRemote) {
						if (!player.abilities.isCreativeMode) {
							if (fluidHandler.isFluidValid(0, waterStackSupplier.get()) && fluidHandler.fill(waterStackSupplier.get(), FluidAction.SIMULATE) == 1000) {
								fluidHandler.fill(waterStackSupplier.get(), FluidAction.EXECUTE);
								player.setHeldItem(handIn, fluidHandler.getContainer());
							}
						}

						player.addStat(Stats.USE_CAULDRON);
						this.setWaterLevel(worldIn, pos, state, 0);
						worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}

				}
				return ActionResultType.SUCCESS;
			}
		}

		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
}
