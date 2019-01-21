package com.mrbysco.morecauldrons.init;

import com.mrbysco.morecauldrons.ModReference;

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