package com.nyronium.stardust.enchantment

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory
import java.util.*

class StabilityEnchantment : StardustEnchantment(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_LEGS, arrayOf(EquipmentSlot.FEET)) {
    init {
        registerAttributeModifier(
            EquipmentSlot.LEGS,
            Attributes.KNOCKBACK_RESISTANCE,
            UUID.fromString("83774500-fc34-45b2-937a-44773e61ebaf"),
        ) { level -> 0.5*(level/Stardust.STABILITY.get().maxLevel) }
    }

    override fun getMaxLevel() = 4
}