package com.mrbysco.morecauldrons.blocks.inspirations.jei;

import com.mrbysco.morecauldrons.blocks.inspirations.ICauldron;
import com.mrbysco.morecauldrons.init.ModBlocks;
import knightminer.inspirations.common.Config;
import knightminer.inspirations.common.PulseBase;
import knightminer.inspirations.plugins.jei.cauldron.CauldronRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
	@Override
	public void register(@Nonnull IModRegistry registry) {
		if(Loader.isModLoaded("inspirations"))
		{
			if(PulseBase.isRecipesLoaded()) {
				if(Config.enableCauldronRecipes) {
					for(Block block : ModBlocks.BLOCKS)
						if(block instanceof ICauldron)
							registry.addRecipeCatalyst(new ItemStack(block), CauldronRecipeCategory.CATEGORY);
				}
			}
		}
	}
}
