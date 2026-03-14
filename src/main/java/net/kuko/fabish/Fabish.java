package net.kuko.fabish;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fabish.compat.config.ConfigUtils;
import net.kuko.fabish.data.DataRegistry;
import net.kuko.fabish.registry.ModBlockEntities;
import net.kuko.fabish.registry.ModBlocks;
import net.kuko.fabish.registry.ModEnergy;
import net.kuko.fabish.registry.ModItems;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fabish implements ModInitializer {
	public static final String MOD_ID = "fabish";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation id(String id) {
		return new ResourceLocation(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModBlocks.register();
		ModBlockEntities.register();
		ModItems.register();
		ModEnergy.register();
		DataRegistry.register();

		if (FabricLoader.getInstance().isModLoaded("computercraft")) {
			net.kuko.fabish.compat.computercraft.UpgradeRegistry.register();
		}

		ConfigUtils.load(ConfigUtils.handler);
	}
}