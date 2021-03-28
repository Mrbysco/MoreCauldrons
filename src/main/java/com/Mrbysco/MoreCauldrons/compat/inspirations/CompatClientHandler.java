package com.mrbysco.morecauldrons.compat.inspirations;

import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.RegistryObject;

public class CompatClientHandler {

	public static void registerModelLoaders(ModelRegistryEvent event) {
		for(RegistryObject<Block> registryBlock : CauldronRegistry.BLOCKS.getEntries()) {
			Block cauldronBlock = registryBlock.get();
			String path = cauldronBlock.getRegistryName().getPath();
			knightminer.inspirations.shared.SharedClientEvents.configPack.addBlockstateReplacement(knightminer.inspirations.common.Config.extendedCauldron,
					cauldronBlock, "inspirations_" + path);
		}
	}

	public static void registerBlockColors(ColorHandlerEvent.Block event) {
		BlockColors blockColors = event.getBlockColors();

		// coloring of liquid inside, for fluids, potions, and dyes
		registerBlockColors(blockColors, (state, world, pos, tintIndex) -> {
			// skip tint index 0, that is particles
			if (tintIndex > 0 && world != null && pos != null) {
				// must be cauldron TE
				TileEntity te = world.getTileEntity(pos);
				if(te instanceof knightminer.inspirations.recipes.tileentity.CauldronTileEntity) {
					// if it contains water, run vanilla tinting
					knightminer.inspirations.library.recipe.cauldron.contents.ICauldronContents contents = ((knightminer.inspirations.recipes.tileentity.CauldronTileEntity) te).getContents();
					if (!contents.matches(knightminer.inspirations.library.recipe.cauldron.CauldronContentTypes.FLUID, Fluids.WATER)) {
						return contents.getTintColor();
					}
				}
				// water tinting if contains water or TE is missing
				return BiomeColors.getWaterColor(world, pos);
			}

			return -1;
		}, CauldronRegistry.ACACIA_CAULDRON.get(), CauldronRegistry.DARK_OAK_CAULDRON.get(), CauldronRegistry.BIRCH_CAULDRON.get(),
				CauldronRegistry.JUNGLE_CAULDRON.get(), CauldronRegistry.OAK_CAULDRON.get(), CauldronRegistry.SPRUCE_CAULDRON.get(),
				CauldronRegistry.GOLD_CAULDRON.get(), CauldronRegistry.DIAMOND_CAULDRON.get(), CauldronRegistry.COBBLE_CAULDRON.get(),
				CauldronRegistry.BRICK_CAULDRON.get(), CauldronRegistry.OBSIDIAN_CAULDRON.get(), CauldronRegistry.STONE_CAULDRON.get(),
				CauldronRegistry.GRANITE_CAULDRON.get(), CauldronRegistry.POLISHED_GRANITE_CAULDRON.get(), CauldronRegistry.DIORITE_CAULDRON.get(),
				CauldronRegistry.POLISHED_DIORITE_CAULDRON.get(), CauldronRegistry.ANDESITE_CAULDRON.get(), CauldronRegistry.POLISHED_ANDESITE_CAULDRON.get(),
				CauldronRegistry.GLASS_CAULDRON.get());
	}

	protected static void registerBlockColors(BlockColors blockColors, IBlockColor handler, Block... blocks) {
		for (Block block : blocks) {
			if (block != null) {
				blockColors.register(handler, block);
			}
		}
	}
}
