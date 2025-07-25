package mixin;

import com.nyronium.stardust.core.StardustRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    @Inject(method = "isWearingGold", at = @At("RETURN"), cancellable = true)
    private static void isWearingGold(LivingEntity pLivingEntity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() ||
                StardustUtils.INSTANCE.hasEnchantment(
                        StardustRegistry.INSTANCE.getPEERING().get(),
                        pLivingEntity.getItemBySlot(EquipmentSlot.HEAD)
                )
        );
    }
}
