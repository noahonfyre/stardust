package mixin;

import com.nyronium.stardust.core.StardustRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractVillager.class)
public class AbstractVillagerMixin {
    @Redirect(method = "notifyTrade", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/trading/MerchantOffer;increaseUses()V"))
    private void notifyTrade(MerchantOffer instance) {
        AbstractVillager villager = (AbstractVillager) (Object) this;
        Player player = villager.getTradingPlayer();
        if(player == null) return;
        if(!StardustUtils.INSTANCE.hasEnchantment(StardustRegistry.INSTANCE.getCHARISMA().get(), player.getItemBySlot(EquipmentSlot.HEAD))){
            instance.increaseUses();
            return;
        }

        int level = StardustUtils.INSTANCE.getEnchantmentLevel(StardustRegistry.INSTANCE.getCHARISMA().get(), player.getItemBySlot(EquipmentSlot.HEAD));
        int random = (int) (Math.random()*100);
        if(!(random <= 100*(level/StardustRegistry.INSTANCE.getCHARISMA().get().getMaxLevel()))) {
            instance.increaseUses();
        }

        System.out.println(instance.getUses());
        System.out.println(instance.getMaxUses());
    }
}
