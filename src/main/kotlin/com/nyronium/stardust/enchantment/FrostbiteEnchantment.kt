package com.nyronium.stardust.enchantment

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class FrostbiteEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, vararg pApplicableSlots: EquipmentSlot) : Enchantment(pRarity, pCategory, pApplicableSlots) {
    override fun getMaxLevel() = 2

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return

        pTarget.ticksFrozen = 100*pLevel
    }
}