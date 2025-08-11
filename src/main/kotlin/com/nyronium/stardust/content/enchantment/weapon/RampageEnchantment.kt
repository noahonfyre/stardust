package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.entity.living.LivingDeathEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class RampageEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).enchantingTable().loot("chests/woodland_mansion", 0.25f))
    .category(StardustCategories.WEAPON)
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

        if(!source.getItemBySlot(EquipmentSlot.MAINHAND).hasEnchantment(this)) return
        val rampageLevel = source.getItemBySlot(EquipmentSlot.MAINHAND).getLevel(this)
        source.heal((source.maxHealth/2)*rampageLevel/this.maxLevel)
    }
}