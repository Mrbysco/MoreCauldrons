package com.mrbysco.morecauldrons.init;

import com.mrbysco.morecauldrons.ModReference;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CauldronRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModReference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModReference.MOD_ID);

    public static ArrayList<CauldronReg> CAULDRON_REGS = new ArrayList<>();

    public static CauldronReg ACACIA_CAULDRON = addReg(new CauldronReg("acacia", BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE), true));
    public static CauldronReg DARK_OAK_CAULDRON = addReg(new CauldronReg("dark_oak", BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN), true));
    public static CauldronReg BIRCH_CAULDRON = addReg(new CauldronReg("birch", BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND), true));
    public static CauldronReg JUNGLE_CAULDRON = addReg(new CauldronReg("jungle", BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT), true));
    public static CauldronReg OAK_CAULDRON = addReg(new CauldronReg("oak", BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD), true));
    public static CauldronReg SPRUCE_CAULDRON = addReg(new CauldronReg("spruce", BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL), true));

    public static CauldronReg GOLD_CAULDRON = addReg(new CauldronReg("gold", Material.METAL, MaterialColor.GOLD));
    public static CauldronReg DIAMOND_CAULDRON = addReg(new CauldronReg("diamond", Material.METAL, MaterialColor.DIAMOND));
    public static CauldronReg COPPER_CAULDRON = addReg(new CauldronReg("copper", Material.METAL, MaterialColor.COLOR_ORANGE));

    public static CauldronReg COBBLE_CAULDRON = addReg(new CauldronReg("cobble", Material.STONE, MaterialColor.STONE));
    public static CauldronReg BRICK_CAULDRON = addReg(new CauldronReg("brick", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(2.0F, 10.0F).sound(SoundType.STONE)));
    public static CauldronReg OBSIDIAN_CAULDRON = addReg(new CauldronReg("obsidian", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(50.0F, 1200.0F).sound(SoundType.STONE)));
    public static CauldronReg GLASS_CAULDRON = addReg(new CauldronReg("glass", BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static CauldronReg STONE_CAULDRON = addReg(new CauldronReg("stone", Material.STONE, MaterialColor.STONE));
    public static CauldronReg GRANITE_CAULDRON = addReg(new CauldronReg("granite", Material.STONE, MaterialColor.DIRT));
    public static CauldronReg POLISHED_GRANITE_CAULDRON = addReg(new CauldronReg("polished_granite", Material.STONE, MaterialColor.DIRT));
    public static CauldronReg DIORITE_CAULDRON = addReg(new CauldronReg("diorite", Material.STONE, MaterialColor.QUARTZ));
    public static CauldronReg POLISHED_DIORITE_CAULDRON = addReg(new CauldronReg("polished_diorite", Material.STONE, MaterialColor.QUARTZ));
    public static CauldronReg ANDESITE_CAULDRON = addReg(new CauldronReg("andesite", Material.STONE, MaterialColor.STONE));
    public static CauldronReg POLISHED_ANDESITE_CAULDRON = addReg(new CauldronReg("polished_andesite", Material.STONE, MaterialColor.STONE));

    public static CauldronReg addReg(CauldronReg reg) {
        CAULDRON_REGS.add(reg);
        return reg;
    }
}
