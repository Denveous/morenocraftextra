package net.morenorcraftextra.block.entity;

import net.morenorcraftextra.MorenoCraftExtra;
import net.morenorcraftextra.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<DoorSensorBlockEntity> DOOR_SENSOR_BLOCK_ENTITY;
    public static BlockEntityType<DoorSensorImpulseBlockEntity> DOOR_SENSOR_IMPULSE;

    public static void registerModBlockEntities() {
        DOOR_SENSOR_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, MorenoCraftExtra.MOD_ID + ":door_sensor_be", FabricBlockEntityTypeBuilder.create(DoorSensorBlockEntity::new, ModBlocks.DOOR_SENSOR).build());
        DOOR_SENSOR_IMPULSE = Registry.register(Registries.BLOCK_ENTITY_TYPE, MorenoCraftExtra.MOD_ID + ":door_sensor_impulse_be", FabricBlockEntityTypeBuilder.create(DoorSensorImpulseBlockEntity::new, ModBlocks.DOOR_SENSOR_IMPULSE).build());
    }
}