package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory

class ShelteringEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).default())
    .category(EnchantmentCategory.ARMOR)
    .applicableSlots(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
) {
    init {
        registerAttributeModifier(Attributes.ARMOR) { level -> 2.0 * level }
    }
}