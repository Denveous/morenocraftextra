package net.morenorcraftextra;

import net.fabricmc.api.ModInitializer;
import net.morenorcraftextra.block.ModBlocks;
import net.morenorcraftextra.block.entity.ModBlockEntities;

public class MorenoCraftExtra implements ModInitializer {
    public static final String MOD_ID = "morenorcraftextra";

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerModBlockEntities();
    }
}