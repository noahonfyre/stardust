package com.nyronium.stardust

import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.enchantment.EnchantmentCategory

object StardustExtensions {
    var TOOLS: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":tools") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem
    }
    var SWORD: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":sword") {
        it is SwordItem
    }
}