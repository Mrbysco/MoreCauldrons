package com.mrbysco.morecauldrons;

import com.mrbysco.morecauldrons.client.ClientHandler;
import com.mrbysco.morecauldrons.config.MoreCauldronsConfig;
import com.mrbysco.morecauldrons.handler.CauldronHandler;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModReference.MOD_ID)
public class MoreCauldrons {
	public static final Logger LOGGER = LogManager.getLogger(ModReference.MOD_ID);

	public MoreCauldrons() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MoreCauldronsConfig.serverSpec);
		eventBus.register(MoreCauldronsConfig.class);

		CauldronRegistry.BLOCKS.register(eventBus);
		CauldronRegistry.ITEMS.register(eventBus);

		MinecraftForge.EVENT_BUS.register(new CauldronHandler());

		if(ModList.get().isLoaded("inspirations")) {
			if(knightminer.inspirations.common.Config.extendedCauldron.get()) {
				CauldronRegistry.registerInspirationsSupport();
				com.mrbysco.morecauldrons.compat.inspirations.CompatSetup.TILES.register(eventBus);
			} else {
				CauldronRegistry.registerVanilla();
			}
			eventBus.addListener(com.mrbysco.morecauldrons.compat.inspirations.CompatSetup::commonSetup);
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
				eventBus.addListener(EventPriority.LOWEST, com.mrbysco.morecauldrons.compat.inspirations.CompatClientHandler::registerModelLoaders);
				eventBus.addListener(com.mrbysco.morecauldrons.compat.inspirations.CompatClientHandler::registerBlockColors);
			});
		} else {
			CauldronRegistry.registerVanilla();
		}

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::doClientStuff);
			if(!ModList.get().isLoaded("inspirations")) {
				eventBus.addListener(ClientHandler::registerBlockColors);
			}
		});
	}
}