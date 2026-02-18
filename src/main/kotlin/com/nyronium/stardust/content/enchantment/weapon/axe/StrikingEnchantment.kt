package com.nyronium.stardust.content.enchantment.weapon.axe

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.event.entity.player.CriticalHitEvent
import net.minecraftforge.eventbus.api.Event
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class StrikingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE)
        .loot("chests/pillager_outpost", 0.5f)
        .tradable()
    )
    .category(StardustCategories.AXE)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {

    init {
        FORGE_BUS.addListener(::onCriticalHit)
    }

    fun onCriticalHit(event: CriticalHitEvent) {
        val player = event.entity
        if(player.level().isClientSide) return
        if(!event.isVanillaCritical) return
        if(player.getItemBySlot(EquipmentSlot.MAINHAND).hasEnchantment(this)) {
            val strikingLevel = player.getItemBySlot(EquipmentSlot.MAINHAND).getLevel(this)
            event.damageModifier = 1f+strikingLevel/this.maxLevel
            event.result = Event.Result.ALLOW
            return
        }
        event.result = Event.Result.DEFAULT
    }
}