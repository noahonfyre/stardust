package com.nyronium.stardust.content.enchantment.armor.chestplate

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingHurtEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class NullificationEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.RARE).default().loot("chests/desert_pyramid", 0.25f))
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

        if(!player.getItemBySlot(EquipmentSlot.CHEST).hasEnchantment(this)) return
        val nullificationLevel = player.getItemBySlot(EquipmentSlot.CHEST).getLevel(this)

        val random = (0..100).random()
        if(random <= nullificationLevel) {
            event.isCanceled = true
        }
    }
}