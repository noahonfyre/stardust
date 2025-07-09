package com.nyronium.stardust

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment

object StardustUtils {
    fun hasEnchantment(enchantment: Enchantment, stack: ItemStack): Boolean {
        return getLevel(enchantment, stack) > 0
    }

    fun getLevel(enchantment: Enchantment, stack: ItemStack): Int {
        return stack.getEnchantmentLevel(enchantment)
    }
}