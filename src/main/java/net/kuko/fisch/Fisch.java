package net.kuko.fisch;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fisch.compat.config.ConfigUtils;
import net.kuko.fisch.data.DataRegistry;
import net.kuko.fisch.registry.ModBlockEntities;
import net.kuko.fisch.registry.ModBlocks;
import net.kuko.fisch.registry.ModEnergy;
import net.kuko.fisch.registry.ModItems;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fisch implements ModInitializer {
	public static final String MOD_ID = "fisch";
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
			net.kuko.fisch.compat.computercraft.UpgradeRegistry.register();
		}

		ConfigUtils.load(ConfigUtils.handler);
	}
}