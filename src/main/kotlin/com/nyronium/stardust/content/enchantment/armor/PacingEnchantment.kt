package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory

class PacingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default().notDiscoverable())
    .category(EnchantmentCategory.ARMOR_FEET)
    .applicableSlots(EquipmentSlot.FEET)
) {
    init {
        registerAttributeModifier(Attributes.MOVEMENT_SPEED) { level -> 0.01*level }
    }
}