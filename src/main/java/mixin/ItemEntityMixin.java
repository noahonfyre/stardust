package mixin;

import com.nyronium.stardust.core.registry.EnchantmentRegistry;
import com.nyronium.stardust.core.StardustUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.UUID;

@Mixin(value = ItemEntity.class, priority = 2000)
public class ItemEntityMixin {
    // TENACITY
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void hurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity self = (ItemEntity) (Object) this;
        if(StardustUtils.INSTANCE.hasEnchantment(EnchantmentRegistry.INSTANCE.getTENACITY().get(), self.getItem())) {
            cir.setReturnValue(false);
        }
    }

    // TENACITY
    @Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
    private void tick(CallbackInfo ci) {
        ItemEntity self = (ItemEntity) (Object) this;
        if (self.getY() < self.level().getMinBuildHeight()-64.0) {
            Level level = self.level();
            ItemStack stack = self.getItem().copy();
            if (!level.isClientSide && StardustUtils.INSTANCE.hasEnchantment(EnchantmentRegistry.INSTANCE.getTENACITY().get(), stack)) {
                ItemEntityAccessor accessor = (ItemEntityAccessor) this;
                UUID throwerUUID = accessor.getThrower();
                if (throwerUUID != null) {
                    ServerPlayer thrower = Objects.requireNonNull(self.level().getServer()).getPlayerList().getPlayer(throwerUUID);
                    assert thrower != null;
                    thrower.getInventory().add(stack);
                    self.discard();
                    ci.cancel();
                }
            }
        }
    }
}
