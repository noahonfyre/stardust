package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.EnchantmentCategory

class FrostbiteEnchantment : StardustEnchantment(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun getMaxLevel() = 2

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return

        val random = (0..100).random()
        if(random <= 5*pLevel) {
            pTarget.ticksFrozen = 200
        }
    }
}