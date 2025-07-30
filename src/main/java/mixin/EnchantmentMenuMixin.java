package mixin;

import com.nyronium.stardust.content.experience.CostManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentMenu.class)
public class EnchantmentMenuMixin {
    @Inject(method = "clickMenuButton", at = @At("HEAD"))
    private void clickMenuButton(Player pPlayer, int pId, CallbackInfoReturnable<Boolean> cir) {
        CostManager.INSTANCE.getSelectedLocal().set(pId);
    }
}
