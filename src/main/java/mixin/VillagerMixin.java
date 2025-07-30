package mixin;

import com.nyronium.stardust.core.StardustRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class VillagerMixin {
    @Inject(method = "updateSpecialPrices", at = @At("HEAD"))
    private void updateSpecialPrices(Player pPlayer, CallbackInfo ci) {
        if(!StardustUtils.INSTANCE.hasEnchantment(StardustRegistry.INSTANCE.getCHARISMA().get(), pPlayer.getItemBySlot(EquipmentSlot.HEAD))) return;
        int charismaLevel = StardustUtils.INSTANCE.getLevel(StardustRegistry.INSTANCE.getCHARISMA().get(), pPlayer.getItemBySlot(EquipmentSlot.HEAD));

        Villager self = (Villager) (Object) this;

        for(MerchantOffer offer : self.getOffers()) {
            int value = Math.toIntExact(Math.round(Math.floor(((double) offer.getBaseCostA().getCount()) / 8 * charismaLevel)));
            offer.setSpecialPriceDiff(-value);
        }
    }
}
