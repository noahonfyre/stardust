package com.nyronium.stardust.content.enchantment.elytra

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.common.ForgeMod

class LiftEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.RARE).default())
    .category(EnchantmentCategory.WEARABLE)
    .applicableSlots(EquipmentSlot.CHEST)
) {
    init {
        registerAttributeModifier(
            attribute = ForgeMod.ENTITY_GRAVITY.get(),
            operation = AttributeModifier.Operation.MULTIPLY_TOTAL,
            condition = { it.isFallFlying }
        ) { -0.5 }
    }
}