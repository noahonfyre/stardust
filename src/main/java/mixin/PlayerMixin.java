package mixin;

import com.nyronium.stardust.content.experience.ExperienceManager;
import com.nyronium.stardust.core.EnchantmentRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(Player.class)
public abstract class PlayerMixin {

    // SOULBOUND
    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V"))
    private void dropAll(Inventory instance) {
        List<NonNullList<ItemStack>> compartments = ((InventoryAccessor) instance).getCompartments();
        for(List<ItemStack> list : compartments) {
            for(int i = 0; i < list.size(); ++i) {
                ItemStack itemstack = list.get(i);
                if (!itemstack.isEmpty() && !StardustUtils.INSTANCE.hasEnchantment(EnchantmentRegistry.INSTANCE.getSOULBOUND().get(), itemstack)) {
                    instance.player.drop(itemstack, true, false);
                    list.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

    // ENDURANCE
    @Redirect(method = "checkMovementStatistics", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
    private void causeFoodExhaustion(Player instance, float pExhaustion) {
        Player self = (Player) (Object) this;
        if(StardustUtils.INSTANCE.hasEnchantment(EnchantmentRegistry.INSTANCE.getENDURANCE().get(), instance.getItemBySlot(EquipmentSlot.LEGS))) {
            float reducedExhaustion = pExhaustion / (1 + StardustUtils.INSTANCE.getLevel(EnchantmentRegistry.INSTANCE.getENDURANCE().get(), instance.getItemBySlot(EquipmentSlot.LEGS)));
            self.causeFoodExhaustion(reducedExhaustion);
        } else {
            self.causeFoodExhaustion(pExhaustion);
        }
    }

    @Redirect(method = "onEnchantmentPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperienceLevels(I)V"))
    private void giveExperienceLevels(Player instance, int levels) {
        ExperienceManager.INSTANCE.handleEnchantXp(instance, levels);
    }
}
