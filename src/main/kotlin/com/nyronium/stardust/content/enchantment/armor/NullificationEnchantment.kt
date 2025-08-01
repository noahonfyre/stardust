package com.nyronium.stardust.content.enchantment.armor

import com.nyronium.stardust.core.StardustUtils
import com.nyronium.stardust.core.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.core.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.core.infrastructure.StardustEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingHurtEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class NullificationEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE).default().notTradable().loot("chests/desert_pyramid", 0.25f))
    .category(EnchantmentCategory.ARMOR_CHEST)
    .applicableSlots(EquipmentSlot.CHEST)
) {

    init {
        FORGE_BUS.addListener(::onLivingHurt)
    }

    fun onLivingHurt(event: LivingHurtEvent) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player

        if(!StardustUtils.hasEnchantment(this, player.getItemBySlot(EquipmentSlot.CHEST))) return
        val nullificationLevel = StardustUtils.getLevel(this, player.getItemBySlot(EquipmentSlot.CHEST))

        val random = (0..100).random()
        if(random <= nullificationLevel) {
            event.isCanceled = true
        }
    }
}