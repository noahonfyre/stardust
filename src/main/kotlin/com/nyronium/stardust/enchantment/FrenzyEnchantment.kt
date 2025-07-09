package com.nyronium.stardust.enchantment

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantment.Rarity
import net.minecraft.world.item.enchantment.EnchantmentCategory

class FrenzyEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, vararg pApplicableSlots: EquipmentSlot) : Enchantment(pRarity, pCategory, pApplicableSlots) {
    override fun getMaxLevel() = 3
}