package com.nyronium.stardust

import com.nyronium.stardust.enchantment.DecayEnchantment
import com.nyronium.stardust.enchantment.DeterminationEnchantment
import com.nyronium.stardust.enchantment.FrostbiteEnchantment
import com.nyronium.stardust.enchantment.FrenzyEnchantment
import com.nyronium.stardust.enchantment.GainingEnchantment
import com.nyronium.stardust.enchantment.ElectrocutionEnchantment
import com.nyronium.stardust.enchantment.ReachingEnchantment
import com.nyronium.stardust.enchantment.StabilityEnchantment
import com.nyronium.stardust.enchantment.PacingEnchantment
import com.nyronium.stardust.enchantment.WisdomEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(Stardust.ID)
object Stardust {
    const val ID = "stardust"
    val REGISTRY: DeferredRegister<Enchantment> = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ID)

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Infusing a touch of stardust...")
        REGISTRY.register(MOD_BUS)
    }

    val ARMOR_SLOTS: Array<EquipmentSlot> = arrayOf(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
    val HANDS: Array<EquipmentSlot> = arrayOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)

    val ELECTROCUTION: RegistryObject<Enchantment> = REGISTRY.register("electrocution") {
        ElectrocutionEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND)
    }
    val FROSTBITE: RegistryObject<Enchantment> = REGISTRY.register("frostbite") {
        FrostbiteEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND)
    }
    val DECAY: RegistryObject<Enchantment> = REGISTRY.register("decay") {
        DecayEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND)
    }

    val REACHING: RegistryObject<Enchantment> = REGISTRY.register("reaching") {
        ReachingEnchantment(Enchantment.Rarity.UNCOMMON, StardustExtensions.TOOLS, EquipmentSlot.MAINHAND)
    }
    val FRENZY: RegistryObject<Enchantment> = REGISTRY.register("frenzy") {
        FrenzyEnchantment(Enchantment.Rarity.VERY_RARE, StardustExtensions.SWORD, EquipmentSlot.MAINHAND)
    }
    val GAINING: RegistryObject<Enchantment> = REGISTRY.register("gaining") {
        GainingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND)
    }
    val WISDOM: RegistryObject<Enchantment> = REGISTRY.register("wisdom") {
        WisdomEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND)
    }

    val PACING: RegistryObject<Enchantment> = REGISTRY.register("pacing") {
        PacingEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, EquipmentSlot.FEET)
    }
    val STABILITY: RegistryObject<Enchantment> = REGISTRY.register("stability") {
        StabilityEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, EquipmentSlot.FEET)
    }

    val DETERMINATION: RegistryObject<Enchantment> = REGISTRY.register("determination") {
        DeterminationEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_LEGS, EquipmentSlot.LEGS)
    }
}