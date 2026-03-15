package net.kuko.fabish.registry.item;

import net.kuko.fabish.registry.entity.AbstractRainbowArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArrowItem;   // extend ArrowItem, not Item
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RainbowArrowItem extends ArrowItem {

    public RainbowArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractRainbowArrow createArrow(Level level, ItemStack itemStack, LivingEntity shooter) {
        // Use the new shooter constructor instead of setting the owner manually
        return new AbstractRainbowArrow(level, shooter);
    }
}