package com.nyronium.stardust.content.enchantment.tool.pickaxe

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustCategories
import net.minecraft.world.entity.EquipmentSlot

class DemolitionEnchantment : StardustEnchantment(
    EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.RARE).default())
    .category(StardustCategories.PICKAXE)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {

}