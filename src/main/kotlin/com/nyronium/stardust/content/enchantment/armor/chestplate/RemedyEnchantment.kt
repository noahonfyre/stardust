package com.nyronium.stardust.content.enchantment.armor.chestplate

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.player.PlayerXpEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class RemedyEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.RARE)
        .loot("chests/stronghold_corridor", 0.25f)
        .tradable()
    )
    .category(EnchantmentCategory.ARMOR_CHEST)
    .applicableSlots(EquipmentSlot.CHEST)
) {
    init {
        FORGE_BUS.addListener(::onPickupExperience)
    }

    fun onPickupExperience(event: PlayerXpEvent.PickupXp) {
        if(event.entity.level().isClientSide) return
        val player = event.entity

        if(!player.getItemBySlot(EquipmentSlot.CHEST).hasEnchantment(this)) return

        if(player.foodData.needsFood()) {
            player.foodData.eat(1, event.orb.value/10f)
        } else if(player.health < player.maxHealth) {
            player.heal(event.orb.value/10f)
        } else return

        event.orb.value = 0
    }
}