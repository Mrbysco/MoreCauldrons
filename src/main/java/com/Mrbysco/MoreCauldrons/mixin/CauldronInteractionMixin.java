package com.mrbysco.morecauldrons.mixin;

import com.mrbysco.morecauldrons.ModReference;
import com.mrbysco.morecauldrons.util.CauldronHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Predicate;

@Mixin(CauldronInteraction.class)
public interface CauldronInteractionMixin {

	/**
	 * @author Mrbysco
	 * @reason No other way I could think of
	 */
	@Overwrite
	static InteractionResult fillBucket(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack p_175641_, ItemStack p_175642_, Predicate<BlockState> statePredicate, SoundEvent soundEvent) {
		if (!statePredicate.test(state)) {
			return InteractionResult.PASS;
		} else {
			BlockState currentCauldron = level.getBlockState(pos);

			if(currentCauldron.getBlock().getRegistryName().getNamespace().equals(ModReference.MOD_ID)) {
				BlockState cauldronState = currentCauldron;
				BlockState equalState = CauldronHelper.getEquivalentCauldron(currentCauldron);
				if(equalState != null) {
					cauldronState = equalState;
				}

				if (!level.isClientSide) {
					Item item = p_175641_.getItem();
					player.setItemInHand(hand, ItemUtils.createFilledResult(p_175641_, player, p_175642_));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					level.setBlockAndUpdate(pos, cauldronState);
					level.playSound((Player)null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, pos);
				}

				return InteractionResult.sidedSuccess(level.isClientSide);
			}

			if (!level.isClientSide) {
				Item item = p_175641_.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(p_175641_, player, p_175642_));
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
				level.playSound((Player)null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, pos);
			}

			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	/**
	 * @author Mrbysco
	 * @reason No other way I could think of
	 */
	@Overwrite
	static InteractionResult emptyBucket(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
		BlockState currentCauldron = level.getBlockState(pos);
		if(currentCauldron.getBlock().getRegistryName().getNamespace().equals(ModReference.MOD_ID)) {
			BlockState cauldronState = currentCauldron;
			if(state.is(Blocks.POWDER_SNOW_CAULDRON)) {
				BlockState equalState = CauldronHelper.getEquivalentSnowCauldron(currentCauldron);
				if(equalState != null) {
					cauldronState = equalState.setValue(LayeredCauldronBlock.LEVEL, 3);
				}
			} else if(state.is(Blocks.WATER_CAULDRON)) {
				BlockState equalState = CauldronHelper.getEquivalentWaterCauldron(currentCauldron);
				if(equalState != null) {
					cauldronState = equalState.setValue(LayeredCauldronBlock.LEVEL, 3);
				}
			} else if(state.is(Blocks.LAVA_CAULDRON)) {
				BlockState equalState = CauldronHelper.getEquivalentLavaCauldron(currentCauldron);
				if(equalState != null) {
					cauldronState = equalState;
				}
			}
			if (!level.isClientSide) {
				Item item = stack.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
				player.awardStat(Stats.FILL_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.setBlockAndUpdate(pos, cauldronState);
				level.playSound((Player)null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
			}

			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		if (!level.isClientSide) {
			Item item = stack.getItem();
			player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
			player.awardStat(Stats.FILL_CAULDRON);
			player.awardStat(Stats.ITEM_USED.get(item));
			level.setBlockAndUpdate(pos, state);
			level.playSound((Player)null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, pos);
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}
