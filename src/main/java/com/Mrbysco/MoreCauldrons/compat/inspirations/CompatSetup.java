package com.mrbysco.morecauldrons.compat.inspirations;

import com.mrbysco.morecauldrons.ModReference;
import com.mrbysco.morecauldrons.compat.inspirations.tile.EnhancedCauldronTile;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;

import java.util.Map;

public class CompatSetup {
	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModReference.MOD_ID);
	public static final RegistryObject<TileEntityType<EnhancedCauldronTile>> ENHANCED_CAULDRON_TILE = TILES.register("enhanced_cauldron_tile", () -> TileEntityType.Builder.create(() ->
					new EnhancedCauldronTile(), CauldronRegistry.ACACIA_CAULDRON.get(), CauldronRegistry.DARK_OAK_CAULDRON.get(), CauldronRegistry.BIRCH_CAULDRON.get(),
			CauldronRegistry.JUNGLE_CAULDRON.get(), CauldronRegistry.OAK_CAULDRON.get(), CauldronRegistry.SPRUCE_CAULDRON.get(), CauldronRegistry.GOLD_CAULDRON.get(),
			CauldronRegistry.DIAMOND_CAULDRON.get(), CauldronRegistry.COBBLE_CAULDRON.get(), CauldronRegistry.BRICK_CAULDRON.get(), CauldronRegistry.OBSIDIAN_CAULDRON.get(),
			CauldronRegistry.STONE_CAULDRON.get(), CauldronRegistry.GRANITE_CAULDRON.get(), CauldronRegistry.POLISHED_GRANITE_CAULDRON.get(),
			CauldronRegistry.DIORITE_CAULDRON.get(), CauldronRegistry.POLISHED_DIORITE_CAULDRON.get(), CauldronRegistry.ANDESITE_CAULDRON.get(),
			CauldronRegistry.POLISHED_ANDESITE_CAULDRON.get(), CauldronRegistry.GLASS_CAULDRON.get()).build(null));

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		if (knightminer.inspirations.common.Config.extendedCauldron.getAsBoolean()) {
			// inject new cauldron blocks into the leatherworker point of interest
			// fortunately, its as simple as injecting it into the map
			Map<BlockState, PointOfInterestType> map = GameData.getBlockStatePointOfInterestTypeMap();
			synchronized (map) {
				for(RegistryObject<Block> registryBlock : CauldronRegistry.BLOCKS.getEntries()) {
					Block cauldron = registryBlock.get();
					cauldron.getStateContainer().getValidStates().forEach(state -> map.put(state, PointOfInterestType.LEATHERWORKER));
				}
			}
		}
	}
}
