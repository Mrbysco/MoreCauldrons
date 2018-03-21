package com.Mrbysco.MoreCauldrons.init;

import com.Mrbysco.MoreCauldrons.ModReference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CauldronTab extends CreativeTabs{

	public CauldronTab() {
		super(ModReference.MOD_ID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.oak_cauldron);
	}
}