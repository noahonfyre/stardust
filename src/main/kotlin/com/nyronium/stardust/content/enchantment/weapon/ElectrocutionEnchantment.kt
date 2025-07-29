package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.enchantment.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.StardustEnchantment
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.*
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class ElectrocutionEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(2)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther !is DecayEnchantment && pOther !is FrostbiteEnchantment
    }

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
}