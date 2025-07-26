package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.enchantment.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingDeathEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class RampageEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        FORGE_BUS.addListener(::onLivingDeath)
    }

    fun onLivingDeath(event: LivingDeathEvent) {
        if(event.entity.level().isClientSide) return
        if(event.source.entity == null) return
        if(event.source.entity !is Player) return
        val source = event.source.entity!! as Player

        if(!StardustUtils.hasEnchantment(this, source.getItemBySlot(EquipmentSlot.MAINHAND))) return
        val rampageLevel = StardustUtils.getEnchantmentLevel(this, source.getItemBySlot(EquipmentSlot.MAINHAND))
        source.heal((source.maxHealth/2)*rampageLevel/this.maxLevel)
    }
}