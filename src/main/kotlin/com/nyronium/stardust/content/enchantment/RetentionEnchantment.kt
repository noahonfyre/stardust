package com.nyronium.stardust.content.enchantment

import com.nyronium.stardust.core.StardustUtils
import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot

class RetentionEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.RARE).default())
    .category(StardustUtils.PRIMARY_ITEMS)
    .applicableSlotsAll(EquipmentSlot.entries.toTypedArray())
)