package org.mapleir.dot4j.helper.utils;

import net.minecraft.util.math.Vec3d;

import static org.mapleir.dot4j.helper.utils.PacketHelper.mc;

public class MoveHelper {

    public static boolean hasMovement()
    {
        final Vec3d playerMovement = mc.player.getVelocity();
        return playerMovement.getX() != 0 || playerMovement.getY() != 0 || playerMovement.getZ() != 0;
    }
}
