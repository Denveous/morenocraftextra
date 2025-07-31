package net.morenorcraftextra.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import java.util.List;

public class DoorSensorBlockEntity extends BlockEntity {
    public DoorSensorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.DOOR_SENSOR_BLOCK_ENTITY, blockPos, blockState);
    }

    private boolean shouldPower(World world, BlockPos pos, BlockState state) {
        Box detectionBox = new Box(pos).expand(1.0);
        List<PlayerEntity> players = world.getEntitiesByClass(PlayerEntity.class, detectionBox, player -> true);
        return !players.isEmpty();
    }

    public void tick() {
        BlockPos pos = this.getPos();
        BlockState state = this.getCachedState();
        if (this.world != null && !this.world.isClient) {
            boolean shouldPower = this.shouldPower(this.world, pos, state);
            if (shouldPower != state.get(Properties.POWERED)) {
                this.world.setBlockState(pos, state.with(Properties.POWERED, shouldPower), 3);
                state.updateNeighbors(this.world, pos, 3);
                for (Direction direction : Direction.values()) {
                    this.world.updateNeighborsAlways(pos.offset(direction), state.getBlock());
                }
            }
        }
    }
}