package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory
import java.util.*

class ShelteringEnchantment : StardustEnchantment(
    Rarity.RARE,
    EnchantmentCategory.ARMOR,
    arrayOf(
        EquipmentSlot.HEAD,
        EquipmentSlot.CHEST,
        EquipmentSlot.LEGS,
        EquipmentSlot.FEET
    )
) {
    init {
        registerAttributeModifier(
            EquipmentSlot.HEAD,
            Attributes.ARMOR,
            UUID.fromString("f2a91c34-8e6d-4b7a-9f15-d5c8b7e3a912"),
        ) { level -> 2.0 * level }

        registerAttributeModifier(
            EquipmentSlot.CHEST,
            Attributes.ARMOR,
            UUID.fromString("d3b47e82-1c9a-4f63-b8e4-92a7d4c5f138"),
        ) { level -> 2.0 * level }

        registerAttributeModifier(
            EquipmentSlot.LEGS,
            Attributes.ARMOR,
            UUID.fromString("a8c52f96-3e74-4d1b-95f7-6c8d39b4e025"),
        ) { level -> 2.0 * level }

        registerAttributeModifier(
            EquipmentSlot.FEET,
            Attributes.ARMOR,
            UUID.fromString("b1f43d67-9c2a-4e8b-af6d-e4f92c7d1859"),
        ) { level -> 2.0*level }
    }

    override fun getMaxLevel() = 4
}