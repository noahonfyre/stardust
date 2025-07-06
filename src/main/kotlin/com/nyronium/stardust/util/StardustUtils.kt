package com.nyronium.stardust.util

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper

object StardustUtils {
    fun hasEnchantment(enchantment: Enchantment, entity: LivingEntity): Boolean {
        return getLevel(enchantment, entity) > 0
    }

    fun getLevel(enchantment: Enchantment, entity: LivingEntity): Int {
        return EnchantmentHelper.getEnchantmentLevel(enchantment, entity)
    }
}