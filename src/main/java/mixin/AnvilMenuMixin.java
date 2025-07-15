package mixin;

import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
    private int removeLimit(int original) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39))
    private int maxLimit(int original) {
        return Integer.MAX_VALUE - 1;
    }
}
