package com.mrbysco.morecauldrons.config;

import com.mrbysco.morecauldrons.MoreCauldrons;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class MoreCauldronsConfig {

	public static class Server {
		public final BooleanValue liquidDropping;
//		public final BooleanValue woodBurning;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Server settings")
					.push("Server");

			liquidDropping = builder
					.comment("When enabled the cauldron will drop the liquid that's in if it breaks while full. (when Inspirations isn't installed) [default: false]")
					.define("liquidDropping", false);

//			woodBurning = builder
//					.comment("When enabled wooden cauldrons will be set on fire if a hot liquid is inside (when Inspirations is installed) [default: true]")
//					.define("woodBurning", true);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec serverSpec;
	public static final MoreCauldronsConfig.Server SERVER;

	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(MoreCauldronsConfig.Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		MoreCauldrons.LOGGER.debug("Loaded More Cauldron's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		MoreCauldrons.LOGGER.debug("More Cauldron's config just got changed on the file system!");
	}
}
