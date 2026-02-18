package com.nyronium.stardust.content.enchantment

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.event.entity.player.PlayerEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class SoulboundEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.VERY_RARE)
        .loot("chests/end_city_treasure", 1/3f)
        .loot("chests/stronghold_corridor", 0.15f)
        .treasure()
        .tradable()
    )
    .category(StardustCategories.PRIMARY_ITEMS)
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
            if (!stack.isEmpty && stack.hasEnchantment(this)) {
                newPlayer.inventory.setItem(slot, stack.copy())
            }
        }
    }
}