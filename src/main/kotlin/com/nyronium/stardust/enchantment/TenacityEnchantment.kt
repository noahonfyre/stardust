package com.nyronium.stardust.enchantment

import com.nyronium.stardust.misc.StardustUtils
import com.nyronium.stardust.enchantment.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.EnchantmentCategory

class TenacityEnchantment : StardustEnchantment(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.entries.toTypedArray()) {
    init {
        registerOnPlayerTick { event ->
            val player = event.player
            for(stack in player.inventory.items) {
                if(stack.tag == null) continue
                val compoundTag = stack.tag!!

                if(!StardustUtils.hasEnchantment(this, stack)) {
                    if(!compoundTag.getBoolean("TenacityApplied")) continue
                    compoundTag.putBoolean("TenacityApplied", false)
                    compoundTag.putBoolean("Unbreakable", false)
                    continue
                }
                if(compoundTag.getBoolean("TenacityApplied")) continue
                compoundTag.putBoolean("TenacityApplied", true)
                compoundTag.putBoolean("Unbreakable", true)
            }
        }
    }
}