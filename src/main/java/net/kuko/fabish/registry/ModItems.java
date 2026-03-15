package net.kuko.fabish.registry;

import net.kuko.fabish.Fabish;
import net.kuko.fabish.registry.item.PewPewItem;
import net.kuko.fabish.registry.item.RainbowArrowItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    /*
    public static final Item SUSPICIOUS_SUBSTANCE = register(
		// Ignore the food component for now, we'll cover it later in the food section.
		new Item(new FabricItemSettings().food(SUSPICIOUS_FOOD_COMPONENT)),
		"suspicious_substance"
);
     */

    // Typed as RainbowArrowItem — all your custom methods are accessible
    public static final RainbowArrowItem RAINBOW_ARROW =
            (RainbowArrowItem) item("rainbow_arrow", new RainbowArrowItem(new Item.Properties()));

    public static final Item PEW_PEW = item("pew_pew", new PewPewItem(RAINBOW_ARROW));


    private static Item item(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Fabish.MOD_ID, name), item);
    }

    public static void register() {

    }
}