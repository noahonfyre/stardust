package mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nyronium.stardust.core.StardustRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
    @Inject(method = "shouldShowName", at = @At("RETURN"), cancellable = true)
    private void shouldShowName(Entity pEntity, CallbackInfoReturnable<Boolean> cir){
        if(pEntity instanceof Player player && StardustUtils.INSTANCE.hasEnchantment(StardustRegistry.INSTANCE.getSTEALTHINESS().get(), player.getItemBySlot(EquipmentSlot.HEAD))) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "renderNameTag", at = @At("RETURN"), cancellable = true)
    private void shouldShowName(Entity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, CallbackInfo ci){
        if(pEntity instanceof Player player && StardustUtils.INSTANCE.hasEnchantment(StardustRegistry.INSTANCE.getSTEALTHINESS().get(), player.getItemBySlot(EquipmentSlot.HEAD))) {
            ci.cancel();
        }
    }
}
