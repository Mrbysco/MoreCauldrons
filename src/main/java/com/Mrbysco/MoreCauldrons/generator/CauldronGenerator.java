package com.mrbysco.morecauldrons.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mrbysco.morecauldrons.ModReference;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.CauldronBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTable.Builder;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.ValidationTracker;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mrbysco.morecauldrons.init.CauldronRegistry.*;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CauldronGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new Loots(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new BlockStates(generator, helper));
            generator.addProvider(new ItemModels(generator, helper));
        }
    }

    private static class Loots extends LootTableProvider {
        public Loots(DataGenerator gen) {
            super(gen);
        }

        @Override
        protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
            return ImmutableList.of(
                    Pair.of(Blocks::new, LootParameterSets.BLOCK)
            );
        }

        @Override
        protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
            map.forEach((name, table) -> LootTableManager.func_227508_a_(validationtracker, name, table));
        }

        private class Blocks extends BlockLootTables {
            @Override
            protected void addTables() {
                this.registerDropSelfLootTable(ACACIA_CAULDRON.get());
                this.registerDropSelfLootTable(DARK_OAK_CAULDRON.get());
                this.registerDropSelfLootTable(BIRCH_CAULDRON.get());
                this.registerDropSelfLootTable(JUNGLE_CAULDRON.get());
                this.registerDropSelfLootTable(OAK_CAULDRON.get());
                this.registerDropSelfLootTable(SPRUCE_CAULDRON.get());

                this.registerDropSelfLootTable(GOLD_CAULDRON.get());
                this.registerDropSelfLootTable(DIAMOND_CAULDRON.get());
                this.registerDropSelfLootTable(COBBLE_CAULDRON.get());
                this.registerSilkTouch(GLASS_CAULDRON.get());
                this.registerDropSelfLootTable(OBSIDIAN_CAULDRON.get());
                this.registerDropSelfLootTable(BRICK_CAULDRON.get());

                this.registerDropSelfLootTable(STONE_CAULDRON.get());
                this.registerDropSelfLootTable(GRANITE_CAULDRON.get());
                this.registerDropSelfLootTable(POLISHED_GRANITE_CAULDRON.get());
                this.registerDropSelfLootTable(DIORITE_CAULDRON.get());
                this.registerDropSelfLootTable(POLISHED_DIORITE_CAULDRON.get());
                this.registerDropSelfLootTable(ANDESITE_CAULDRON.get());
                this.registerDropSelfLootTable(POLISHED_ANDESITE_CAULDRON.get());
            }

            @Override
            protected Iterable<Block> getKnownBlocks() {
                return (Iterable<Block>) CauldronRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
            }
        }
    }

    private static class ItemModels extends ItemModelProvider {
        public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
            super(gen, ModReference.MOD_ID, helper);
        }

        @Override
        protected void registerModels() {
            makeCauldron(ACACIA_CAULDRON.get());
            makeCauldron(DARK_OAK_CAULDRON.get());
            makeCauldron(BIRCH_CAULDRON.get());
            makeCauldron(JUNGLE_CAULDRON.get());
            makeCauldron(OAK_CAULDRON.get());
            makeCauldron(SPRUCE_CAULDRON.get());

            makeCauldron(GOLD_CAULDRON.get());
            makeCauldron(DIAMOND_CAULDRON.get());

            makeCauldron(COBBLE_CAULDRON.get());
            makeCauldron(GLASS_CAULDRON.get());
            makeCauldron(OBSIDIAN_CAULDRON.get());
            makeCauldron(BRICK_CAULDRON.get());

            makeCauldron(STONE_CAULDRON.get());
            makeCauldron(GRANITE_CAULDRON.get());
            makeCauldron(POLISHED_GRANITE_CAULDRON.get());
            makeCauldron(DIORITE_CAULDRON.get());
            makeCauldron(POLISHED_DIORITE_CAULDRON.get());
            makeCauldron(ANDESITE_CAULDRON.get());
            makeCauldron(POLISHED_ANDESITE_CAULDRON.get());
        }

        private void makeCauldron(Block block) {
            String path = block.getRegistryName().getPath();
            getBuilder(path)
                    .parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                    .texture("layer0", modLoc("item/" + path));
        }

        @Override
        public String getName() {
            return "Item Models";
        }
    }

    private static class BlockStates extends BlockStateProvider {

        public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
            super(gen, ModReference.MOD_ID, helper);
        }

        @Override
        protected void registerStatesAndModels() {
            makeCauldron(ACACIA_CAULDRON.get(), mcLoc("block/acacia_planks"));
            makeCauldron(DARK_OAK_CAULDRON.get(), mcLoc("block/dark_oak_planks"));
            makeCauldron(BIRCH_CAULDRON.get(), mcLoc("block/birch_planks"));
            makeCauldron(JUNGLE_CAULDRON.get(), mcLoc("block/jungle_planks"));
            makeCauldron(OAK_CAULDRON.get(), mcLoc("block/oak_planks"));
            makeCauldron(SPRUCE_CAULDRON.get(), mcLoc("block/spruce_planks"));

            makeCauldron(GOLD_CAULDRON.get(), mcLoc("block/gold_block"));
            makeCauldron(DIAMOND_CAULDRON.get(), mcLoc("block/diamond_block"));

            makeCauldron(COBBLE_CAULDRON.get(), mcLoc("block/cobblestone"));
            makeCauldron(OBSIDIAN_CAULDRON.get(), mcLoc("block/obsidian"));
            makeCauldron(BRICK_CAULDRON.get(), mcLoc("block/bricks"));

            makeCauldron(STONE_CAULDRON.get(), mcLoc("block/stone"));
            makeCauldron(GRANITE_CAULDRON.get(), mcLoc("block/granite"));
            makeCauldron(POLISHED_GRANITE_CAULDRON.get(), mcLoc("block/polished_granite"));
            makeCauldron(DIORITE_CAULDRON.get(), mcLoc("block/diorite"));
            makeCauldron(POLISHED_DIORITE_CAULDRON.get(), mcLoc("block/polished_diorite"));
            makeCauldron(ANDESITE_CAULDRON.get(), mcLoc("block/andesite"));
            makeCauldron(POLISHED_ANDESITE_CAULDRON.get(), mcLoc("block/polished_andesite"));
        }

        private void makeCauldron(Block block, ResourceLocation textureLocation) {
            ModelFile cauldron = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cauldron")))
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation)
                    .texture("inside", textureLocation);

            ModelFile cauldron1 = models().getBuilder(block.getRegistryName().getPath() + "_level1")
                    .parent(models().getExistingFile(mcLoc("block/cauldron_level1")))
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation)
                    .texture("inside", textureLocation);

            ModelFile cauldron2 = models().getBuilder(block.getRegistryName().getPath() + "_level2")
                    .parent(models().getExistingFile(mcLoc("block/cauldron_level2")))
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation)
                    .texture("inside", textureLocation);

            ModelFile cauldron3 = models().getBuilder(block.getRegistryName().getPath() + "_level3")
                    .parent(models().getExistingFile(mcLoc("block/cauldron_level3")))
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation)
                    .texture("inside", textureLocation);

            getVariantBuilder(block)
                    .forAllStates(state -> {
                        int LEVEL = state.get(CauldronBlock.LEVEL).intValue();
                        switch (LEVEL) {
                            default:
                                return ConfiguredModel.builder().modelFile(cauldron).build();
                            case 1:
                                return ConfiguredModel.builder().modelFile(cauldron1).build();
                            case 2:
                                return ConfiguredModel.builder().modelFile(cauldron2).build();
                            case 3:
                                return ConfiguredModel.builder().modelFile(cauldron3).build();
                        }
                    });
        }
    }
}
