package com.nyronium.stardust.core

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.content.enchantment.SoulboundEnchantment
import com.nyronium.stardust.content.enchantment.armor.*
import com.nyronium.stardust.content.enchantment.breakable.ReparationEnchantment
import com.nyronium.stardust.content.enchantment.breakable.TenacityEnchantment
import com.nyronium.stardust.content.enchantment.tool.ReachingEnchantment
import com.nyronium.stardust.content.enchantment.tool.WisdomEnchantment
import com.nyronium.stardust.content.enchantment.weapon.*
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object StardustRegistry {
    val REGISTRY: DeferredRegister<Enchantment> = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Stardust.ID)
    
    val STRIKING: RegistryObject<Enchantment> = REGISTRY.register("striking") { StrikingEnchantment() }
    val KINETIC: RegistryObject<Enchantment> = REGISTRY.register("kinetic") { KineticEnchantment() }
    val SPITE: RegistryObject<Enchantment> = REGISTRY.register("spite") { SpiteEnchantment() }
    val RAMPAGE: RegistryObject<Enchantment> = REGISTRY.register("rampage") { RampageEnchantment() }
    val FRENZY: RegistryObject<Enchantment> = REGISTRY.register("frenzy") { FrenzyEnchantment() }
    val ELECTROCUTION: RegistryObject<Enchantment> = REGISTRY.register("electrocution") { ElectrocutionEnchantment() }
    val FROSTBITE: RegistryObject<Enchantment> = REGISTRY.register("frostbite") { FrostbiteEnchantment() }
    val DECAY: RegistryObject<Enchantment> = REGISTRY.register("decay") { DecayEnchantment() }
    val GAINING: RegistryObject<Enchantment> = REGISTRY.register("gaining") { GainingEnchantment() }

    val WISDOM: RegistryObject<Enchantment> = REGISTRY.register("wisdom") { WisdomEnchantment() }
    val REACHING: RegistryObject<Enchantment> = REGISTRY.register("reaching") { ReachingEnchantment() }

    val RESILIENCE: RegistryObject<Enchantment> = REGISTRY.register("resilience") { ResilienceEnchantment() }
    val SHELTERING: RegistryObject<Enchantment> = REGISTRY.register("sheltering") { ShelteringEnchantment() }

    val STEALTHINESS: RegistryObject<Enchantment> = REGISTRY.register("stealthiness") { StealthinessEnchantment() }
    val CONSUMPTION: RegistryObject<Enchantment> = REGISTRY.register("consumption") { ConsumptionEnchantment() }
    val CHARISMA: RegistryObject<Enchantment> = REGISTRY.register("charisma") { CharismaEnchantment() }
    val PEERING: RegistryObject<Enchantment> = REGISTRY.register("peering") { CharismaEnchantment() }

    val REMEDY: RegistryObject<Enchantment> = REGISTRY.register("remedy") { RemedyEnchantment() }
    val NULLIFICATION: RegistryObject<Enchantment> = REGISTRY.register("nullification") { NullificationEnchantment() }

    val ENDURANCE: RegistryObject<Enchantment> = REGISTRY.register("endurance") { EnduranceEnchantment() }
    val DETERMINATION: RegistryObject<Enchantment> = REGISTRY.register("determination") { DeterminationEnchantment() }
    val STABILITY: RegistryObject<Enchantment> = REGISTRY.register("stability") { StabilityEnchantment() }

    val PACING: RegistryObject<Enchantment> = REGISTRY.register("pacing") { PacingEnchantment() }

    val REPARATION: RegistryObject<Enchantment> = REGISTRY.register("reparation") { ReparationEnchantment() }
    val TENACITY: RegistryObject<Enchantment> = REGISTRY.register("tenacity") { TenacityEnchantment() }

    val SOULBOUND: RegistryObject<Enchantment> = REGISTRY.register("soulbound") { SoulboundEnchantment() }
}