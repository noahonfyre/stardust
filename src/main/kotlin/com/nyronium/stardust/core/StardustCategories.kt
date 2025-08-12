package com.nyronium.stardust.core

import com.nyronium.stardust.Stardust
import net.minecraft.world.item.*
import net.minecraft.world.item.enchantment.EnchantmentCategory

object StardustCategories {
    val WEAPON: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":weapon") {
        it is SwordItem || it is AxeItem
    }
    val TOOL: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":tool") {
        it is SwordItem || it is PickaxeItem || it is AxeItem || it is ShovelItem || it is HoeItem
    }
    val AXE: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":axe") {
        it is AxeItem
    }
    val PICKAXE: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":pickaxe") {
        it is PickaxeItem
    }
    val ELYTRA: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":elytra") {
        it is ElytraItem
    }
    val NONE: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":none") {
        false
    }
    val PRIMARY_ITEMS: EnchantmentCategory = EnchantmentCategory.create(Stardust.ID+":primary_items") {
        listOf(
            SwordItem::class,
            PickaxeItem::class,
            AxeItem::class,
            ShovelItem::class,
            HoeItem::class,
            ArmorItem::class,
            ElytraItem::class,
            TridentItem::class,
            ShieldItem::class,
            BowItem::class,
            CrossbowItem::class
        ).any { itemClass -> itemClass.isInstance(it) }
    }
}