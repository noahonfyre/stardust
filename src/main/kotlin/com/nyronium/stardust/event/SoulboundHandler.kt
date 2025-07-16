package com.nyronium.stardust.event

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.misc.StardustUtils
import net.minecraft.network.chat.Component
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object SoulboundHandler {
    @SubscribeEvent
    fun onPlayerClone(event: PlayerEvent.Clone) {
        event.entity.sendSystemMessage(Component.literal("Clone triggered!"))
        if (!event.isWasDeath) return

        val oldPlayer = event.original
        val newPlayer = event.entity

        for (slot in 0 until oldPlayer.inventory.containerSize) {
            event.entity.sendSystemMessage(Component.literal(oldPlayer.inventory.getItem(slot).displayName.string))
            val stack = oldPlayer.inventory.getItem(slot)
            if (!stack.isEmpty && StardustUtils.hasEnchantment(Stardust.SOULBOUND.get(), stack)) {
                newPlayer.inventory.setItem(slot, stack.copy())
            }
        }
    }
}