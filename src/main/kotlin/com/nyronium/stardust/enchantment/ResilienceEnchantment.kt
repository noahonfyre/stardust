package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory
import java.util.*

class ResilienceEnchantment : StardustEnchantment(
    Rarity.VERY_RARE,
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
            Attributes.ARMOR_TOUGHNESS,
            UUID.fromString("e9641845-df27-442a-8e28-a37faa659b92"),
        ) { level -> 0.25*level }

        registerAttributeModifier(
            EquipmentSlot.CHEST,
            Attributes.ARMOR_TOUGHNESS,
            UUID.fromString("7683e4e5-31f5-406b-bbf2-6065e61519c9"),
        ) { level -> 0.25*level }

        registerAttributeModifier(
            EquipmentSlot.LEGS,
            Attributes.ARMOR_TOUGHNESS,
            UUID.fromString("d7432655-4e69-4809-b1ef-ee3503c556c9"),
        ) { level -> 0.25*level }

        registerAttributeModifier(
            EquipmentSlot.FEET,
            Attributes.ARMOR_TOUGHNESS,
            UUID.fromString("f18d0931-0d08-4546-a6fb-e9bb34615113"),
        ) { level -> 0.25*level }
    }

    override fun getMaxLevel() = 4
}