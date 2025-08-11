package com.nyronium.stardust.content.enchantment.weapon.sword

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory

class FrenzyEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.VERY_RARE).default())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        registerAttributeModifier(Attributes.ATTACK_SPEED) { level -> 0.2*level }
    }
}