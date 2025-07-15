package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class ReparationEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.entries.toTypedArray()) {
    init {
        registerOnPlayerTick { event ->
            val player = event.player

        }
    }
}