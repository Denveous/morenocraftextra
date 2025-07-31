package net.morenorcraftextra.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.morenorcraftextra.block.custom.DoorSensorImpulseBlock;
import java.util.List;

public class DoorSensorImpulseBlockEntity extends BlockEntity {
    private boolean lastDetectionState = false;
    private int pulseTicks = 0;

    public DoorSensorImpulseBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DOOR_SENSOR_IMPULSE, pos, state);
    }

    public void tick() {
        World world = this.getWorld();
        if (world == null || world.isClient()) return;

        if (pulseTicks > 0) {
            pulseTicks--;
            if (pulseTicks == 0) {
                BlockState state = world.getBlockState(pos);
                world.setBlockState(pos, state.with(DoorSensorImpulseBlock.POWERED, false));
                world.updateNeighbors(pos, getCachedState().getBlock());
            }
            return;
        }

        Box detectionBox = new Box(pos.getX() - 2, pos.getY() - 1, pos.getZ() - 2, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3);
        List<PlayerEntity> players = world.getEntitiesByClass(PlayerEntity.class, detectionBox, player -> !player.isSpectator());
        boolean currentDetection = !players.isEmpty();

        if (currentDetection && !lastDetectionState) {
            BlockState state = world.getBlockState(pos);
            world.setBlockState(pos, state.with(DoorSensorImpulseBlock.POWERED, true));
            world.updateNeighbors(pos, getCachedState().getBlock());
            pulseTicks = 50;
        }

        lastDetectionState = currentDetection;
    }
}