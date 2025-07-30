package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.player.CriticalHitEvent
import net.minecraftforge.eventbus.api.Event
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class StrikingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {

    init {
        FORGE_BUS.addListener(::onCriticalHit)
    }

    fun onCriticalHit(event: CriticalHitEvent) {
        val player = event.entity
        if(player.level().isClientSide) return
        if(!event.isVanillaCritical) return
        if(StardustUtils.hasEnchantment(this, player.getItemBySlot(EquipmentSlot.MAINHAND))) {
            val strikingLevel = StardustUtils.getLevel(this, player.getItemBySlot(EquipmentSlot.MAINHAND))
            event.damageModifier = 1f+strikingLevel/this.maxLevel
            event.result = Event.Result.ALLOW
            return
        }
        event.result = Event.Result.DEFAULT
    }
}