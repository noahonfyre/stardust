package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.enchantment.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments

class DecayEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(2)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
    .incompatible(Enchantments.FIRE_ASPECT)
) {

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return
        if(pTarget !is Player) return

        val random = (0..100).random()
        if(random <= 2.5*pLevel) {
            pTarget.addEffect(MobEffectInstance(MobEffects.WITHER, 60, pLevel-1))
        }
    }
}