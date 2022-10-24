package com.mrbysco.morecauldrons.init;

import com.mrbysco.morecauldrons.ModReference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CauldronTab {

	public static final ItemGroup CAULDRON_TAB = new ItemGroup(ModReference.MOD_ID + ".cauldron") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(CauldronRegistry.OAK_CAULDRON.get());
		}
	};
}