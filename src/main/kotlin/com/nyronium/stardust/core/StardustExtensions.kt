package com.nyronium.stardust.core

import com.nyronium.stardust.Stardust
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.EnchantmentCategory

object StardustExtensions {
    var TOOLS: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":tools") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem
    }
    var EMPTY: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":empty") { false }
    var ALL: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":all") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem || it is ArmorItem
    }
}