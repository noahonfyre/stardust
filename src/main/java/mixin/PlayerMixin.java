package mixin;

import com.nyronium.stardust.core.StardustRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow
    public abstract void causeFoodExhaustion(float pExhaustion);

    // SOULBOUND
    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V"))
    private void dropEquipment(Inventory instance) {
        List<NonNullList<ItemStack>> compartments = ((InventoryAccessor) instance).getCompartments();
        for(List<ItemStack> list : compartments) {
            for(int i = 0; i < list.size(); ++i) {
                ItemStack itemstack = list.get(i);
                if (!itemstack.isEmpty() && !StardustUtils.INSTANCE.hasEnchantment(StardustRegistry.INSTANCE.getSOULBOUND().get(), itemstack)) {
                    instance.player.drop(itemstack, true, false);
                    list.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

    // ENDURANCE
    @Redirect(method = "checkMovementStatistics", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
    private void checkMovementStatistics(Player instance, float pExhaustion) {
        if(StardustUtils.INSTANCE.hasEnchantment(StardustRegistry.INSTANCE.getENDURANCE().get(), instance.getItemBySlot(EquipmentSlot.LEGS))) {
            causeFoodExhaustion(pExhaustion/(1+StardustUtils.INSTANCE.getEnchantmentLevel(StardustRegistry.INSTANCE.getENDURANCE().get(), instance.getItemBySlot(EquipmentSlot.LEGS))));
        } else {
            causeFoodExhaustion(pExhaustion);
        }
    }
}
