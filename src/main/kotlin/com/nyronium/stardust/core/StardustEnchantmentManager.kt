package com.nyronium.stardust.core

import net.minecraft.ChatFormatting
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment

class StardustEnchantmentManager(val instance: EnchantedBookItem, val stack: ItemStack, val enchantments: Map<Enchantment, Int>) {
    fun build(): List<Component> {
        val mainViewComponents: MutableList<Component> = mutableListOf()

        for((enchantment, level) in enchantments) {
            mainViewComponents.add(mainPart(enchantment, level))
            mainViewComponents.add(descriptionPart(enchantment))

            if(!Screen.hasShiftDown()) continue
            mainViewComponents.add(Component.literal(enchantment.category.toString()))
        }

        mainViewComponents.add(
            Component.literal("[").withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.translatable("tooltip.stardust.extend").withStyle(if(Screen.hasShiftDown()) ChatFormatting.WHITE else ChatFormatting.GRAY))
                .append(Component.literal("]").withStyle(ChatFormatting.DARK_GRAY))
        )
        return mainViewComponents
    }

    fun mainPart(enchantment: Enchantment, level: Int): Component {
        val color = if(enchantment.isCurse) ChatFormatting.RED else when(enchantment.rarity) {
            Enchantment.Rarity.COMMON -> ChatFormatting.GRAY
            Enchantment.Rarity.UNCOMMON -> ChatFormatting.YELLOW
            Enchantment.Rarity.RARE -> ChatFormatting.AQUA
            Enchantment.Rarity.VERY_RARE -> ChatFormatting.LIGHT_PURPLE
        }
        return Component.translatable(enchantment.descriptionId).withStyle(color)
            .append(levelIndicator(enchantment, level))
    }

    fun descriptionPart(enchantment: Enchantment): Component {
        return Component.translatable(enchantment.descriptionId + ".desc").withStyle(ChatFormatting.DARK_GRAY)
    }

    fun levelIndicator(enchantment: Enchantment, level: Int): Component {
        val isMaxLevel = enchantment.maxLevel == level
        val isOverenchanted = level > enchantment.maxLevel
        val component = when {
            isMaxLevel -> {
                if(level == 1) Component.empty() else Component.literal(roman(level)).withStyle(ChatFormatting.GREEN)
            }
            isOverenchanted -> {
                val overenchantingDiff = "+".repeat(level-enchantment.maxLevel)
                Component.literal(roman(level)).withStyle(ChatFormatting.AQUA)
                    .append(" ")
                    .append(Component.literal("(").withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.literal(overenchantingDiff).withStyle(ChatFormatting.AQUA))
                    .append(Component.literal(")").withStyle(ChatFormatting.DARK_GRAY))
            }
            else -> {
                Component.literal(roman(level)).withStyle(ChatFormatting.YELLOW)
                    .append(Component.literal("/").withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.literal(roman(enchantment.maxLevel)).withStyle(ChatFormatting.GREEN))
            }
        }
        return if(component == Component.empty()) component else Component.literal(" ").append(component)
    }

    private fun roman(value: Int): String {
        if(value !in 1..255) return ""

        val romanMap = listOf(
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
        )

        var remaining = value
        val result = StringBuilder()

        for ((weight, symbol) in romanMap) {
            while (remaining >= weight) {
                result.append(symbol)
                remaining -= weight
            }
        }

        return result.toString()
    }
}