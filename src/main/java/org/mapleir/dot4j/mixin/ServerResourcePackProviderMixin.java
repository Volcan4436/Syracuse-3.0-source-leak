package org.mapleir.dot4j.mixin;

import net.minecraft.client.resource.ServerResourcePackProvider;
import org.mapleir.dot4j.systems.module.core.ModuleManager;
import org.mapleir.dot4j.systems.module.impl.client.Spoofer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerResourcePackProvider.class)
public class ServerResourcePackProviderMixin {

    @ModifyArg(method = "loadServerPack(Ljava/io/File;Lnet/minecraft/resource/ResourcePackSource;)Ljava/util/concurrent/CompletableFuture;", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourcePackProfile;of(Ljava/lang/String;Lnet/minecraft/text/Text;ZLnet/minecraft/resource/ResourcePackProfile$PackFactory;Lnet/minecraft/resource/ResourcePackProfile$Metadata;Lnet/minecraft/resource/ResourceType;Lnet/minecraft/resource/ResourcePackProfile$InsertionPosition;ZLnet/minecraft/resource/ResourcePackSource;)Lnet/minecraft/resource/ResourcePackProfile;"), index = 7)
    private boolean loadServerPack(boolean isPinned) {
        if (ModuleManager.INSTANCE.getModuleByClass(Spoofer.class).isEnabled() && ((Spoofer) ModuleManager.INSTANCE.getModuleByClass(Spoofer.class)).resource.isEnabled()) {
            return false;
        }
        return isPinned;
    }

}