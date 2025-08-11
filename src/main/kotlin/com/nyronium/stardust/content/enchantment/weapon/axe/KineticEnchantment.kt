package com.nyronium.stardust.content.enchantment.weapon.axe

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.entity.living.LivingHurtEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import kotlin.math.roundToInt

class KineticEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default().loot("chests/end_city_treasure", 0.25f))
    .category(StardustCategories.AXE)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        FORGE_BUS.addListener(::onLivingHurt)
    }

    fun onLivingHurt(event: LivingHurtEvent) {
        if(event.entity.level().isClientSide) return
        if(event.source.entity == null) return
        if(event.source.entity !is Player) return
        val attacker = event.source.entity as Player

        if(!attacker.getItemBySlot(EquipmentSlot.MAINHAND).hasEnchantment(this)) return
        val kineticLevel = attacker.getItemBySlot(EquipmentSlot.MAINHAND).getEnchantmentLevel(this)

        val kineticEnergy = attacker.fallDistance.roundToInt()
        if(kineticEnergy <= 5) return

        event.amount *= 1+(kineticEnergy/15f)*(kineticLevel/maxLevel)
        attacker.resetFallDistance()
    }
}