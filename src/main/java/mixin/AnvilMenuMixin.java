package mixin;

import com.nyronium.stardust.content.experience.ExperienceManager;
import com.nyronium.stardust.core.config.ConfigHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = AnvilMenu.class, priority = 2000)
public class AnvilMenuMixin {
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40), require = 0)
    private int removeLimit(int original) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39), require = 0)
    private int maxLimit(int original) {
        int anvilCap = ConfigHandler.INSTANCE.getAnvilCap().get();
        if(anvilCap == 0) {
            return Integer.MAX_VALUE - 1;
        } else {
            return anvilCap;
        }
    }

    @Redirect(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getBaseRepairCost()I"))
    private int createResult(ItemStack instance) {
        if(ConfigHandler.INSTANCE.getDisablePriorWorkPenalty().get()) {
            return 0;
        } else {
            return instance.getBaseRepairCost();
        }
    }

    @Redirect(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperienceLevels(I)V"))
    private void giveExperienceLevels(Player player, int levels) {
        player.giveExperiencePoints(-ExperienceManager.INSTANCE.levelToTotalExperience(-levels));
    }
}
