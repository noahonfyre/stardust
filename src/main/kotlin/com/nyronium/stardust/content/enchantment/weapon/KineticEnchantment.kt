package com.nyronium.stardust.content.enchantment.weapon

import com.nyronium.stardust.content.enchantment.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingHurtEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import kotlin.math.roundToInt

class KineticEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE))
    .category(EnchantmentCategory.WEAPON)
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

        event.amount *= 1+(kineticEnergy/15).coerceAtMost(1)*(kineticLevel/maxLevel)
        attacker.resetFallDistance()

        attacker.sendSystemMessage(Component.literal(((kineticEnergy/20).coerceAtMost(1)*(kineticLevel/maxLevel)).toString()))
    }
}