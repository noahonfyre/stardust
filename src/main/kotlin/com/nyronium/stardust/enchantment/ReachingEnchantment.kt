package com.nyronium.stardust.enchantment

import com.nyronium.stardust.misc.StardustExtensions
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraftforge.common.ForgeMod
import java.util.*

class ReachingEnchantment : StardustEnchantment(Rarity.UNCOMMON, StardustExtensions.TOOLS, arrayOf(EquipmentSlot.MAINHAND)) {
    init {
        registerAttributeModifier(
            EquipmentSlot.MAINHAND,
            ForgeMod.ENTITY_REACH.get(),
            UUID.fromString("0acfdbee-af25-42bb-bfa0-5d9800ad2451"),
        ) { level -> 1.0*level }

        registerAttributeModifier(
            EquipmentSlot.MAINHAND,
            ForgeMod.BLOCK_REACH.get(),
            UUID.fromString("b31a85d1-653b-4557-9876-a2f158a0e1aa"),
        ) { level -> 1.0*level }
    }

    override fun getMaxLevel() = 3
}