package com.nyronium.stardust.core.infrastructure

import com.nyronium.stardust.core.StardustUtils
import com.nyronium.stardust.datagen.StardustGlobalLootModifiersProvider
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.event.TickEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import java.util.*
import java.util.Locale.getDefault

open class StardustEnchantment(val config: EnchantmentConfiguration) : Enchantment(
    config.obtainingConfiguration.rarity,
    config.category,
    config.applicableSlots
) {
    init {
        if(config.obtainingConfiguration.lootModifiers.isNotEmpty()) {
            config.obtainingConfiguration.lootModifiers.forEach { (lootTable, modifierFunction) ->
                val lootModifier = modifierFunction(this)
                val modifierName = this::class.simpleName!!.removeSuffix("Enchantment").lowercase(getDefault())+"_from_"+lootTable.removePrefix("chests/")
                StardustGlobalLootModifiersProvider.modifiersToRegister[modifierName] = lootModifier
            }
        }
    }

    override fun isDiscoverable(): Boolean {
        return config.obtainingConfiguration.isDiscoverable
    }

    override fun isTradeable(): Boolean {
        return config.obtainingConfiguration.isTradable
    }

    override fun isTreasureOnly(): Boolean {
        return !config.obtainingConfiguration.isEnchantableViaEnchantingTable
    }

    override fun getMaxLevel(): Int {
        return config.maxLevel
    }

    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && !config.incompatibleEnchantments.contains(pOther)
    }

    fun registerAttributeModifier(
        attribute: Attribute,
        operation: Operation = Operation.ADDITION,
        valueOfLevel: (Int) -> Double
    ): UUID {
        val uuid = UUID.randomUUID()

        FORGE_BUS.addListener task@{ event: TickEvent.PlayerTickEvent ->
            val player = event.player
            val enchantment = this
            val attribute = player.getAttribute(attribute) ?: return@task

            for (slot in config.applicableSlots) {
                if (!StardustUtils.hasEnchantment(enchantment, player.getItemBySlot(slot))) {
                    if (attribute.getModifier(uuid) == null) return@task
                    attribute.removeModifier(uuid)
                    return@task
                }
                val enchantmentLevel = StardustUtils.getLevel(enchantment, player.getItemBySlot(slot))
                val hasModifier = attribute.getModifier(uuid) != null
                val isModifierAmountChanged = attribute.getModifier(uuid)?.amount != valueOfLevel(enchantmentLevel)

                if (hasModifier && isModifierAmountChanged) attribute.removeModifier(uuid)
                if (hasModifier) return@task

                val modifier = AttributeModifier(
                    uuid,
                    "Enchantment modifier",
                    valueOfLevel(enchantmentLevel),
                    operation
                )

                attribute.addTransientModifier(modifier)
            }
        }

        return uuid
    }
}