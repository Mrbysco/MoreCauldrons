package com.mrbysco.morecauldrons.handler;

public class InspirationsHandler {
//    @SubscribeEvent
//    public void CauldronTickEvent(TickEvent.WorldTickEvent event) {
//        if(event.phase == TickEvent.Phase.START)
//            return;
//
//        if(!event.world.isRemote) {
//            World world = event.world;
//            if (world.getGameTime() % 20 == 0) {
//                if(!MoreCauldronsConfig.SERVER.woodBurning.get())
//                    return;
//
//                ArrayList<TileEntity> TileList = new ArrayList<>(world.loadedTileEntityList);
//                for (TileEntity tile : TileList)
//                {
//                    if (world.isAreaLoaded(tile.getPos(), 3)) {
//                        if(tile instanceof CauldronTileEntity)
//                        {
//                            CauldronTileEntity cauldron = (CauldronTileEntity)tile;
//                            BlockPos pos = cauldron.getPos();
//                            if(world.getBlockState(pos).getBlock() instanceof EnhancedWoodenCauldronBlock) {
//                                if(cauldron.getContentType() == CauldronContents.FLUID)
//                                {
//                                    CauldronState state = CauldronState.fromNBT(cauldron.serializeNBT().getCompound(cauldron.TAG_STATE));
//                                    if(state.getFluid().getAttributes().getTemperature() >= 450)
//                                    {
//                                        if(world.rand.nextInt(10) < 2)
//                                        {
//                                            if(world.getBlockState(pos.north()).getMaterial() == Material.AIR && world.rand.nextInt(10) < 1)
//                                                world.setBlockState(pos.north(), Blocks.FIRE.getDefaultState(), 3);
//                                            if(world.getBlockState(pos.south()).getMaterial() == Material.AIR && world.rand.nextInt(10) < 1)
//                                                world.setBlockState(pos.south(), Blocks.FIRE.getDefaultState(), 3);
//                                            if(world.getBlockState(pos.west()).getMaterial() == Material.AIR && world.rand.nextInt(10) < 1)
//                                                world.setBlockState(pos.west(), Blocks.FIRE.getDefaultState(), 3);
//                                            if(world.getBlockState(pos.east()).getMaterial() == Material.AIR && world.rand.nextInt(10) < 1)
//                                                world.setBlockState(pos.east(), Blocks.FIRE.getDefaultState(), 3);
//
//                                            if(MoreCauldronsConfig.SERVER.liquidDropping.get())
//                                            {
//                                                BlockState fluidBlock = state.getFluid().getDefaultState().getBlockState();
//                                                if(world.rand.nextInt(100) < 3 && fluidBlock != null)
//                                                    world.setBlockState(pos, fluidBlock, 6);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}
