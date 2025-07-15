package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory
import java.util.*

class PacingEnchantment : StardustEnchantment(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, arrayOf(EquipmentSlot.FEET)) {
    init {
        registerAttributeModifier(
            EquipmentSlot.FEET,
            Attributes.MOVEMENT_SPEED,
            UUID.fromString("d1a3f204-7ad8-4f5c-a4f2-67d2e8d75958"),
        ) { level -> 0.01*level }
    }

    override fun getMaxLevel() = 3
}