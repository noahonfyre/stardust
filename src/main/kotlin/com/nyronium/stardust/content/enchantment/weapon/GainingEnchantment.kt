package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory

class GainingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return
        if(pAttacker !is Player) return
        if(pTarget !is LivingEntity) return
        pAttacker.giveExperiencePoints(pTarget.experienceReward*(pLevel/maxLevel))
    }
}