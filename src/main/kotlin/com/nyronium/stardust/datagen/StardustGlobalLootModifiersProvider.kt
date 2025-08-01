package com.nyronium.stardust.datagen

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.content.loot.EnchantedBookLootModifier
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.GlobalLootModifierProvider

class StardustGlobalLootModifiersProvider(output: PackOutput) : GlobalLootModifierProvider(output, Stardust.ID) {
    companion object {
        val modifiersToRegister: MutableMap<String, EnchantedBookLootModifier> = mutableMapOf()
    }

    override fun start() {
        for ((key, modifier) in modifiersToRegister) {
            add(key, modifier)
        }
    }
}