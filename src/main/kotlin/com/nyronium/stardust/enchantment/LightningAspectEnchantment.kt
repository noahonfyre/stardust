package com.nyronium.stardust.enchantment

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class LightningAspectEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, vararg pApplicableSlots: EquipmentSlot) : Enchantment(pRarity, pCategory, pApplicableSlots) {
    override fun getMaxLevel() = 2

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return

        repeat(pLevel) {
            EntityType.LIGHTNING_BOLT.spawn(pAttacker.level() as ServerLevel, null, pTarget as Player, pTarget.blockPosition(), MobSpawnType.TRIGGERED, false, false)
        }
    }
}