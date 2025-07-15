package com.nyronium.stardust.event

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.data.SoulboundDataProvider
import com.nyronium.stardust.misc.StardustUtils
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object SoulboundHandler {
    val soulboundItems = mutableMapOf<Player, Inventory>()

    @SubscribeEvent
    fun onLivingDrops(event: LivingDropsEvent) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player
        println("Setup")
        if(!player.inventory.hasAnyMatching { StardustUtils.hasEnchantment(Stardust.SOULBOUND.get(), it) }) return
        println("Has matching")
        event.isCanceled = true
        println("Canceled")
        for(stack in player.inventory.items) {
            println("Item")
            if(StardustUtils.hasEnchantment(Stardust.SOULBOUND.get(), stack)) continue
            player.drop(stack, false, false)
            player.inventory.removeItem(stack)
        }
    }

    @SubscribeEvent
    fun onPlayerClone(event: PlayerEvent.Clone) {
        event.entity.sendSystemMessage(Component.literal("Clone triggered!"))
        if (!event.isWasDeath) return

        val oldInventory = soulboundItems[event.original] ?: return
        val newPlayer = event.entity

        for (slot in 0 until oldInventory.containerSize) {
            event.entity.sendSystemMessage(Component.literal(oldInventory.getItem(slot).displayName.string))
            val stack = oldInventory.getItem(slot)
            if (!stack.isEmpty && StardustUtils.hasEnchantment(Stardust.SOULBOUND.get(), stack)) {
                newPlayer.inventory.setItem(slot, stack.copy())
            }
        }
    }

    fun onLivingDeath(event: LivingDeathEvent) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player
        player.sendSystemMessage(Component.literal("Death triggered!"))
        val soulboundItems = player.inventory.items.filter { StardustUtils.hasEnchantment(Stardust.SOULBOUND.get(), it) }
        player.getCapability(SoulboundDataProvider.SOULBOUND_CAPABILITY).ifPresent {
            it.items.addAll(soulboundItems)
        }
    }

    fun onPlayerRespawn(event: PlayerEvent.PlayerRespawnEvent) {
        if(event.entity.level().isClientSide) return
        val player = event.entity
        val cap = player.getCapability(SoulboundDataProvider.SOULBOUND_CAPABILITY)
        player.sendSystemMessage(Component.literal(cap.toString()))
        cap.ifPresent {
            player.inventory.items.addAll(it.items)
        }
    }

    fun onAttachCapabilitiesPlayer(event: AttachCapabilitiesEvent<Entity>) {
        if(event.`object` !is Player) return
        if(!event.`object`.getCapability(SoulboundDataProvider.SOULBOUND_CAPABILITY).isPresent) {
            event.addCapability(ResourceLocation(Stardust.ID, "soulbound"), SoulboundDataProvider())
        }
    }

    fun d(event: PlayerEvent.Clone) {
        if (!event.isWasDeath) return

        event.original.getCapability(SoulboundDataProvider.SOULBOUND_CAPABILITY).ifPresent {
            oldData -> event.entity.getCapability(SoulboundDataProvider.SOULBOUND_CAPABILITY).ifPresent {
                newData -> newData.items.addAll(oldData.items)
            }
        }
    }

    fun onRegisterCapabilities(event: RegisterCapabilitiesEvent) {
        event.register(SoulboundDataProvider::class.java)
    }
}