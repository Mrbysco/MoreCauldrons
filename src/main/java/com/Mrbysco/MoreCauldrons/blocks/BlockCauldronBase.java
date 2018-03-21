package com.Mrbysco.MoreCauldrons.blocks;

import java.util.Locale;

import com.Mrbysco.MoreCauldrons.MoreCauldrons;

import knightminer.inspirations.library.recipe.cauldron.ICauldronRecipe.CauldronState;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockCauldronBase extends BlockCauldron{
	//Most of this is for Inspirations Compatability.
	//Just so I can use the same JSON file.
	public static final PropertyEnum<Contents> CONTENTS = PropertyEnum.create("contents", Contents.class);
	public static final PropertyBool BOILING = PropertyBool.create("boiling");
	
	public BlockCauldronBase() {
		super();
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(LEVEL, 0)
				.withProperty(BOILING, false)
				.withProperty(CONTENTS, Contents.FLUID));
		this.setCreativeTab(MoreCauldrons.cauldronTab);
	}
	
	@Override
	protected ExtendedBlockState createBlockState() {
		return new ExtendedBlockState(this, new IProperty[]{LEVEL, CONTENTS, BOILING}, new IUnlistedProperty[]{});
	}
	
	public static enum Contents implements IStringSerializable {
		FLUID,
		DYE,
		POTION;

		private int meta;
		Contents() {
			this.meta = ordinal();
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.US);
		}

		public int getMeta() {
			return meta;
		}

		public static Contents fromMeta(int meta) {
			if(meta > values().length) {
				meta = 0;
			}

			return values()[meta];
		}
	}
}
