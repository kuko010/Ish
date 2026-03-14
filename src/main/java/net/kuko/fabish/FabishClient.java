package net.kuko.fabish;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class FabishClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyMapping openScreen = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.fisch.open_paper",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"
                ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

        });
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            net.kuko.fabish.compat.computercraft.UpgradeRegistry.clientRegister();
        }
    }
}