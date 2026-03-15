package net.kuko.fabish.registry.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class PewPewItem extends BowItem {

    // Store the Item, not a mutable ItemStack
    private final ArrowItem arrowItem;

    public PewPewItem(ArrowItem arrowItem) {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
        this.arrowItem = arrowItem;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingTicks) {
        if (!(livingEntity instanceof Player player)) return;
        if (level.isClientSide()) return;

        int usedTicks = getUseDuration(stack) - remainingTicks;
        float force = getPowerForTime(usedTicks);
        if (force < 0.1F) return;

        spawnArrow(stack, level, player, force);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS,
                1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + force * 0.5F);

        player.awardStat(Stats.ITEM_USED.get(this));
    }

    // releaseUsing handles the single shot when the player lets go early
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        if (!(livingEntity instanceof Player player)) return;
        if (level.isClientSide()) return;

        int usedTicks = getUseDuration(stack) - timeLeft;
        float force = getPowerForTime(usedTicks);
        if (force < 0.1F) return;

        spawnArrow(stack, level, player, force);
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    private void spawnArrow(ItemStack stack, Level level, Player player, float force) {
        // Safe: arrowItem is already guaranteed ArrowItem by the constructor type
        Projectile projectile = arrowItem.createArrow(level, new ItemStack(arrowItem), player);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(),
                0.0F, force * 3.0F, 1.0F);
        level.addFreshEntity(projectile);
    }
}