package com.nyronium.stardust.content.enchantment.armor.leggings

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraftforge.event.entity.living.LivingHealEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class DeterminationEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.RARE).enchantingTable().discoverable().loot("chests/end_city_treasure", 0.05f))
    .category(EnchantmentCategory.ARMOR_LEGS)
    .applicableSlots(EquipmentSlot.LEGS)
    .incompatible(Enchantments.SWIFT_SNEAK)
) {
    init {
        FORGE_BUS.addListener(::onLivingHeal)
    }

    fun onLivingHeal(event: LivingHealEvent) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player

        if(!player.getItemBySlot(EquipmentSlot.LEGS).hasEnchantment(this)) return
        val determinationLevel = player.getItemBySlot(EquipmentSlot.LEGS).getLevel(this)

        if(!player.isCrouching) return

        event.amount *= 1+determinationLevel/this.maxLevel
    }
}