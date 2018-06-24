package com.Mrbysco.MoreCauldrons.config;

import com.Mrbysco.MoreCauldrons.ModReference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModReference.MOD_ID)
@Config.LangKey("morecauldrons.config.title")
public class MoreCauldronsConfigGen {

	@Config.Comment({"General settings"})
	public static General general = new General();
	
	@Config.Comment({"Inspirations features"})
	public static Inspirations inspirations = new Inspirations();
	
	public static class General{
		@Config.Comment("When true the cauldron will drop the liquid that's in if it breaks while full. (when possible) [default: false]")
		public boolean liquidDropping = false;
	}
	
	public static class Inspirations{
		@Config.Comment("When true the cauldron will burn the player if inside while there's a hot liquid inside. [default: false]")
		public boolean burningInside = false;
	}
	
	@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ModReference.MOD_ID)) {
				ConfigManager.sync(ModReference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
