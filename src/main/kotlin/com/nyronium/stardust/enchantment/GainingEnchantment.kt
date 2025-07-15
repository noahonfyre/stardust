package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory

class GainingEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun getMaxLevel() = 5

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return
        if(pAttacker !is Player) return
        if(pTarget !is LivingEntity) return
        pAttacker.giveExperiencePoints(pTarget.experienceReward*(pLevel/maxLevel))
    }
}