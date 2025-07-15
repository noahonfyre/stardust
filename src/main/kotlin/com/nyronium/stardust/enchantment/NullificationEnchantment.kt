package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class NullificationEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, arrayOf(EquipmentSlot.CHEST)) {
    override fun getMaxLevel() = 5
}