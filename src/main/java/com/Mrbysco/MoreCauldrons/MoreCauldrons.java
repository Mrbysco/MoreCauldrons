package com.Mrbysco.MoreCauldrons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Mrbysco.MoreCauldrons.events.EnhancedRecipeEvents;
import com.Mrbysco.MoreCauldrons.init.CauldronTab;
import com.Mrbysco.MoreCauldrons.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModReference.MOD_ID, 
	name = ModReference.MOD_NAME, 
	version = ModReference.VERSION, 
	acceptedMinecraftVersions = ModReference.ACCEPTED_VERSIONS,
	dependencies = ModReference.DEPENDENCIES)

public class MoreCauldrons {
	@Instance(ModReference.MOD_ID)
	public static MoreCauldrons instance;
	
	@SidedProxy(clientSide = ModReference.CLIENT_PROXY_CLASS, serverSide = ModReference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger logger = LogManager.getLogger(ModReference.MOD_ID);
	
	public static boolean inspirationsLoaded;
	
	public static CauldronTab cauldronTab = new CauldronTab();
			
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		inspirationsLoaded = Loader.isModLoaded("inspirations");
		
		//logger.debug("Registering config");
		//MinecraftForge.EVENT_BUS.register(new MoreCauldronsConfigGen());

		proxy.Preinit();
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event)
	{
		if(inspirationsLoaded)
		{
			logger.debug("Registering event handlers");
			MinecraftForge.EVENT_BUS.register(EnhancedRecipeEvents.class);
		}
		
		proxy.Init();
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
}