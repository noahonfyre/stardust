package com.nyronium.stardust.core.registry

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.content.enchantment.SoulboundEnchantment
import com.nyronium.stardust.content.enchantment.armor.ResilienceEnchantment
import com.nyronium.stardust.content.enchantment.armor.ShelteringEnchantment
import com.nyronium.stardust.content.enchantment.armor.boots.PacingEnchantment
import com.nyronium.stardust.content.enchantment.armor.chestplate.NullificationEnchantment
import com.nyronium.stardust.content.enchantment.armor.chestplate.RemedyEnchantment
import com.nyronium.stardust.content.enchantment.armor.helmet.CharismaEnchantment
import com.nyronium.stardust.content.enchantment.armor.helmet.ConsumptionEnchantment
import com.nyronium.stardust.content.enchantment.armor.helmet.StealthinessEnchantment
import com.nyronium.stardust.content.enchantment.armor.leggings.DeterminationEnchantment
import com.nyronium.stardust.content.enchantment.armor.leggings.DriftEnchantment
import com.nyronium.stardust.content.enchantment.armor.leggings.EnduranceEnchantment
import com.nyronium.stardust.content.enchantment.breakable.TenacityEnchantment
import com.nyronium.stardust.content.enchantment.elytra.LiftEnchantment
import com.nyronium.stardust.content.enchantment.elytra.ThrustingEnchantment
import com.nyronium.stardust.content.enchantment.tool.ReachingEnchantment
import com.nyronium.stardust.content.enchantment.tool.WisdomEnchantment
import com.nyronium.stardust.content.enchantment.weapon.RampageEnchantment
import com.nyronium.stardust.content.enchantment.weapon.axe.KineticEnchantment
import com.nyronium.stardust.content.enchantment.weapon.axe.StrikingEnchantment
import com.nyronium.stardust.content.enchantment.weapon.sword.DecayEnchantment
import com.nyronium.stardust.content.enchantment.weapon.sword.FrostbiteEnchantment
import com.nyronium.stardust.content.enchantment.weapon.sword.GainingEnchantment
import com.nyronium.stardust.content.enchantment.weapon.sword.VenomEnchantment
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object EnchantmentRegistry {
    val REGISTRY: DeferredRegister<Enchantment> = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Stardust.ID)

    val RAMPAGE: RegistryObject<Enchantment> = REGISTRY.register("rampage") { RampageEnchantment() }
    val DECAY: RegistryObject<Enchantment> = REGISTRY.register("decay") { DecayEnchantment() }
    val VENOM: RegistryObject<Enchantment> = REGISTRY.register("venom") { VenomEnchantment() }
    val FROSTBITE: RegistryObject<Enchantment> = REGISTRY.register("frostbite") { FrostbiteEnchantment() }
    val GAINING: RegistryObject<Enchantment> = REGISTRY.register("gaining") { GainingEnchantment() }

    val WISDOM: RegistryObject<Enchantment> = REGISTRY.register("wisdom") { WisdomEnchantment() }
    val REACHING: RegistryObject<Enchantment> = REGISTRY.register("reaching") { ReachingEnchantment() }

    val STRIKING: RegistryObject<Enchantment> = REGISTRY.register("striking") { StrikingEnchantment() }
    val KINETIC: RegistryObject<Enchantment> = REGISTRY.register("kinetic") { KineticEnchantment() }

    val SHELTERING: RegistryObject<Enchantment> = REGISTRY.register("sheltering") { ShelteringEnchantment() }
    val RESILIENCE: RegistryObject<Enchantment> = REGISTRY.register("resilience") { ResilienceEnchantment() }

    val STEALTHINESS: RegistryObject<Enchantment> = REGISTRY.register("stealthiness") { StealthinessEnchantment() }
    val CONSUMPTION: RegistryObject<Enchantment> = REGISTRY.register("consumption") { ConsumptionEnchantment() }
    val CHARISMA: RegistryObject<Enchantment> = REGISTRY.register("charisma") { CharismaEnchantment() }

    val REMEDY: RegistryObject<Enchantment> = REGISTRY.register("remedy") { RemedyEnchantment() }
    val NULLIFICATION: RegistryObject<Enchantment> = REGISTRY.register("nullification") { NullificationEnchantment() }

    val ENDURANCE: RegistryObject<Enchantment> = REGISTRY.register("endurance") { EnduranceEnchantment() }
    val DRIFT: RegistryObject<Enchantment> = REGISTRY.register("drift") { DriftEnchantment() }
    val DETERMINATION: RegistryObject<Enchantment> = REGISTRY.register("determination") { DeterminationEnchantment() }

    val PACING: RegistryObject<Enchantment> = REGISTRY.register("pacing") { PacingEnchantment() }

    val LIFT: RegistryObject<Enchantment> = REGISTRY.register("lift") { LiftEnchantment() }
    val THRUSTING: RegistryObject<Enchantment> = REGISTRY.register("thrusting") { ThrustingEnchantment() }

    val TENACITY: RegistryObject<Enchantment> = REGISTRY.register("tenacity") { TenacityEnchantment() }

    val SOULBOUND: RegistryObject<Enchantment> = REGISTRY.register("soulbound") { SoulboundEnchantment() }
}