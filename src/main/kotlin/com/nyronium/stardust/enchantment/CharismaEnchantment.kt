package com.nyronium.stardust.enchantment

import com.nyronium.stardust.misc.StardustUtils
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class CharismaEnchantment : StardustEnchantment(Rarity.COMMON, EnchantmentCategory.ARMOR_HEAD, arrayOf(EquipmentSlot.HEAD)) {
   init {
       registerOnPlayerTick task@{ event ->
           val player = event.player

           if(!StardustUtils.hasEnchantment(this, player.getItemBySlot(EquipmentSlot.MAINHAND))) {

           }
       }
   }

    override fun getMaxLevel() = 3
}