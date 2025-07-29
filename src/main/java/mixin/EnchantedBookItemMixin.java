package mixin;

import com.nyronium.stardust.core.StardustEnchantmentManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookItemMixin {
    @Shadow
    public static ListTag getEnchantments(ItemStack pEnchantedBookStack) {
        throw new UnsupportedOperationException("This code should be shadowed from EnchantedBookItem.");
    }

    @Unique
    public Map<Enchantment, Integer> stardust$getBookEnchantments(ItemStack stack) {
        ListTag listTag = getEnchantments(stack);
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        for(int i = 0; i < listTag.size(); i++) {
            CompoundTag compoundTag = listTag.getCompound(i);
            int level = EnchantmentHelper.getEnchantmentLevel(compoundTag);
            Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(EnchantmentHelper.getEnchantmentId(compoundTag));
            enchantments.put(enchantment, level);
        }
        return enchantments;
    }

    /**
     * @author NoahOnFyre
     * @reason Implementation of custom tooltips for enchanted books.
     */
    @Overwrite
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        EnchantedBookItem self = (EnchantedBookItem) (Object) this;
        Map<Enchantment, Integer> enchantments = stardust$getBookEnchantments(pStack);
        pTooltip.addAll(new StardustEnchantmentManager(self, pStack, enchantments).build());
    }
}
