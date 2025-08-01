package com.nyronium.stardust.content.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries


class EnchantedBookLootModifier(
    conditions: Array<LootItemCondition>,
    private val item: Item,
    private val enchantment: Enchantment,
    private val minLevel: Int,
    private val maxLevel: Int
) : LootModifier(conditions) {
    companion object {
        val CODEC: Codec<EnchantedBookLootModifier> = RecordCodecBuilder.create { instance ->
            codecStart(instance)
                .and(ForgeRegistries.ITEMS.codec.fieldOf("item").forGetter { it.item })
                .and(ForgeRegistries.ENCHANTMENTS.codec.fieldOf("enchantment").forGetter { it.enchantment })
                .and(Codec.INT.fieldOf("minLevel").forGetter { it.minLevel })
                .and(Codec.INT.fieldOf("maxLevel").forGetter { it.maxLevel })
                .apply(instance, ::EnchantedBookLootModifier)
        }
    }

    override fun doApply(generatedLoot: ObjectArrayList<ItemStack>, context: LootContext): ObjectArrayList<ItemStack> {
        for(condition in conditions) {
            if(!condition.test(context)) return generatedLoot
        }

        val enchantedBook = ItemStack(item)
        EnchantedBookItem.addEnchantment(enchantedBook, EnchantmentInstance(enchantment, (minLevel..maxLevel).random()))
        generatedLoot.add(enchantedBook)

        return generatedLoot
    }

    override fun codec(): Codec<out IGlobalLootModifier> = CODEC
}