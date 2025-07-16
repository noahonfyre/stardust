package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class ConsumptionEnchantment : StardustEnchantment(Rarity.COMMON, EnchantmentCategory.ARMOR_HEAD, arrayOf(EquipmentSlot.HEAD)) {
    override fun getMaxLevel() = 3
}