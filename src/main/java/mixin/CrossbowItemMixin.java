package mixin;

import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    // INFINITY
    @Inject(method = "loadProjectile", at = @At(value = "HEAD"), cancellable = true)
    private static void loadProjectile(LivingEntity pShooter, ItemStack pCrossbowStack, ItemStack pAmmoStack, boolean pHasAmmo, boolean pIsCreative, CallbackInfoReturnable<Boolean> cir) {
        if (pAmmoStack.isEmpty()) {
            cir.setReturnValue(false);
        } else {
            boolean flag = pIsCreative && pAmmoStack.getItem() instanceof ArrowItem;
            ItemStack itemstack;
            if (!flag && !pIsCreative && !pHasAmmo) {
                if(StardustUtils.INSTANCE.hasEnchantment(Enchantments.INFINITY_ARROWS, pCrossbowStack)) {
                    itemstack = pAmmoStack.copy();
                    itemstack.setCount(1);
                } else {
                    itemstack = pAmmoStack.split(1);
                }
                if (pAmmoStack.isEmpty() && pShooter instanceof Player) {
                    ((Player)pShooter).getInventory().removeItem(pAmmoStack);
                }
            } else {
                itemstack = pAmmoStack.copy();
            }

            stardust$addChargedProjectile(pCrossbowStack, itemstack);
            cir.setReturnValue(true);
        }
    }

    @Unique
    private static void stardust$addChargedProjectile(ItemStack pCrossbowStack, ItemStack pAmmoStack) {
        CompoundTag compoundtag = pCrossbowStack.getOrCreateTag();
        ListTag listtag;
        if (compoundtag.contains("ChargedProjectiles", 9)) {
            listtag = compoundtag.getList("ChargedProjectiles", 10);
        } else {
            listtag = new ListTag();
        }

        CompoundTag compoundtag1 = new CompoundTag();
        pAmmoStack.save(compoundtag1);
        listtag.add(compoundtag1);
        compoundtag.put("ChargedProjectiles", listtag);
    }

    // POWER PUNCH FLAME
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
