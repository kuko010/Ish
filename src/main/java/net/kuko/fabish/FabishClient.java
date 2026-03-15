package net.kuko.fabish;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fabish.client.render.RainbowArrowRenderer;
import net.kuko.fabish.client.shader.ModShaders;
import net.kuko.fabish.registry.ModEntityTypes;
import net.minecraft.resources.ResourceLocation;

public class FabishClient implements ClientModInitializer {





    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(
                ModEntityTypes.RAINBOW_ARROW,
                RainbowArrowRenderer::new
        );

        // In FabishClient.onInitializeClient
        CoreShaderRegistrationCallback.EVENT.register(context -> {
            context.register(new ResourceLocation(Fabish.MOD_ID, "rainbow_arrow"),
                    DefaultVertexFormat.NEW_ENTITY, shader -> {
                        ModShaders.RAINBOW_ARROW = shader;
                    });
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

        });
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            net.kuko.fabish.compat.computercraft.UpgradeRegistry.clientRegister();
        }
    }
}