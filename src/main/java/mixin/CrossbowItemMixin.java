package mixin;

import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
    @Inject(method = "getArrow", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void getArrow(Level pLevel, LivingEntity pLivingEntity, ItemStack pCrossbowStack, ItemStack pAmmoStack, CallbackInfoReturnable<AbstractArrow> cir, ArrowItem arrowitem, AbstractArrow abstractarrow) {
        int j = StardustUtils.INSTANCE.getLevel(Enchantments.POWER_ARROWS, pCrossbowStack);
        if (j > 0) {
            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D);
        }

        int k = StardustUtils.INSTANCE.getLevel(Enchantments.PUNCH_ARROWS, pCrossbowStack);
        if (k > 0) {
            abstractarrow.setKnockback(k);
        }

        if (StardustUtils.INSTANCE.hasEnchantment(Enchantments.FLAMING_ARROWS, pCrossbowStack)) {
            abstractarrow.setSecondsOnFire(100);
        }
        cir.setReturnValue(abstractarrow);
    }
}
