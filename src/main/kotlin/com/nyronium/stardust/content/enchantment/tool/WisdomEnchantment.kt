package com.nyronium.stardust.content.enchantment.tool

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.level.BlockEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class WisdomEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default())
    .category(EnchantmentCategory.DIGGER)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        FORGE_BUS.addListener(::onBlockBreak)
    }


    fun onBlockBreak(event: BlockEvent.BreakEvent) {
        val player = event.player
        if(!StardustUtils.hasEnchantment(this, player.getItemBySlot(EquipmentSlot.MAINHAND))) return
        val wisdomLevel = StardustUtils.getLevel(this, player.getItemBySlot(EquipmentSlot.MAINHAND))
        event.expToDrop *= 1+wisdomLevel/this.maxLevel
    }
}