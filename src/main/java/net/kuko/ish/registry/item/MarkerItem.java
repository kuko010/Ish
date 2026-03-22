package net.kuko.ish.registry.item;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kuko.ish.IshClient;
import net.minecraft.core.BlockPos;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class MarkerItem extends Item {
    public MarkerItem(FabricItemSettings settings) {
        super(settings);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) { // server

        } else { // client

        }

        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
    }
}
