package com.nyronium.stardust.enchantment.infrastructure

import com.nyronium.stardust.misc.StardustUtils
import com.nyronium.stardust.event.EventHandler.tickTasks
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.TickEvent
import java.util.*

open class StardustEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, pApplicableSlots: Array<EquipmentSlot>) : Enchantment(pRarity, pCategory, pApplicableSlots) {

    fun registerOnPlayerTick(task: (TickEvent.PlayerTickEvent) -> Unit) {
        tickTasks.add(task)
    }

    fun registerAttributeModifier(
        slot: EquipmentSlot,
        attribute: Attribute,
        uuid: UUID,
        operation: AttributeModifier.Operation = AttributeModifier.Operation.ADDITION,
        valueOfLevel: (Int) -> Double
    ) {
        tickTasks.add task@{ event ->
            val player = event.player
            val enchantment = this@StardustEnchantment
            val attribute = player.getAttribute(attribute) ?: return@task

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

            attribute.addTransientModifier(
                AttributeModifier(
                    uuid,
                    "Enchantment modifier",
                    valueOfLevel(enchantmentLevel),
                    operation
                )
            )
        }
    }
}