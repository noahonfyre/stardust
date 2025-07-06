package com.nyronium.stardust

import com.nyronium.stardust.enchantment.LightningAspectEnchantment
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


@Mod(Stardust.ID)
object Stardust {
    const val ID = "stardust"
    val REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ID)

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Infusing a touch of stardust...")
    }

    val ARMOR_SLOTS: Array<EquipmentSlot> = arrayOf(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
    val HANDS: Array<EquipmentSlot> = arrayOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)

    val LIGHTNING_ASPECT = REGISTRY.register("lightning_aspect") { LightningAspectEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND) }
}