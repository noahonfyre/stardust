package com.nyronium.stardust.core

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment

object StardustUtils {
    infix fun ItemStack.hasEnchantment(enchantment: Enchantment): Boolean {
        return hasEnchantment(enchantment, this)
    }

    infix fun ItemStack.getLevel(enchantment: Enchantment): Int {
        return getLevel(enchantment, this)
    }

    fun hasEnchantment(enchantment: Enchantment, stack: ItemStack): Boolean {
        return getLevel(enchantment, stack) > 0
    }

    fun getLevel(enchantment: Enchantment, stack: ItemStack): Int {
        return stack.getEnchantmentLevel(enchantment)
    }
}