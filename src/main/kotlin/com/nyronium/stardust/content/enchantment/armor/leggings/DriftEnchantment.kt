package com.nyronium.stardust.content.enchantment.armor.leggings

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraftforge.common.ForgeMod

class DriftEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON)
        .loot("chests/underwater_ruin_big", 0.9f)
        .loot("chests/shipwreck_treasure", 0.5f)
        .tradable()
    )
    .category(EnchantmentCategory.ARMOR_LEGS)
    .applicableSlots(EquipmentSlot.LEGS)
    .incompatible(Enchantments.SWIFT_SNEAK)
) {
    init {
        registerAttributeModifier(
            attribute = ForgeMod.SWIM_SPEED.get(),
            operation = AttributeModifier.Operation.MULTIPLY_BASE,
            condition = { it.isUnderWater && it.isSwimming }
        ) { level -> 0.25*level }
    }
}