package com.Mrbysco.MoreCauldrons.blocks.inspirations.jei;

import javax.annotation.Nonnull;

import com.Mrbysco.MoreCauldrons.blocks.inspirations.ICauldron;
import com.Mrbysco.MoreCauldrons.init.ModBlocks;

import knightminer.inspirations.common.Config;
import knightminer.inspirations.common.PulseBase;
import knightminer.inspirations.library.recipe.TextureRecipe;
import knightminer.inspirations.plugins.jei.cauldron.CauldronRecipeCategory;
import knightminer.inspirations.plugins.jei.texture.TextureRecipeHandler;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

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
