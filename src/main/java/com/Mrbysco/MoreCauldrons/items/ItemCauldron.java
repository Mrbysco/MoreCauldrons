package com.Mrbysco.MoreCauldrons.items;

import com.Mrbysco.MoreCauldrons.ModReference;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockSpecial;

public class ItemCauldron extends ItemBlockSpecial{

	public ItemCauldron(String registryName, Block block) {
		super(block);
		
		this.setUnlocalizedName(ModReference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
	}

}
