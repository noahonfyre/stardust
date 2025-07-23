package com.nyronium.stardust.core

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment

object StardustUtils {
    infix fun ItemStack.hasEnchantment(enchantment: Enchantment): Boolean {
        return getEnchantmentLevel(enchantment, this) > 0
    }

    infix fun ItemStack.getEnchantmentLevel(enchantment: Enchantment): Int {
        return getEnchantmentLevel(enchantment, this)
    }

    fun hasEnchantment(enchantment: Enchantment, stack: ItemStack): Boolean {
        return getEnchantmentLevel(enchantment, stack) > 0
    }

    fun getEnchantmentLevel(enchantment: Enchantment, stack: ItemStack): Int {
        return stack.getEnchantmentLevel(enchantment)
    }
}