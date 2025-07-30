package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory

class StabilityEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default())
    .category(EnchantmentCategory.ARMOR_LEGS)
    .applicableSlots(EquipmentSlot.LEGS)
) {
    init {
        registerAttributeModifier(Attributes.KNOCKBACK_RESISTANCE) { level -> 0.15*(level/this.maxLevel) }
    }
}