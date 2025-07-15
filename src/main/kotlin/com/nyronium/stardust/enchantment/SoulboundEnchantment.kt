package com.nyronium.stardust.enchantment

import com.nyronium.stardust.misc.StardustExtensions
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot

class SoulboundEnchantment : StardustEnchantment(Rarity.VERY_RARE, StardustExtensions.ALL, EquipmentSlot.entries.toTypedArray())