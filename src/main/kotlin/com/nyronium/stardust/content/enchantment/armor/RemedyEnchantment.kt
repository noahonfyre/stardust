package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.StardustUtils
import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.player.PlayerXpEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class RemedyEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE).loot("chests/stronghold_corridor", 0.25f))
    .category(EnchantmentCategory.ARMOR_CHEST)
    .applicableSlots(EquipmentSlot.CHEST)
) {
    init {
        FORGE_BUS.addListener(::onPickupExperience)
    }

    fun onPickupExperience(event: PlayerXpEvent.PickupXp) {
        if(event.entity.level().isClientSide) return
        val player = event.entity

        if(!StardustUtils.hasEnchantment(this, player.getItemBySlot(EquipmentSlot.CHEST))) return

        if(player.foodData.foodLevel < 20) {
            player.foodData.eat(1, event.orb.value/10f)
        } else if(player.health < player.maxHealth) {
            player.heal(event.orb.value/10f)
        } else return

        event.orb.value = 0
    }
}