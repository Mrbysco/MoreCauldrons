package com.mrbysco.morecauldrons.init;

import com.mrbysco.morecauldrons.ModReference;
import com.mrbysco.morecauldrons.blocks.BaseCauldronBlock;
import com.mrbysco.morecauldrons.blocks.WoodenCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CauldronRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModReference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModReference.MOD_ID);

    public static RegistryObject<Block> ACACIA_CAULDRON;
    public static RegistryObject<Block> DARK_OAK_CAULDRON;
    public static RegistryObject<Block> BIRCH_CAULDRON;
    public static RegistryObject<Block> JUNGLE_CAULDRON;
    public static RegistryObject<Block> OAK_CAULDRON;
    public static RegistryObject<Block> SPRUCE_CAULDRON;

    public static RegistryObject<Block> GOLD_CAULDRON;
    public static RegistryObject<Block> DIAMOND_CAULDRON;

    public static RegistryObject<Block> COBBLE_CAULDRON;
    public static RegistryObject<Block> BRICK_CAULDRON;
    public static RegistryObject<Block> OBSIDIAN_CAULDRON;
    public static RegistryObject<Block> GLASS_CAULDRON;
    public static RegistryObject<Block> STONE_CAULDRON;
    public static RegistryObject<Block> GRANITE_CAULDRON;
    public static RegistryObject<Block> POLISHED_GRANITE_CAULDRON;
    public static RegistryObject<Block> DIORITE_CAULDRON;
    public static RegistryObject<Block> POLISHED_DIORITE_CAULDRON;
    public static RegistryObject<Block> ANDESITE_CAULDRON;
    public static RegistryObject<Block> POLISHED_ANDESITE_CAULDRON;

    public static void registerVanilla() {
        ACACIA_CAULDRON = registerBlock("acacia_cauldron", () -> new WoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.ADOBE)), CauldronTab.CAULDRON_TAB);
        DARK_OAK_CAULDRON = registerBlock("dark_oak_cauldron", () -> new WoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.BROWN)), CauldronTab.CAULDRON_TAB);
        BIRCH_CAULDRON = registerBlock("birch_cauldron", () -> new WoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.SAND)), CauldronTab.CAULDRON_TAB);
        JUNGLE_CAULDRON = registerBlock("jungle_cauldron", () -> new WoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.DIRT)), CauldronTab.CAULDRON_TAB);
        OAK_CAULDRON = registerBlock("oak_cauldron", () -> new WoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD)), CauldronTab.CAULDRON_TAB);
        SPRUCE_CAULDRON = registerBlock("spruce_cauldron", () -> new WoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN)), CauldronTab.CAULDRON_TAB);

        GOLD_CAULDRON = registerBlock("gold_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.IRON, MaterialColor.GOLD).sound(SoundType.METAL).hardnessAndResistance(3.0F, 10.0F)), CauldronTab.CAULDRON_TAB);
        DIAMOND_CAULDRON = registerBlock("diamond_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.IRON, MaterialColor.DIAMOND).sound(SoundType.METAL).hardnessAndResistance(5.0F, 10.0F)), CauldronTab.CAULDRON_TAB);

        COBBLE_CAULDRON = registerBlock("cobble_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        GLASS_CAULDRON = registerBlock("glass_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()), CauldronTab.CAULDRON_TAB);
        OBSIDIAN_CAULDRON = registerBlock("obsidian_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        BRICK_CAULDRON = registerBlock("brick_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(2.0F, 10.0F).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);

        STONE_CAULDRON = registerBlock("stone_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        GRANITE_CAULDRON = registerBlock("granite_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.DIRT).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        POLISHED_GRANITE_CAULDRON = registerBlock("polished_granite_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.DIRT).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        DIORITE_CAULDRON = registerBlock("diorite_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        POLISHED_DIORITE_CAULDRON = registerBlock("polished_diorite_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        ANDESITE_CAULDRON = registerBlock("andesite_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        POLISHED_ANDESITE_CAULDRON = registerBlock("polished_andesite_cauldron", () -> new BaseCauldronBlock(Block.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
    }

/*    public static void registerInspirationsSupport() {
        ACACIA_CAULDRON = registerBlock("acacia_cauldron", () -> new EnhancedWoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.ADOBE)), CauldronTab.CAULDRON_TAB);
        DARK_OAK_CAULDRON = registerBlock("dark_oak_cauldron", () -> new EnhancedWoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.BROWN)), CauldronTab.CAULDRON_TAB);
        BIRCH_CAULDRON = registerBlock("birch_cauldron", () -> new EnhancedWoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.SAND)), CauldronTab.CAULDRON_TAB);
        JUNGLE_CAULDRON = registerBlock("jungle_cauldron", () -> new EnhancedWoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.DIRT)), CauldronTab.CAULDRON_TAB);
        OAK_CAULDRON = registerBlock("oak_cauldron", () -> new EnhancedWoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD)), CauldronTab.CAULDRON_TAB);
        SPRUCE_CAULDRON = registerBlock("spruce_cauldron", () -> new EnhancedWoodenCauldronBlock(Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN)), CauldronTab.CAULDRON_TAB);

        GOLD_CAULDRON = registerBlock("gold_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.IRON, MaterialColor.GOLD).sound(SoundType.METAL).hardnessAndResistance(3.0F, 10.0F)), CauldronTab.CAULDRON_TAB);
        DIAMOND_CAULDRON = registerBlock("diamond_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.IRON, MaterialColor.DIAMOND).sound(SoundType.METAL).hardnessAndResistance(5.0F, 10.0F)), CauldronTab.CAULDRON_TAB);

        COBBLE_CAULDRON = registerBlock("cobble_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        GLASS_CAULDRON = registerBlock("glass_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()), CauldronTab.CAULDRON_TAB);
        OBSIDIAN_CAULDRON = registerBlock("obsidian_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        BRICK_CAULDRON = registerBlock("brick_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(2.0F, 10.0F).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);

        STONE_CAULDRON = registerBlock("stone_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        GRANITE_CAULDRON = registerBlock("granite_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.DIRT).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        POLISHED_GRANITE_CAULDRON = registerBlock("polished_granite_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.DIRT).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        DIORITE_CAULDRON = registerBlock("diorite_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        POLISHED_DIORITE_CAULDRON = registerBlock("polished_diorite_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        ANDESITE_CAULDRON = registerBlock("andesite_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
        POLISHED_ANDESITE_CAULDRON = registerBlock("polished_andesite_cauldron", () -> new EnhancedCauldronBase(Block.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE)), CauldronTab.CAULDRON_TAB);
    }*/

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = CauldronRegistry.BLOCKS.register(name, supplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
        return block;
    }
}
