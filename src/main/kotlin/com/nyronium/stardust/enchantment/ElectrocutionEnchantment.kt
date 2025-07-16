package com.nyronium.stardust.enchantment

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.*
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments

class ElectrocutionEnchantment : StardustEnchantment(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun getMaxLevel() = 2

    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if(pAttacker.level().isClientSide) return

        val random = (0..100).random()
        if(random <= 2.5*pLevel) {
            EntityType.LIGHTNING_BOLT.spawn(
                pAttacker.level() as ServerLevel,
                null,
                pAttacker as Player,
                pTarget.blockPosition(),
                MobSpawnType.TRIGGERED,
                false,
                false
            )
        }
    }

    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther != Enchantments.FIRE_ASPECT && pOther != Stardust.FROSTBITE && pOther != Stardust.DECAY
    }
}