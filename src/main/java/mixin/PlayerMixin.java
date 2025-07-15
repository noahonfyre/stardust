package mixin;

import com.nyronium.stardust.Stardust;
import com.nyronium.stardust.misc.StardustUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow
    public abstract void causeFoodExhaustion(float pExhaustion);

    @Redirect(method = "checkMovementStatistics", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
    private void checkMovementStatistics(Player instance, float pExhaustion) {
        instance.sendSystemMessage(Component.literal("invoked"));
        if(!StardustUtils.INSTANCE.hasEnchantment(Stardust.INSTANCE.getENDURANCE().get(), instance.getItemBySlot(EquipmentSlot.LEGS))) {
            instance.swing(InteractionHand.MAIN_HAND);
            causeFoodExhaustion(pExhaustion);
        } else {
            causeFoodExhaustion(pExhaustion/(1+StardustUtils.INSTANCE.getLevel(Stardust.INSTANCE.getENDURANCE().get(), instance.getItemBySlot(EquipmentSlot.LEGS))));
        }
    }
}
