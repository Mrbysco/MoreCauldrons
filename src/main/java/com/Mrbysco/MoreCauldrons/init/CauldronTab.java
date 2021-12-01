package com.mrbysco.morecauldrons.init;

import com.mrbysco.morecauldrons.ModReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CauldronTab{

	public static final CreativeModeTab CAULDRON_TAB = new CreativeModeTab(ModReference.MOD_ID + ".cauldron") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(CauldronRegistry.OAK_CAULDRON.getCauldron().get());
		}
	};
}