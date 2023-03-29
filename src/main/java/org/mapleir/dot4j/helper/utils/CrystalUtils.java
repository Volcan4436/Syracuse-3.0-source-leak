package org.mapleir.dot4j.helper.utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

import static org.mapleir.dot4j.helper.utils.SyraMC.mc;

public enum CrystalUtils {
    ;

    public static boolean canPlaceCrystalServer(BlockPos block) {
        BlockState blockState = mc.world.getBlockState(block);
        if (!blockState.isOf(Blocks.OBSIDIAN) && !blockState.isOf(Blocks.BEDROCK))
            return false;
        BlockPos blockPos2 = block.up();
        if (!mc.world.isAir(blockPos2))
            return false;
        double d = blockPos2.getX();
        double e = blockPos2.getY();
        double f = blockPos2.getZ();
        List<Entity> list = mc.world.getOtherEntities(null, new Box(d, e, f, d + 1.0D, e + 2.0D, f + 1.0D));
        //list.removeIf(entity -> entity instanceof ItemEntity); // items will be picked up by the nearby player, crystals can be placed down a lot faster in citying
        list.removeIf(entity ->
        {
            if (!(entity instanceof EndCrystalEntity))
                return false;
            return false;
        });
        return list.isEmpty();
    }

    public static boolean canPlaceCrystalClient(BlockPos block) {
        BlockState blockState = mc.world.getBlockState(block);
        if (!blockState.isOf(Blocks.OBSIDIAN) && !blockState.isOf(Blocks.BEDROCK))
            return false;
        return canPlaceCrystalClientAssumeObsidian(block);
    }

    public static boolean canPlaceCrystalClientAssumeObsidian(BlockPos block) {
        BlockPos blockPos2 = block.up();
        if (!mc.world.isAir(blockPos2))
            return false;
        double d = blockPos2.getX();
        double e = blockPos2.getY();
        double f = blockPos2.getZ();
        List<Entity> list = mc.world.getOtherEntities(null, new Box(d, e, f, d + 1.0D, e + 2.0D, f + 1.0D));
        return list.isEmpty();
    }

    public static boolean canPlaceCrystalClientAssumeObsidian(BlockPos block, Box bb) {
        BlockPos blockPos2 = block.up();
        if (!mc.world.isAir(blockPos2))
            return false;
        double d = blockPos2.getX();
        double e = blockPos2.getY();
        double f = blockPos2.getZ();
        Box crystalBox = new Box(d, e, f, d + 1.0D, e + 2.0D, f + 1.0D);
        if (crystalBox.intersects(bb))
            return false;
        List<Entity> list = mc.world.getOtherEntities(null, crystalBox);
        return list.isEmpty();
    }
}