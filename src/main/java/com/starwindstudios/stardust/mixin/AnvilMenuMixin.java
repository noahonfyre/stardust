package com.starwindstudios.stardust.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = AnvilMenu.class, priority = 2000)
public class AnvilMenuMixin {
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40), require = 0)
    private int stardust$removeLimit(int original) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39), require = 0)
    private int stardust$maxLimit(int original) {
        // TODO: config set max level
        return Integer.MAX_VALUE - 1;
    }


}
