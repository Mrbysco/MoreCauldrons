package com.mrbysco.morecauldrons.handler;

import com.mrbysco.morecauldrons.config.MoreCauldronsConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CauldronHandler {
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        if(state.getBlock() instanceof CauldronBlock && MoreCauldronsConfig.SERVER.liquidDropping.get()) { //&& !ModList.get().isLoaded("inspirations")) {
            if(state.get(CauldronBlock.LEVEL) == 3) {
                event.getWorld().setBlockState(event.getPos(), Blocks.WATER.getDefaultState(), 6);
            }
        }
    }
}