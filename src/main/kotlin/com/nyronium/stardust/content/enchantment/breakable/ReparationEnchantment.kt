package com.nyronium.stardust.content.enchantment.breakable

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.EnchantmentRegistry
import com.nyronium.stardust.core.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraftforge.event.TickEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class ReparationEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .obtaining(ObtainingConfiguration(Rarity.RARE).default())
    .category(EnchantmentCategory.BREAKABLE)
    .applicableSlotsAll(EquipmentSlot.entries.toTypedArray())
) {
    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther != EnchantmentRegistry.TENACITY.get()
    }

    init {
        FORGE_BUS.addListener(::onPlayerTick)
    }

    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        val player = event.player
        if(player.tickCount % 20 != 0) return
        val filtered = EquipmentSlot.entries.toTypedArray().filter {
            StardustUtils.hasEnchantment(this, player.getItemBySlot(it)) &&
                    StardustUtils.hasEnchantment(Enchantments.MENDING, player.getItemBySlot(it))
        }
        if(filtered.isEmpty()) return
        val first = filtered.firstOrNull { player.getItemBySlot(it).damageValue < player.getItemBySlot(it).maxDamage } ?: return
        player.getItemBySlot(first).damageValue -= 1
    }
}