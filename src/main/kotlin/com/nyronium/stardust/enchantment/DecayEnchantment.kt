package com.nyronium.stardust.enchantment

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments

class DecayEnchantment : StardustEnchantment(Rarity.RARE, EnchantmentCategory.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun getMaxLevel() = 2

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return
        if(pTarget !is Player) return

        val random = (0..100).random()
        if(random <= 2.5*pLevel) {
            pTarget.addEffect(MobEffectInstance(MobEffects.WITHER, 60, pLevel-1))
        }
    }

    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther != Enchantments.FIRE_ASPECT && pOther != Stardust.FROSTBITE && pOther != Stardust.ELECTROCUTION
    }
}