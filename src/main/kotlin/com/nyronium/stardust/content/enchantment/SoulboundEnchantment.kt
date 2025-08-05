package com.nyronium.stardust.content.enchantment

import com.nyronium.stardust.core.StardustUtils
import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.event.entity.player.PlayerEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class SoulboundEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.VERY_RARE).default())
    .category(StardustUtils.PRIMARY_ITEMS)
    .applicableSlotsAll(EquipmentSlot.entries.toTypedArray())
) {
    init {
        FORGE_BUS.addListener(::onPlayerClone)
    }

    fun onPlayerClone(event: PlayerEvent.Clone) {
        if (!event.isWasDeath) return

        val oldPlayer = event.original
        val newPlayer = event.entity

        for (slot in 0 until oldPlayer.inventory.containerSize) {
            val stack = oldPlayer.inventory.getItem(slot)
            if (!stack.isEmpty && StardustUtils.hasEnchantment(this, stack)) {
                newPlayer.inventory.setItem(slot, stack.copy())
            }
        }
    }
}