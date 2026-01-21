package mixin;

import com.nyronium.stardust.content.experience.ExperienceManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40), require = 0)
    private int removeLimit(int original) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39), require = 0)
    private int maxLimit(int original) {
        return Integer.MAX_VALUE - 1;
    }

    @Redirect(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperienceLevels(I)V"))
    private void giveExperienceLevels(Player player, int levels) {
        player.giveExperiencePoints(-ExperienceManager.INSTANCE.levelToTotalExperience(-levels));
    }
}
