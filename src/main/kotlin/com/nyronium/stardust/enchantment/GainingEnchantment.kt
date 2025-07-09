package com.nyronium.stardust.enchantment

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class GainingEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, vararg pApplicableSlots: EquipmentSlot) : Enchantment(pRarity, pCategory, pApplicableSlots) {
    override fun getMaxLevel() = 3

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return
        if(pAttacker !is Player) return
        if(pTarget !is LivingEntity) return

        val experience = pTarget.experienceReward*(1+pLevel)
        pAttacker.giveExperiencePoints(experience.coerceAtLeast(1))
    }
}