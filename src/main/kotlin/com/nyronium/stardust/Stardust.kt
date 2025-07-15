package com.nyronium.stardust

import com.nyronium.stardust.enchantment.*
import net.minecraft.world.item.enchantment.Enchantment
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

    val STRIKING: RegistryObject<Enchantment> = REGISTRY.register("striking") { StrikingEnchantment() }
    val SPITE: RegistryObject<Enchantment> = REGISTRY.register("spite") { SpiteEnchantment() }
    val FRENZY: RegistryObject<Enchantment> = REGISTRY.register("frenzy") { FrenzyEnchantment() }
    val ELECTROCUTION: RegistryObject<Enchantment> = REGISTRY.register("electrocution") { ElectrocutionEnchantment() }
    val FROSTBITE: RegistryObject<Enchantment> = REGISTRY.register("frostbite") { FrostbiteEnchantment() }
    val DECAY: RegistryObject<Enchantment> = REGISTRY.register("decay") { DecayEnchantment() }
    val GAINING: RegistryObject<Enchantment> = REGISTRY.register("gaining") { GainingEnchantment() }

    val WISDOM: RegistryObject<Enchantment> = REGISTRY.register("wisdom") { WisdomEnchantment() }
    val REACHING: RegistryObject<Enchantment> = REGISTRY.register("reaching") { ReachingEnchantment() }

    val RESILIENCE: RegistryObject<Enchantment> = REGISTRY.register("resilience") { ResilienceEnchantment() }
    val SHELTERING: RegistryObject<Enchantment> = REGISTRY.register("sheltering") { ShelteringEnchantment() }

    val CHARISMA: RegistryObject<Enchantment> = REGISTRY.register("charisma") { CharismaEnchantment() }
    val PEERING: RegistryObject<Enchantment> = REGISTRY.register("peering") { CharismaEnchantment() }

    val ENDURANCE: RegistryObject<Enchantment> = REGISTRY.register("endurance") { EnduranceEnchantment() }
    val DETERMINATION: RegistryObject<Enchantment> = REGISTRY.register("determination") { DeterminationEnchantment() }
    val STABILITY: RegistryObject<Enchantment> = REGISTRY.register("stability") { StabilityEnchantment() }

    val PACING: RegistryObject<Enchantment> = REGISTRY.register("pacing") { PacingEnchantment() }

    val TENACITY: RegistryObject<Enchantment> = REGISTRY.register("tenacity") { TenacityEnchantment() }

    val SOULBOUND: RegistryObject<Enchantment> = REGISTRY.register("soulbound") { TenacityEnchantment() }
}