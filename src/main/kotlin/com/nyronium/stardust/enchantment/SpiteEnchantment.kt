package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class SpiteEnchantment : StardustEnchantment(Rarity.COMMON, EnchantmentCategory.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun getMaxLevel() = 3
}