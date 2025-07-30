package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.UseAnim
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class ConsumptionEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE).discoverable())
    .category(EnchantmentCategory.ARMOR_HEAD)
    .applicableSlots(EquipmentSlot.HEAD)
) {
    init {
        FORGE_BUS.addListener(::onLivingStartUseItem)
    }

    fun onLivingStartUseItem(event: LivingEntityUseItemEvent.Start) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player

        if(!StardustUtils.hasEnchantment(this, player.getItemBySlot(EquipmentSlot.HEAD))) return
        val consumptionLevel = StardustUtils.getLevel(this, player.getItemBySlot(EquipmentSlot.HEAD))
        if(event.item.useAnimation != UseAnim.EAT && event.item.useAnimation != UseAnim.DRINK) return
        event.duration /= 1+consumptionLevel/this.maxLevel
    }
}