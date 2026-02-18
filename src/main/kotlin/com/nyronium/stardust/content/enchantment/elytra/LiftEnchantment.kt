package com.nyronium.stardust.content.enchantment.elytra

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraftforge.common.ForgeMod

class LiftEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.RARE)
        .loot("chests/end_city_treasure", 1/3f)
        .treasure()
        .tradable()
    )
    .category(StardustCategories.ELYTRA)
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