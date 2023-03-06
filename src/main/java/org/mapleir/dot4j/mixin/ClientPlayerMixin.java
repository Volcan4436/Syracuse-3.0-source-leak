package org.mapleir.dot4j.mixin;

import org.mapleir.dot4j.event.impl.HandSwingEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin {

    @Inject(at = @At("HEAD"), method = "swingHand")
    public void swingHand(Hand hand, CallbackInfo ci) {
        new HandSwingEvent(hand).call();
    }
}
