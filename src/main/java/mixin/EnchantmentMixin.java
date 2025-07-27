package mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "canEnchant", at = @At("RETURN"), cancellable = true)
    private void canEnchant(ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        if(cir.getReturnValue() || !pStack.is(Tags.Items.TOOLS_CROSSBOWS)) return;

        Enchantment pThis = (Enchantment) (Object) this;
        boolean isCompatible = pThis == Enchantments.POWER_ARROWS ||
                pThis == Enchantments.PUNCH_ARROWS ||
                pThis == Enchantments.FLAMING_ARROWS ||
                pThis == Enchantments.INFINITY_ARROWS;

        cir.setReturnValue(isCompatible || cir.getReturnValue());
    }

    @Inject(method = "isCompatibleWith", at = @At("RETURN"), cancellable = true)
    private void isCompatibleWith(Enchantment pOther, CallbackInfoReturnable<Boolean> cir) {
        if(cir.getReturnValue()) return;

        Enchantment pThis = (Enchantment) (Object) this;
        boolean isCompatible = stardust$enchantmentMatch(pThis, pOther, Enchantments.INFINITY_ARROWS, Enchantments.MENDING) ||
                stardust$enchantmentMatch(pThis, pOther, Enchantments.PIERCING, Enchantments.MULTISHOT);

        cir.setReturnValue(isCompatible || cir.getReturnValue());
    }

    @Unique
    private boolean stardust$enchantmentMatch(Enchantment pThis, Enchantment pOther, Enchantment pCheckFirst, Enchantment pCheckSecond) {
        return (pThis == pCheckFirst && pOther == pCheckSecond) || (pThis == pCheckSecond && pOther == pCheckFirst);
    }
}
