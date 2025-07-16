package com.nyronium.stardust.event

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.misc.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.monster.piglin.Piglin
import net.minecraft.world.entity.monster.piglin.PiglinBrute
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.EnderManAngerEvent
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent
import net.minecraftforge.event.entity.living.LivingHealEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.event.entity.player.CriticalHitEvent
import net.minecraftforge.event.entity.player.PlayerXpEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object EventHandler {
    var tickTasks = mutableListOf<(TickEvent.PlayerTickEvent) -> Unit>()

    @SubscribeEvent
    fun onLivingHeal(event: LivingHealEvent) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player

        if(!StardustUtils.hasEnchantment(Stardust.DETERMINATION.get(), player.getItemBySlot(EquipmentSlot.LEGS))) return
        val determinationLevel = StardustUtils.getLevel(Stardust.DETERMINATION.get(), player.getItemBySlot(EquipmentSlot.LEGS))

        if(!player.isCrouching) return

        event.amount *= 1+determinationLevel/Stardust.DETERMINATION.get().maxLevel
    }

    @SubscribeEvent
    fun onLivingHurt(event: LivingHurtEvent) {
        if(event.entity.level().isClientSide) return
        if(event.entity !is Player) return
        val player = event.entity as Player

        if(!StardustUtils.hasEnchantment(Stardust.NULLIFICATION.get(), player.getItemBySlot(EquipmentSlot.CHEST))) return
        val nullificationLevel = StardustUtils.getLevel(Stardust.NULLIFICATION.get(), player.getItemBySlot(EquipmentSlot.CHEST))

        val random = (0..100).random()
        if(random <= nullificationLevel) {
            event.isCanceled = true
        }
    }

    @SubscribeEvent
    fun onPickupExperience(event: PlayerXpEvent.PickupXp) {
        if(event.entity.level().isClientSide) return
        val player = event.entity

        if(!StardustUtils.hasEnchantment(Stardust.REMEDY.get(), player.getItemBySlot(EquipmentSlot.CHEST))) return

        if(player.foodData.foodLevel < 20) {
            player.foodData.eat(1, event.orb.value/10f)
        } else if(player.health < player.maxHealth) {
            player.heal(event.orb.value/10f)
        } else return

        event.orb.value = 0
    }

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
        if(event.newTarget !is Player) return

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