package com.nyronium.stardust.enchantment

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import kotlin.math.exp

class WisdomEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, vararg pApplicableSlots: EquipmentSlot) : Enchantment(pRarity, pCategory, pApplicableSlots) {
    override fun getMaxLevel() = 5
}