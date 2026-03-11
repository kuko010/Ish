package net.kuko.fisch.block.entity;

import net.kuko.fisch.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityExampleBlockEntity extends BlockEntity {

    public BlockEntityExampleBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.EXAMPLE_BLOCK_ENTITY_BE, blockPos, blockState);
    }
}
