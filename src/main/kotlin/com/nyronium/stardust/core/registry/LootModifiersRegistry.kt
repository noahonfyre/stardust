package com.nyronium.stardust.core.registry

import com.mojang.serialization.Codec
import com.nyronium.stardust.Stardust
import com.nyronium.stardust.content.loot.EnchantedBookLootModifier
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject


object LootModifiersRegistry {
    val REGISTRY: DeferredRegister<Codec<out IGlobalLootModifier>> = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Stardust.ID)

    val ADD_ENCHANTED_BOOK_MODIFIER: RegistryObject<Codec<out IGlobalLootModifier>> = REGISTRY.register("add_enchanted_book") { EnchantedBookLootModifier.CODEC }
}