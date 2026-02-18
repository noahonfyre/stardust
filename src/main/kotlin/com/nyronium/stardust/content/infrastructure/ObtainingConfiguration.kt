package com.nyronium.stardust.content.infrastructure

import com.nyronium.stardust.content.loot.EnchantedBookLootModifier
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantment.Rarity
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraftforge.common.loot.LootTableIdCondition

class ObtainingConfiguration(val rarity: Rarity) {
    var lootModifiers: MutableMap<String, (Enchantment) -> EnchantedBookLootModifier> = mutableMapOf()
    var isTradable: Boolean = true
    var isDiscoverable: Boolean = true
    var isEnchantableViaEnchantingTable: Boolean = true

    fun loot(lootTable: String, chance: Float, minLevel: Int? = null, maxLevel: Int? = null): ObtainingConfiguration {
        lootModifiers[lootTable] = {
            EnchantedBookLootModifier(
                arrayOf(
                    LootTableIdCondition.builder(ResourceLocation(lootTable)).build(),
                    LootItemRandomChanceCondition.randomChance(chance).build()
                ),
                Items.ENCHANTED_BOOK, it, minLevel ?: it.minLevel, maxLevel ?: it.maxLevel
            )
        }
        return this
    }

    fun tradable(): ObtainingConfiguration {
        isTradable = true
        return this
    }

    fun treasure(): ObtainingConfiguration {
        isEnchantableViaEnchantingTable = false
        isDiscoverable = false
        return this
    }
}