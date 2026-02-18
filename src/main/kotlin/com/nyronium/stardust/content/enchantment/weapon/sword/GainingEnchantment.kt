package com.nyronium.stardust.content.enchantment.weapon.sword

import com.nyronium.stardust.content.infrastructure.EnchantmentConfiguration
import com.nyronium.stardust.content.infrastructure.ObtainingConfiguration
import com.nyronium.stardust.content.infrastructure.StardustEnchantment
import com.nyronium.stardust.core.StardustUtils.getLevel
import com.nyronium.stardust.core.StardustUtils.hasEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

class GainingEnchantment : StardustEnchantment(EnchantmentConfiguration()
    .maxLevel(5)
    .obtaining(ObtainingConfiguration(Rarity.UNCOMMON).tradable())
    .category(EnchantmentCategory.WEAPON)
    .applicableSlots(EquipmentSlot.MAINHAND)
) {
    init {
        FORGE_BUS.addListener(::onLivingDropExperience)
    }

    fun onLivingDropExperience(event: LivingExperienceDropEvent) {
        val lastDamageSource = event.entity.lastDamageSource ?: return
        val entity = lastDamageSource.entity ?: return
        if(entity !is Player) return
        if(!entity.getItemBySlot(EquipmentSlot.MAINHAND).hasEnchantment(this)) return
        val gainingLevel = entity.getItemBySlot(EquipmentSlot.MAINHAND).getLevel(this)
        event.droppedExperience *= 1+(gainingLevel/maxLevel)
    }
}