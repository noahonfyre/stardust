package com.nyronium.stardust.content.enchantment.armor.helmet

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.EnderManAngerEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class PeeringEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default().loot("chests/nether_bridge", 0.25f).loot("chests/stronghold_corridor", 0.33f))
    .category(EnchantmentCategory.ARMOR_HEAD)
    .applicableSlots(EquipmentSlot.HEAD)
) {
    init {
        FORGE_BUS.addListener(::onEnderManAnger)
    }

    fun onEnderManAnger(event: EnderManAngerEvent) {
        if(event.entity.level().isClientSide) return
        event.isCanceled = event.player.getItemBySlot(EquipmentSlot.HEAD).hasEnchantment(this)
    }
}