package com.mrbysco.morecauldrons.items;

import com.mrbysco.morecauldrons.ModReference;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockSpecial;

public class ItemCauldron extends ItemBlockSpecial{

	public ItemCauldron(String registryName, Block block) {
		super(block);
		
		this.setUnlocalizedName(ModReference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
	}

}
