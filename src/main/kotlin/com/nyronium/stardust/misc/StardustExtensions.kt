package com.nyronium.stardust.misc

import com.nyronium.stardust.Stardust
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.EnchantmentCategory

object StardustExtensions {
    var TOOLS: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":tools") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem
    }
    var ALL: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":all") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem || it is ArmorItem
    }
}