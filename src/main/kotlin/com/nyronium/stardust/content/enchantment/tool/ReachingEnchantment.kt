package com.nyronium.stardust.content.enchantment.tool

import com.nyronium.stardust.content.enchantment.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.enchantment.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustExtensions
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.common.ForgeMod

class ReachingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default())
    .category(StardustExtensions.TOOLS)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        registerAttributeModifier(ForgeMod.ENTITY_REACH.get()) { level -> 1.0*level }
        registerAttributeModifier(ForgeMod.BLOCK_REACH.get()) { level -> 1.0*level }
    }
}