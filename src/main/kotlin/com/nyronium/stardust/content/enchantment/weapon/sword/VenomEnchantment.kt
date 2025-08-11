package com.nyronium.stardust.content.enchantment.weapon.sword

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments

class VenomEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(2)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && (pOther is DecayEnchantment || pOther == Enchantments.FIRE_ASPECT)
    }

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return
        if(pTarget !is LivingEntity) return

        pTarget.addEffect(MobEffectInstance(MobEffects.WITHER, 80, pLevel-1))
    }
}