package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import com.nyronium.stardust.misc.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments

class ReparationEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.entries.toTypedArray()) {
    init {
        registerOnPlayerTick task@{ event ->
            val player = event.player
            if(player.tickCount % 20 != 0) return@task
            val filtered = EquipmentSlot.entries.toTypedArray().filter {
                StardustUtils.hasEnchantment(this, player.getItemBySlot(it)) &&
                        StardustUtils.hasEnchantment(Enchantments.MENDING, player.getItemBySlot(it))
            }
            if(filtered.isEmpty()) return@task
            val first = filtered.firstOrNull { player.getItemBySlot(it).damageValue < player.getItemBySlot(it).maxDamage } ?: return@task
            player.getItemBySlot(first).damageValue -= 1
        }
    }
}