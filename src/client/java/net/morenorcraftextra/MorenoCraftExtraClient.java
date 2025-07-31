package net.morenorcraftextra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.morenorcraftextra.block.ModBlocks;
import net.morenorcraftextra.util.DoorSensorVisibility;

public class MorenoCraftExtraClient implements ClientModInitializer {
    private boolean lastHoldingState = false;
    private int tickCounter = 0;
    
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            tickCounter++;
            if (tickCounter % 10 != 0) return;
            
            PlayerEntity player = client.player;
            if (player == null) return;
            
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();
            
            boolean holdingDoorSensor = mainHand.getItem() == ModBlocks.DOOR_SENSOR.asItem() || offHand.getItem() == ModBlocks.DOOR_SENSOR.asItem() || 
                                        mainHand.getItem() == ModBlocks.DOOR_SENSOR_IMPULSE.asItem() || offHand.getItem() == ModBlocks.DOOR_SENSOR_IMPULSE.asItem();
            
            if (holdingDoorSensor != lastHoldingState) {
                lastHoldingState = holdingDoorSensor;
                DoorSensorVisibility.setVisible(holdingDoorSensor);
                
                BlockPos playerPos = player.getBlockPos();
                for (int x = -4; x <= 4; x++) {
                    for (int y = -3; y <= 3; y++) {
                        for (int z = -4; z <= 4; z++) {
                            BlockPos pos = playerPos.add(x, y, z);
                            if (client.world != null && (client.world.getBlockState(pos).getBlock() == ModBlocks.DOOR_SENSOR || 
                                client.world.getBlockState(pos).getBlock() == ModBlocks.DOOR_SENSOR_IMPULSE)) {
                                client.world.updateListeners(pos, client.world.getBlockState(pos), client.world.getBlockState(pos), 3);
                            }
                        }
                    }
                }
            }
        });
    }
}