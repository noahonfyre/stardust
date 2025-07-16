package com.nyronium.stardust.enchantment

import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.enchantment.EnchantmentCategory
import java.util.*

class FrenzyEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    init {
        registerAttributeModifier(
            EquipmentSlot.MAINHAND,
            Attributes.ATTACK_SPEED,
            UUID.fromString("c4f8833d-33a5-446b-b95d-9e21a240dfd6")
        ) { level -> 0.2*level }
    }

    override fun getMaxLevel() = 3
}