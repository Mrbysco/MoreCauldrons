package com.mrbysco.morecauldrons.init;

import com.mrbysco.morecauldrons.blocks.BaseCauldronBlock;
import com.mrbysco.morecauldrons.blocks.CustomPowderSnowCauldronBlock;
import com.mrbysco.morecauldrons.blocks.WoodenCauldronBlock;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;

public class CauldronReg {
	private final String name;
	private RegistryObject<Block> cauldron;
	private RegistryObject<Block> water_cauldron;
	private RegistryObject<Block> lava_cauldron;
	private RegistryObject<Block> powder_snow_cauldron;
	private RegistryObject<Item> cauldron_item;

	public String getName() {
		return name;
	}

	public RegistryObject<Block> getCauldron() {
		return cauldron;
	}

	public RegistryObject<Block> getWaterCauldron() {
		return water_cauldron;
	}

	public RegistryObject<Block> getLavaCauldron() {
		return lava_cauldron;
	}

	public RegistryObject<Block> getPowderSnowCauldron() {
		return powder_snow_cauldron;
	}

	public boolean matchesCauldronType(BlockState state) {
		return state.is(cauldron.get()) || state.is(water_cauldron.get()) || state.is(lava_cauldron.get()) || state.is(powder_snow_cauldron.get());
	}

	public CauldronReg(String name, BlockBehaviour.Properties properties, boolean wood) {
		this.name = name;

		if(wood) {
			cauldron = CauldronRegistry.BLOCKS.register(name + "_cauldron", () ->
					new WoodenCauldronBlock(properties.sound(SoundType.WOOD).strength(2.0F, 3.0F)
							.requiresCorrectToolForDrops().strength(2.0F).noOcclusion(),
							() -> this));
		} else {
			cauldron = CauldronRegistry.BLOCKS.register(name + "_cauldron", () ->
					new BaseCauldronBlock(properties.requiresCorrectToolForDrops().strength(2.0F).noOcclusion(),
							() -> this));
		}

		water_cauldron = CauldronRegistry.BLOCKS.register("water_" + name + "_cauldron", () ->
				new LayeredCauldronBlock(BlockBehaviour.Properties.copy(cauldron.get()), LayeredCauldronBlock.RAIN, CauldronInteraction.WATER));

		lava_cauldron = CauldronRegistry.BLOCKS.register("lava_" + name + "_cauldron", () ->
				new LavaCauldronBlock(BlockBehaviour.Properties.copy(cauldron.get()).lightLevel((state) -> {
					return 15;
				})));

		powder_snow_cauldron = CauldronRegistry.BLOCKS.register("powder_snow_" + name + "_cauldron", () ->
				new CustomPowderSnowCauldronBlock(BlockBehaviour.Properties.copy(cauldron.get()), () -> this, LayeredCauldronBlock.SNOW, CauldronInteraction.POWDER_SNOW));

		cauldron_item = CauldronRegistry.ITEMS.register(name + "_cauldron", () -> new BlockItem(cauldron.get(), new Item.Properties().tab(CauldronTab.CAULDRON_TAB)));
	}

	public CauldronReg(String name, BlockBehaviour.Properties properties) {
		this(name, properties, false);
	}

	public CauldronReg(String name, Material material, MaterialColor color) {
		this(name, BlockBehaviour.Properties.of(material, color), false);
	}

	public CauldronReg(String name, Material material, MaterialColor color, boolean wood) {
		this(name, BlockBehaviour.Properties.of(material, color), wood);
	}
}
