package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.EnderManAngerEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class PeeringEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.COMMON).default().notTradable())
    .category(EnchantmentCategory.ARMOR_HEAD)
    .applicableSlots(EquipmentSlot.HEAD)
) {
    init {
        FORGE_BUS.addListener(::onEnderManAnger)
    }

    fun onEnderManAnger(event: EnderManAngerEvent) {
        if(event.entity.level().isClientSide) return
        event.isCanceled = StardustUtils.hasEnchantment(this, event.player.getItemBySlot(EquipmentSlot.HEAD))
    }
}