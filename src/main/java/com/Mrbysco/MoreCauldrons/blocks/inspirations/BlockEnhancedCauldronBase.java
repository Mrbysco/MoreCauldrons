package com.mrbysco.morecauldrons.blocks.inspirations;

import com.mrbysco.morecauldrons.MoreCauldrons;
import knightminer.inspirations.common.Config;
import knightminer.inspirations.library.InspirationsRegistry;
import knightminer.inspirations.recipes.block.BlockEnhancedCauldron;
import knightminer.inspirations.recipes.tileentity.TileCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEnhancedCauldronBase extends BlockEnhancedCauldron{
	public BlockEnhancedCauldronBase() {
		super();
		this.setCreativeTab(MoreCauldrons.cauldronTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!Config.enableCauldronRecipes) {
			return false;
		}

		if(player.isSneaking()) {
			return false;
		}

		ItemStack stack = player.getHeldItem(hand);
		if(stack.isEmpty()) {
			return false;
		}

		boolean result = TileCauldron.interact(world, pos, state, player, hand);
		return result || InspirationsRegistry.isCauldronBlacklist(player.getHeldItem(hand));
	}
}
