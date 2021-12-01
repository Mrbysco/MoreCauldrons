package com.mrbysco.morecauldrons.handler;

import com.mrbysco.morecauldrons.config.MoreCauldronsConfig;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CauldronHandler {
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        if(state.getBlock() instanceof LayeredCauldronBlock && MoreCauldronsConfig.SERVER.liquidDropping.get()) { //&& !ModList.get().isLoaded("inspirations")) {
            if(state.getValue(LayeredCauldronBlock.LEVEL) == 3) {
                event.getWorld().setBlock(event.getPos(), Blocks.WATER.defaultBlockState(), 6);
            }
        }
    }
}