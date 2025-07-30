package com.nyronium.stardust.content.infrastructure

import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantment.Rarity
import net.minecraft.world.item.enchantment.EnchantmentCategory

class EnchantmentConfiguration {
    var maxLevel: Int = 1
    var category: EnchantmentCategory = StardustUtils.EMPTY
    var applicableSlots: Array<EquipmentSlot> = arrayOf()
    var obtainingConfiguration: ObtainingConfiguration = ObtainingConfiguration(Rarity.COMMON).default()
    var incompatibleEnchantments: MutableList<Enchantment> = mutableListOf()

    fun maxLevel(maxLevel: Int): EnchantmentConfiguration {
        this.maxLevel = maxLevel
        return this
    }

    fun category(category: EnchantmentCategory): EnchantmentConfiguration {
        this.category = category
        return this
    }

    fun applicableSlots(vararg slots: EquipmentSlot): EnchantmentConfiguration {
        this.applicableSlots = slots.toList().toTypedArray()
        return this
    }

    fun applicableSlotsAll(slots: Array<EquipmentSlot>): EnchantmentConfiguration {
        this.applicableSlots = slots
        return this
    }

    fun incompatible(vararg enchantments: Enchantment): EnchantmentConfiguration {
        this.incompatibleEnchantments.addAll(enchantments)
        return this
    }

    fun obtaining(obtainingConfiguration: ObtainingConfiguration): EnchantmentConfiguration {
        this.obtainingConfiguration = obtainingConfiguration
        return this
    }
}