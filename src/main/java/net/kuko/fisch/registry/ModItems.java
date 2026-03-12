package net.kuko.fisch.registry;

import net.kuko.fisch.Fisch;
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


    private static Item item(Item item,String name) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Fisch.MOD_ID, name), item);
    }

    public static void register() {

    }
}