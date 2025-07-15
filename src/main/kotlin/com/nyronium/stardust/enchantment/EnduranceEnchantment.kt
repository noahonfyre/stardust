package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class EnduranceEnchantment : StardustEnchantment(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_LEGS, arrayOf(EquipmentSlot.LEGS)) {
    override fun getMaxLevel() = 4
}