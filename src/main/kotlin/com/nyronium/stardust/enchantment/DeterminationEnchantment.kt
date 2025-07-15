package com.nyronium.stardust.enchantment

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.misc.StardustUtils
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class DeterminationEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_LEGS, arrayOf(EquipmentSlot.LEGS)) {
    init {
        registerOnPlayerTick { event ->
            val player = event.player
            if(player.isCrouching && StardustUtils.hasEnchantment(Stardust.DETERMINATION.get(), player.getItemBySlot(EquipmentSlot.LEGS))) {
                val determinationLevel = StardustUtils.getLevel(Stardust.DETERMINATION.get(), player.getItemBySlot(EquipmentSlot.LEGS))

                if(player.tickCount % 20 == 0) {
                    player.heal(0.5f*determinationLevel)
                }
            }
        }
    }

    override fun getMaxLevel() = 4
}