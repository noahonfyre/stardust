package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraftforge.common.ForgeMod

class DriftEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).discoverable().enchantingTable().loot("chests/underwater_ruin_big", 0.9f).loot("chests/shipwreck_treasure", 0.5f))
    .category(EnchantmentCategory.ARMOR_LEGS)
    .applicableSlots(EquipmentSlot.LEGS)
    .incompatible(Enchantments.SWIFT_SNEAK)
) {
    init {
        registerAttributeModifier(ForgeMod.SWIM_SPEED.get(), AttributeModifier.Operation.MULTIPLY_BASE) { level -> 0.5*level }
    }
}