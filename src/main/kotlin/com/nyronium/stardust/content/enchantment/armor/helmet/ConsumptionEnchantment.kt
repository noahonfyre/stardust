package com.nyronium.stardust.content.enchantment.armor.helmet

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.UseAnim
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class ConsumptionEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(3)
    .obtaining(ObtainingConfiguration(Rarity.RARE).tradable())
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

        if(!player.getItemBySlot(EquipmentSlot.HEAD).hasEnchantment(this)) return
        val consumptionLevel = player.getItemBySlot(EquipmentSlot.HEAD).getLevel(this)
        if(event.item.useAnimation != UseAnim.EAT && event.item.useAnimation != UseAnim.DRINK) return
        event.duration /= 1+consumptionLevel/this.maxLevel
    }
}