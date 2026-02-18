package com.nyronium.stardust.content.enchantment.armor.boots

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory

class PacingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).tradable())
    .category(EnchantmentCategory.ARMOR_FEET)
    .applicableSlots(EquipmentSlot.FEET)
) {
    init {
        registerAttributeModifier(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_BASE) { level -> 0.1*level }
    }
}