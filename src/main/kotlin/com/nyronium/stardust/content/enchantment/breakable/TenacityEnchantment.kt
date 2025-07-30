package com.nyronium.stardust.content.enchantment.breakable

import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.EnchantmentRegistry
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraftforge.event.TickEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class TenacityEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.VERY_RARE).default())
    .category(EnchantmentCategory.BREAKABLE)
    .applicableSlotsAll(EquipmentSlot.entries.toTypedArray())
) {
    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && !listOf(Enchantments.MENDING, Enchantments.UNBREAKING, EnchantmentRegistry.REPARATION.get()).contains(pOther)
    }

    init {
        FORGE_BUS.addListener(::onPlayerTick)
    }

    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
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