package net.kuko.fabish.registry.entity;

import net.kuko.fabish.registry.ModEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class AbstractRainbowArrow extends AbstractArrow {

    // Constructor for registration/factory
    public AbstractRainbowArrow(EntityType<? extends AbstractRainbowArrow> entityType, Level level) {
        super(entityType, level);
    }

    public AbstractRainbowArrow(Level level, LivingEntity shooter) {
        super(ModEntityTypes.RAINBOW_ARROW, shooter, level);
    }


    @Override
    protected @NonNull ItemStack getPickupItem() {
        return ItemStack.EMPTY; // Never null — causes NPE in vanilla pickup logic
    }

    // NEW: Ensures the entity spawns correctly on the client side
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}