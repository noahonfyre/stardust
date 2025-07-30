package com.nyronium.stardust.core

import com.nyronium.stardust.Stardust
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

object StardustUtils {
    var TOOLS: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":tools") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem
    }
    var EMPTY: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":empty") { false }
    var ALL: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":all") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem || it is ArmorItem
    }

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