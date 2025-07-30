package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class CharismaEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default())
    .category(EnchantmentCategory.ARMOR_HEAD)
    .applicableSlots(EquipmentSlot.HEAD)
)