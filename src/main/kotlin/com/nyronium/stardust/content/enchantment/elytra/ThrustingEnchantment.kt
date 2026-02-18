package com.nyronium.stardust.content.enchantment.elytra

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.event.TickEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class ThrustingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).tradable())
    .category(StardustCategories.ELYTRA)
    .applicableSlots(EquipmentSlot.CHEST)
) {
    init {
        FORGE_BUS.addListener(::onPlayerTick)
    }

    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if(!event.player.isFallFlying) return
        if(!event.player.getItemBySlot(EquipmentSlot.CHEST).hasEnchantment(this)) return
        val thrustingLevel = event.player.getItemBySlot(EquipmentSlot.CHEST).getLevel(this)

        if(event.player.deltaMovement.y >= -0.15) return

        event.player.addDeltaMovement(event.player.lookAngle.scale(0.0025*thrustingLevel))
    }
}