package com.nyronium.stardust.content.infrastructure

import net.minecraft.world.item.enchantment.Enchantment.Rarity

class ObtainingConfiguration(val rarity: Rarity) {
    var isTradable: Boolean = false
    var isDiscoverable: Boolean = false
    var isEnchantableViaEnchantingTable: Boolean = true

    fun default(): ObtainingConfiguration {
        isTradable = true
        isDiscoverable = true
        isEnchantableViaEnchantingTable = true
        return this
    }

    fun tradable(): ObtainingConfiguration {
        isTradable = true
        return this
    }

    fun discoverable(): ObtainingConfiguration {
        isDiscoverable = true
        return this
    }

    fun enchantingTable(): ObtainingConfiguration {
        isEnchantableViaEnchantingTable = true
        return this
    }

    fun notTradable(): ObtainingConfiguration {
        isTradable = false
        return this
    }

    fun notDiscoverable(): ObtainingConfiguration {
        isDiscoverable = false
        return this
    }

    fun noEnchantingTable(): ObtainingConfiguration {
        isEnchantableViaEnchantingTable = false
        return this
    }
}