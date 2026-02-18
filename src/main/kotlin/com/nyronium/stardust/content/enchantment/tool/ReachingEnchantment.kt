package com.nyronium.stardust.content.enchantment.tool

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.common.ForgeMod

class ReachingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.RARE).tradable())
    .category(StardustCategories.TOOL)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        registerAttributeModifier(ForgeMod.ENTITY_REACH.get()) { level -> 0.5*level }
        registerAttributeModifier(ForgeMod.BLOCK_REACH.get()) { level -> 0.5*level }
    }
}