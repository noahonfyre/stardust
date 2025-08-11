package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory

class ResilienceEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.RARE).default())
    .category(EnchantmentCategory.ARMOR)
    .applicableSlots(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
) {
    init {
        registerAttributeModifier(Attributes.ARMOR_TOUGHNESS) { level -> 0.25*level }
    }
}