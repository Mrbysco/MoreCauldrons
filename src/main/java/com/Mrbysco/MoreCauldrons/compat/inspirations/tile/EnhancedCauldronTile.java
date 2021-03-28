package com.mrbysco.morecauldrons.compat.inspirations.tile;

import com.mrbysco.morecauldrons.compat.inspirations.CompatSetup;
import com.mrbysco.morecauldrons.compat.inspirations.blocks.EnhancedCauldronBase;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.tileentity.TileEntityType;

public class EnhancedCauldronTile extends knightminer.inspirations.recipes.tileentity.CauldronTileEntity {
	public EnhancedCauldronTile() {
		this((EnhancedCauldronBase) CauldronRegistry.ACACIA_CAULDRON.get());
	}

	public EnhancedCauldronTile(EnhancedCauldronBase cauldron) {
		super(cauldron);
	}

	@Override
	public TileEntityType<?> getType() {
		return CompatSetup.ENHANCED_CAULDRON_TILE.get();
	}
}
