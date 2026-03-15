package net.kuko.fabish.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kuko.fabish.Fabish;
import net.kuko.fabish.registry.entity.AbstractRainbowArrow;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {

    public static final EntityType<AbstractRainbowArrow> RAINBOW_ARROW =
            Registry.register(
                    BuiltInRegistries.ENTITY_TYPE,
                    new ResourceLocation(Fabish.MOD_ID, "rainbow_arrow"),
                    FabricEntityTypeBuilder.<AbstractRainbowArrow>create(
                                    MobCategory.MISC,
                                    AbstractRainbowArrow::new
                            )
                            .dimensions(EntityDimensions.fixed(0.5F, 0.5F)) // hitbox size
                            .trackRangeBlocks(64)   // how far clients sync this entity
                            .trackedUpdateRate(1)  // update frequency in ticks
                            .build()
            );

    // Call this in your mod initializer to trigger static field loading
    public static void register() {}
}