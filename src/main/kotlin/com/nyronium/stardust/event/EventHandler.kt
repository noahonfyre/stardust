package com.nyronium.stardust.event

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.misc.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.monster.piglin.Piglin
import net.minecraft.world.entity.monster.piglin.PiglinBrute
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.EnderManAngerEvent
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent
import net.minecraftforge.event.entity.player.CriticalHitEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object EventHandler {
    var tickTasks = mutableListOf<(TickEvent.PlayerTickEvent) -> Unit>()

    @SubscribeEvent
    fun onCriticalHit(event: CriticalHitEvent) {
        val player = event.entity
        if(player.level().isClientSide) return

        if(!event.isVanillaCritical) return
        if(StardustUtils.hasEnchantment(Stardust.STRIKING.get(), player.getItemBySlot(EquipmentSlot.MAINHAND))) {
            val strikingLevel = StardustUtils.getLevel(Stardust.STRIKING.get(), player.getItemBySlot(EquipmentSlot.MAINHAND))
            event.damageModifier *= 1+strikingLevel/Stardust.STRIKING.get().maxLevel
            event.result = Event.Result.ALLOW
            return
        }
        event.result = Event.Result.DEFAULT
    }

    @SubscribeEvent
    fun onLivingChangeTarget(event: LivingChangeTargetEvent) {
        if(event.entity.level().isClientSide) return
        if(event.newTarget == null) return

        if(event.entity !is Piglin || event.entity !is PiglinBrute) return
        if(!StardustUtils.hasEnchantment(Stardust.PEERING.get(), event.newTarget.getItemBySlot(EquipmentSlot.HEAD))) return
        event.isCanceled = true
    }

    @SubscribeEvent
    fun onEnderManAnger(event: EnderManAngerEvent) {
        if(event.entity.level().isClientSide) return
        event.isCanceled = StardustUtils.hasEnchantment(Stardust.PEERING.get(), event.player.getItemBySlot(EquipmentSlot.HEAD))
    }

    @SubscribeEvent
    fun onBlockBreak(event: BlockEvent.BreakEvent) {
        val player = event.player
        if(!StardustUtils.hasEnchantment(Stardust.WISDOM.get(), player.getItemBySlot(EquipmentSlot.MAINHAND))) return
        val wisdomLevel = StardustUtils.getLevel(Stardust.WISDOM.get(), player.getItemBySlot(EquipmentSlot.MAINHAND))
        event.expToDrop *= 1+wisdomLevel/Stardust.WISDOM.get().maxLevel
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if(event.side.isClient) return
        if(event.phase != TickEvent.Phase.END) return
        tickTasks.forEach { task -> task.invoke(event) }
    }
}