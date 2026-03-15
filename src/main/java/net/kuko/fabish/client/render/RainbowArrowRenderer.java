package net.kuko.fabish.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.kuko.fabish.Fabish;
import net.kuko.fabish.client.shader.ModRenderTypes;
import net.kuko.fabish.client.shader.ModShaders;
import net.kuko.fabish.registry.entity.AbstractRainbowArrow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jspecify.annotations.NonNull;

public class RainbowArrowRenderer extends ArrowRenderer<AbstractRainbowArrow> {

    public RainbowArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(AbstractRainbowArrow entity, float yaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        // Manually push GameTime to the shader every frame
        if (ModShaders.RAINBOW_ARROW != null) {
            float gameTime = (entity.level().getGameTime() + partialTick) / 20.0f;
            ModShaders.RAINBOW_ARROW.safeGetUniform("GameTime").set(gameTime);
            ModShaders.RAINBOW_ARROW.safeGetUniform("GlobalPosX").set((float) entity.getX());
            ModShaders.RAINBOW_ARROW.safeGetUniform("GlobalPosY").set((float) entity.getY());
            ModShaders.RAINBOW_ARROW.safeGetUniform("GlobalPosZ").set((float) entity.getZ());
        }

        super.render(entity, yaw, partialTick, poseStack,
                renderType -> bufferSource.getBuffer(ModRenderTypes.rainbowArrow(getTextureLocation(entity))),
                packedLight);
    }

    @Override
    public @NonNull ResourceLocation getTextureLocation(AbstractRainbowArrow entity) {
        return new ResourceLocation(Fabish.MOD_ID, "textures/entity/projectiles/gray_arrow.png");
    }
}