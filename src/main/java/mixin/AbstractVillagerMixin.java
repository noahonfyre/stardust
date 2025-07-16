package mixin;

import com.nyronium.stardust.Stardust;
import com.nyronium.stardust.misc.StardustUtils;
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
        if(!StardustUtils.INSTANCE.hasEnchantment(Stardust.INSTANCE.getCHARISMA().get(), player.getItemBySlot(EquipmentSlot.HEAD))){
            instance.increaseUses();
            return;
        }

        int level = StardustUtils.INSTANCE.getLevel(Stardust.INSTANCE.getCHARISMA().get(), player.getItemBySlot(EquipmentSlot.HEAD));
        int random = (int) (Math.random()*100);
        if(!(random <= 100*(level/Stardust.INSTANCE.getCHARISMA().get().getMaxLevel()))) {
            instance.increaseUses();
        }

        System.out.println(instance.getUses());
        System.out.println(instance.getMaxUses());
    }
}
