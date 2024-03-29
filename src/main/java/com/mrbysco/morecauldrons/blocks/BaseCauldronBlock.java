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
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import java.util.Optional;
import java.util.function.Supplier;

public class BaseCauldronBlock extends CauldronBlock {

	public BaseCauldronBlock(Block.Properties properties) {
		super(properties.harvestTool(ToolType.PICKAXE).strength(2.0F));
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack stack = player.getItemInHand(handIn);
		if (stack.isEmpty()) {
			return ActionResultType.PASS;
		} else {
			int i = state.getValue(LEVEL);
			if (stack.getCount() == 1 && FluidUtil.getFluidHandler(stack).isPresent()) {
				IFluidHandlerItem fluidHandler = FluidUtil.getFluidHandler(stack).orElse(null);
				Optional<FluidStack> containedFluid = FluidUtil.getFluidContained(stack);
				Supplier<FluidStack> waterStackSupplier = () -> new FluidStack(Fluids.WATER, 1000);
				if (containedFluid.isPresent() && i < 3) {
					//Container has liquid
					FluidStack fluidStack = containedFluid.get();
					if (fluidStack.getFluid() == Fluids.WATER && fluidStack.getAmount() >= 1000) {
						FluidStack drainedStack = fluidHandler.drain(waterStackSupplier.get(), FluidAction.SIMULATE);
						if (drainedStack.getAmount() == 1000) {
							fluidHandler.drain(waterStackSupplier.get(), FluidAction.EXECUTE);
							player.setItemInHand(handIn, fluidHandler.getContainer());
							player.awardStat(Stats.FILL_CAULDRON);
							if (!worldIn.isClientSide) {
								this.setWaterLevel(worldIn, pos, state, 3);
							}
							worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				} else {
					//Container is empty
					if (i == 3) {
						if (!player.abilities.instabuild) {
							if (fluidHandler.isFluidValid(0, waterStackSupplier.get()) &&
									fluidHandler.fill(waterStackSupplier.get(), FluidAction.SIMULATE) == 1000) {
								fluidHandler.fill(waterStackSupplier.get(), FluidAction.EXECUTE);
								player.setItemInHand(handIn, fluidHandler.getContainer());
							} else {
								return ActionResultType.PASS;
							}
						}

						player.awardStat(Stats.USE_CAULDRON);
						if (!worldIn.isClientSide) {
							this.setWaterLevel(worldIn, pos, state, 0);
						}
						worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}

				}
				return ActionResultType.SUCCESS;
			}
		}

		return super.use(state, worldIn, pos, player, handIn, hit);
	}
}
