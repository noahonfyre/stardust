package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class EnduranceEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(4)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default().loot("chests/village/village_cartographer", 0.75f))
    .category(EnchantmentCategory.ARMOR_LEGS)
    .applicableSlots(EquipmentSlot.LEGS)
)