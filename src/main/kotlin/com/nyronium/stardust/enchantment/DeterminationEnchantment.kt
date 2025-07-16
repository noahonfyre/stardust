package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments

class DeterminationEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_LEGS, arrayOf(EquipmentSlot.LEGS)) {
    override fun getMaxLevel() = 4

    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SWIFT_SNEAK
    }
}