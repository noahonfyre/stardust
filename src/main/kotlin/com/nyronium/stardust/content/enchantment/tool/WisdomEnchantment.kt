package com.nyronium.stardust.content.enchantment.tool

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.level.BlockEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class WisdomEnchantment : StardustEnchantment(
    EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).tradable())
    .category(EnchantmentCategory.DIGGER)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        FORGE_BUS.addListener(::onBlockBreak)
    }


    fun onBlockBreak(event: BlockEvent.BreakEvent) {
        val player = event.player
        if(!player.getItemBySlot(EquipmentSlot.MAINHAND).hasEnchantment(this)) return
        val wisdomLevel = player.getItemBySlot(EquipmentSlot.MAINHAND).getLevel(this)
        event.expToDrop *= 1+wisdomLevel/this.maxLevel
    }
}