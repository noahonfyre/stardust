package com.nyronium.stardust.core

import com.nyronium.stardust.core.config.ConfigHandler
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.registries.ForgeRegistries

object EnchantmentHelper {
    fun isEnchantmentEnabled(enchantment: Enchantment): Boolean {
        val id = ForgeRegistries.ENCHANTMENTS.getKey(enchantment) ?: return false
        return !ConfigHandler.disabledEnchantments.get().contains(id.toString())
    }
}