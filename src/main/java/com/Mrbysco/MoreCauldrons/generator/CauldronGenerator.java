package com.mrbysco.morecauldrons.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mrbysco.morecauldrons.ModReference;
import com.mrbysco.morecauldrons.init.CauldronReg;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

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
        protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
            return ImmutableList.of(
                    Pair.of(Blocks::new, LootContextParamSets.BLOCK)
            );
        }

        @Override
        protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
            map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
        }
        private class Blocks extends BlockLoot {
            @Override
            protected void addTables() {
                this.dropCauldron(ACACIA_CAULDRON);
                this.dropCauldron(DARK_OAK_CAULDRON);
                this.dropCauldron(BIRCH_CAULDRON);
                this.dropCauldron(JUNGLE_CAULDRON);
                this.dropCauldron(OAK_CAULDRON);
                this.dropCauldron(SPRUCE_CAULDRON);

                this.dropCauldron(GOLD_CAULDRON);
                this.dropCauldron(DIAMOND_CAULDRON);
                this.dropCauldron(COPPER_CAULDRON);
                this.dropCauldron(COBBLE_CAULDRON);
                this.dropGlassCauldron(GLASS_CAULDRON);
                this.dropCauldron(OBSIDIAN_CAULDRON);
                this.dropCauldron(BRICK_CAULDRON);

                this.dropCauldron(STONE_CAULDRON);
                this.dropCauldron(GRANITE_CAULDRON);
                this.dropCauldron(POLISHED_GRANITE_CAULDRON);
                this.dropCauldron(DIORITE_CAULDRON);
                this.dropCauldron(POLISHED_DIORITE_CAULDRON);
                this.dropCauldron(ANDESITE_CAULDRON);
                this.dropCauldron(POLISHED_ANDESITE_CAULDRON);
            }

            private void dropCauldron(CauldronReg reg) {
                this.dropSelf(reg.getCauldron().get());
                this.dropOther(reg.getWaterCauldron().get(), reg.getCauldron().get());
                this.dropOther(reg.getLavaCauldron().get(), reg.getCauldron().get());
                this.dropOther(reg.getPowderSnowCauldron().get(), reg.getCauldron().get());
            }

            private void dropGlassCauldron(CauldronReg reg) {
                this.dropWhenSilkTouch(reg.getCauldron().get());
                this.otherWhenSilkTouch(reg.getWaterCauldron().get(), reg.getCauldron().get());
                this.otherWhenSilkTouch(reg.getLavaCauldron().get(), reg.getCauldron().get());
                this.otherWhenSilkTouch(reg.getPowderSnowCauldron().get(), reg.getCauldron().get());
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
            makeCauldron(ACACIA_CAULDRON.getCauldron().get());
            makeCauldron(DARK_OAK_CAULDRON.getCauldron().get());
            makeCauldron(BIRCH_CAULDRON.getCauldron().get());
            makeCauldron(JUNGLE_CAULDRON.getCauldron().get());
            makeCauldron(OAK_CAULDRON.getCauldron().get());
            makeCauldron(SPRUCE_CAULDRON.getCauldron().get());

            makeCauldron(GOLD_CAULDRON.getCauldron().get());
            makeCauldron(DIAMOND_CAULDRON.getCauldron().get());
//            makeCauldron(COPPER_CAULDRON.getCauldron().get());

            makeCauldron(COBBLE_CAULDRON.getCauldron().get());
            makeCauldron(GLASS_CAULDRON.getCauldron().get());
            makeCauldron(OBSIDIAN_CAULDRON.getCauldron().get());
            makeCauldron(BRICK_CAULDRON.getCauldron().get());

            makeCauldron(STONE_CAULDRON.getCauldron().get());
            makeCauldron(GRANITE_CAULDRON.getCauldron().get());
            makeCauldron(POLISHED_GRANITE_CAULDRON.getCauldron().get());
            makeCauldron(DIORITE_CAULDRON.getCauldron().get());
            makeCauldron(POLISHED_DIORITE_CAULDRON.getCauldron().get());
            makeCauldron(ANDESITE_CAULDRON.getCauldron().get());
            makeCauldron(POLISHED_ANDESITE_CAULDRON.getCauldron().get());
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
            makeCauldron(ACACIA_CAULDRON, mcLoc("block/acacia_planks"));
            makeCauldron(DARK_OAK_CAULDRON, mcLoc("block/dark_oak_planks"));
            makeCauldron(BIRCH_CAULDRON, mcLoc("block/birch_planks"));
            makeCauldron(JUNGLE_CAULDRON, mcLoc("block/jungle_planks"));
            makeCauldron(OAK_CAULDRON, mcLoc("block/oak_planks"));
            makeCauldron(SPRUCE_CAULDRON, mcLoc("block/spruce_planks"));

            makeCauldron(GOLD_CAULDRON, mcLoc("block/gold_block"));
            makeCauldron(DIAMOND_CAULDRON, mcLoc("block/diamond_block"));
            makeCauldron(COPPER_CAULDRON, mcLoc("block/copper_block"));

            makeCauldron(COBBLE_CAULDRON, mcLoc("block/cobblestone"));
            makeCauldron(OBSIDIAN_CAULDRON, mcLoc("block/obsidian"));
            makeCauldron(BRICK_CAULDRON, mcLoc("block/bricks"));

            makeCauldron(STONE_CAULDRON, mcLoc("block/stone"));
            makeCauldron(GRANITE_CAULDRON, mcLoc("block/granite"));
            makeCauldron(POLISHED_GRANITE_CAULDRON, mcLoc("block/polished_granite"));
            makeCauldron(DIORITE_CAULDRON, mcLoc("block/diorite"));
            makeCauldron(POLISHED_DIORITE_CAULDRON, mcLoc("block/polished_diorite"));
            makeCauldron(ANDESITE_CAULDRON, mcLoc("block/andesite"));
            makeCauldron(POLISHED_ANDESITE_CAULDRON, mcLoc("block/polished_andesite"));
        }

        private void makeCauldron(CauldronReg reg, ResourceLocation textureLoc) {
            makeCauldron(reg.getCauldron().get(), textureLoc);
            makeWaterCauldron(reg.getWaterCauldron().get(), textureLoc);
            makeLavaCauldron(reg.getLavaCauldron().get(), textureLoc);
            makeSnowCauldron(reg.getPowderSnowCauldron().get(), textureLoc);
        }

        private void makeCauldron(Block block, ResourceLocation textureLocation) {
            ModelFile cauldron = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cauldron")))
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation)
                    .texture("inside", textureLocation);

            getVariantBuilder(block).forAllStates(state ->  ConfiguredModel.builder().modelFile(cauldron).build());
        }

        private void makeLavaCauldron(Block block, ResourceLocation textureLocation) {
            ModelFile cauldron = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/lava_cauldron")))
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation)
                    .texture("inside", textureLocation);

            getVariantBuilder(block).forAllStates(state ->  ConfiguredModel.builder().modelFile(cauldron).build());
        }

        private void makeWaterCauldron(Block block, ResourceLocation textureLocation) {
            ModelFile level1 = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/water_cauldron_level1")))
                    .texture("inside", textureLocation)
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation);

            ModelFile level2 = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/water_cauldron_level2")))
                    .texture("inside", textureLocation)
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation);


            ModelFile full = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/water_cauldron_full")))
                    .texture("inside", textureLocation)
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation);

            getVariantBuilder(block)
                    .partialState().with(LayeredCauldronBlock.LEVEL, 1)
                    .modelForState().modelFile(level1).addModel()
                    .partialState().with(LayeredCauldronBlock.LEVEL, 2)
                    .modelForState().modelFile(level2).addModel()
                    .partialState().with(LayeredCauldronBlock.LEVEL, 3)
                    .modelForState().modelFile(full).addModel();
        }


        private void makeSnowCauldron(Block block, ResourceLocation textureLocation) {
            ModelFile level1 = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/powder_snow_cauldron_level1")))
                    .texture("inside", textureLocation)
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation);

            ModelFile level2 = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/powder_snow_cauldron_level2")))
                    .texture("inside", textureLocation)
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation);


            ModelFile full = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/powder_snow_cauldron_full")))
                    .texture("inside", textureLocation)
                    .texture("particle", textureLocation)
                    .texture("top", textureLocation)
                    .texture("bottom", textureLocation)
                    .texture("side", textureLocation);

            getVariantBuilder(block)
                    .partialState().with(LayeredCauldronBlock.LEVEL, 1)
                    .modelForState().modelFile(level1).addModel()
                    .partialState().with(LayeredCauldronBlock.LEVEL, 2)
                    .modelForState().modelFile(level2).addModel()
                    .partialState().with(LayeredCauldronBlock.LEVEL, 3)
                    .modelForState().modelFile(full).addModel();
        }
    }
}
