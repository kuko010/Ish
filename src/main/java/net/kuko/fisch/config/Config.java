package net.kuko.fisch.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fisch.Fisch;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    // Create one instance to hold your data
    public static ConfigOptions handler = new ConfigOptions();

    public static Screen genScreen(Screen screen) {
        return YetAnotherConfigLib.createBuilder()
                .save(Config::save)
                .title(Component.literal("Fisch Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("General"))
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Options"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Boolean Option"))
                                        .binding(
                                                true,
                                                () -> handler.myBooleanOption, // Use handler instance
                                                newVal -> handler.myBooleanOption = newVal
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .build().generateScreen(screen);
    }

    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("fisch.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void save() {
        try {
            // Save the handler instance instead of a "new Config()"
            Files.writeString(CONFIG_PATH, GSON.toJson(handler));
        } catch (IOException e) {
            Fisch.LOGGER.error("Failed to save config", e);
        }
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) return;
        try {
            // Load the data back into our handler instance
            handler = GSON.fromJson(Files.readString(CONFIG_PATH), ConfigOptions.class);
        } catch (IOException e) {
            Fisch.LOGGER.error("Failed to load config", e);
        }
    }
}