package com.nyronium.stardust.content.infrastructure

import com.nyronium.stardust.core.EnchantmentHelper
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import com.nyronium.stardust.datagen.StardustGlobalLootModifiersProvider
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
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
                val modifierSimpleName = this::class.simpleName!!.removeSuffix("Enchantment").lowercase(getDefault())+"_from_"+lootTable.split("/")[lootTable.split("/").size - 1]
                StardustGlobalLootModifiersProvider.modifiersToRegister[modifierSimpleName] = lootModifier
            }
        }
    }

    override fun canApplyAtEnchantingTable(stack: ItemStack): Boolean {
        return super.canApplyAtEnchantingTable(stack) && EnchantmentHelper.isEnchantmentEnabled(this)
    }

    override fun isAllowedOnBooks(): Boolean {
        return super.isAllowedOnBooks() && EnchantmentHelper.isEnchantmentEnabled(this)
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
        return super.checkCompatibility(pOther) && !config.incompatibleEnchantments.contains(pOther) && EnchantmentHelper.isEnchantmentEnabled(this)
    }

    fun registerAttributeModifier(
        attribute: Attribute,
        operation: Operation = Operation.ADDITION,
        condition: (Player) -> Boolean = { true },
        value: (Int) -> Double
    ): Map<EquipmentSlot, UUID> {
        val modifierUUIDs = mutableMapOf<EquipmentSlot, UUID>()

        for(slot in config.applicableSlots) {
            val uuid = UUID.randomUUID()
            modifierUUIDs[slot] = uuid
        }

        FORGE_BUS.addListener task@{ event: TickEvent.PlayerTickEvent ->
            val player = event.player
            val enchantment = this
            val attribute = player.getAttribute(attribute) ?: return@task

            for ((slot, uuid) in modifierUUIDs) {
                if (!player.getItemBySlot(slot).hasEnchantment(enchantment) || !condition(player)) {
                    if (attribute.getModifier(uuid) == null) return@task
                    attribute.removeModifier(uuid)
                    return@task
                }
                val enchantmentLevel = player.getItemBySlot(slot).getLevel(enchantment)
                val hasModifier = attribute.getModifier(uuid) != null
                val isModifierAmountChanged = attribute.getModifier(uuid)?.amount != value(enchantmentLevel)

                if (hasModifier && isModifierAmountChanged) attribute.removeModifier(uuid)
                if (hasModifier) return@task

                val modifier = AttributeModifier(
                    uuid,
                    "Enchantment modifier",
                    value(enchantmentLevel),
                    operation
                )

                attribute.addTransientModifier(modifier)
            }
        }
        return modifierUUIDs
    }
}