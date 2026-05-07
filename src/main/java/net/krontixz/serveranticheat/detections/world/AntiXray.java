package net.krontixz.serveranticheat.detections.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.util.math.BlockPos;

public class AntiXray {
    public static BlockState obfuscate(BlockPos pos, BlockState original, WorldChunk chunk) {
        if (!isOre(original)) return original;
        
        for (BlockPos neighbor : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            if (chunk.getBlockState(neighbor).isAir()) {
                return original;
            }
        }
        
        return Blocks.STONE.getDefaultState();
    }

    private static boolean isOre(BlockState state) {
        return state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE) || 
               state.isOf(Blocks.NETHER_QUARTZ_ORE) || state.isOf(Blocks.ANCIENT_DEBRIS);
    }
}
