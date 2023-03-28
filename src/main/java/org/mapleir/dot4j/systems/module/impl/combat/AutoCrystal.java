package org.mapleir.dot4j.systems.module.impl.combat;

import net.minecraft.block.Blocks;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.mapleir.dot4j.helper.utils.CrystalUtils;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

@Module.Info(name = "AutoCrystal", description = "haxer tester", category = Category.COMBAT)
public class AutoCrystal extends Module {

    public void placeCrystal() {

        Vec3d cameraPos = mc.player.getCameraPosVec(1.0F);
        Vec3d rotationVec = mc.player.getRotationVec(1.0F);
        Vec3d targetPos = cameraPos.add(rotationVec.multiply(4.5D));
        BlockHitResult hit = mc.world.raycast(new RaycastContext(cameraPos, targetPos, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, mc.player));
        if (hit.getType() == HitResult.Type.BLOCK && CrystalUtils.canPlaceCrystalServer(hit.getBlockPos()) && (Blocks.OBSIDIAN == mc.world.getBlockState(hit.getBlockPos()).getBlock() || Blocks.BEDROCK == mc.world.getBlockState(hit.getBlockPos()).getBlock())) {
            ActionResult result = mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
            if (result.isAccepted() && result.shouldSwingHand()) {
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }

    public void breakCrystal() {
        if (mc.crosshairTarget instanceof EntityHitResult hit) {
            if (hit.getEntity() instanceof EndCrystalEntity crystal) {
                if (mc.player.getMainHandStack().isOf(Items.END_CRYSTAL)) {
                    mc.interactionManager.attackEntity(mc.player, crystal);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }

    @Override
    public void onTick() {
        placeCrystal();
        breakCrystal();
    }
}