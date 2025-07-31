package net.morenorcraftextra.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.morenorcraftextra.MorenoCraftExtra;
import net.morenorcraftextra.block.custom.DoorSensorBlock;
import net.morenorcraftextra.block.custom.DoorSensorImpulseBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block PLAINS_GRASS_BLOCK = registerBlock("plains_grass_block", new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block DESERT_GRASS_BLOCK = registerBlock("desert_grass_block", new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block JUNGLE_GRASS_BLOCK = registerBlock("jungle_grass_block", new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block TAIGA_GRASS_BLOCK = registerBlock("taiga_grass_block", new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block SWAMP_GRASS_BLOCK = registerBlock("swamp_grass_block", new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block SAVANNA_GRASS_BLOCK = registerBlock("savanna_grass_block", new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block DOOR_SENSOR = registerBlock("door_sensor", new DoorSensorBlock(AbstractBlock.Settings.copy(Blocks.STONE).nonOpaque()));
    public static final Block DOOR_SENSOR_IMPULSE = registerBlock("door_sensor_impulse", new DoorSensorImpulseBlock(AbstractBlock.Settings.copy(Blocks.STONE).nonOpaque()));

    public static final Item TAB_ICON = Registry.register(Registries.ITEM, Identifier.of(MorenoCraftExtra.MOD_ID, "tab_icon"), new Item(new Item.Settings()));

    public static final ItemGroup MORENO_CRAFT_EXTRA = Registry.register(Registries.ITEM_GROUP, Identifier.of(MorenoCraftExtra.MOD_ID, "moreno_craft_extra"), FabricItemGroup.builder().icon(() -> new ItemStack(TAB_ICON)).displayName(Text.translatable("itemgroup.moreno_craft_extra")).entries((displayContext, entries) -> {
        entries.add(PLAINS_GRASS_BLOCK);
        entries.add(DESERT_GRASS_BLOCK);
        entries.add(JUNGLE_GRASS_BLOCK);
        entries.add(TAIGA_GRASS_BLOCK);
        entries.add(SWAMP_GRASS_BLOCK);
        entries.add(SAVANNA_GRASS_BLOCK);
        entries.add(DOOR_SENSOR);
        entries.add(DOOR_SENSOR_IMPULSE);
    }).build());

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MorenoCraftExtra.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(MorenoCraftExtra.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
    }
}