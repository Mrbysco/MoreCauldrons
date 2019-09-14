package com.mrbysco.morecauldrons.blocks.inspirations.jei;

import javax.annotation.Nonnull;

import com.mrbysco.morecauldrons.blocks.inspirations.ICauldron;
import com.mrbysco.morecauldrons.init.ModBlocks;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
	@Override
	public void register(@Nonnull IModRegistry registry) {
		registerInspirations(registry);
	}
	

	@Optional.Method(modid = "inspirations")
	public void registerInspirations(@Nonnull IModRegistry registry) {
		if(knightminer.inspirations.common.PulseBase.isRecipesLoaded()) {
			if(knightminer.inspirations.common.Config.enableCauldronRecipes) {
				for(Block block : ModBlocks.BLOCKS)
					if(block instanceof ICauldron)
						registry.addRecipeCatalyst(new ItemStack(block), knightminer.inspirations.plugins.jei.cauldron.CauldronRecipeCategory.CATEGORY);
			}
		}
	}
}
