package com.nyronium.stardust.event

import com.nyronium.stardust.Stardust
import com.nyronium.stardust.StardustUtils
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.common.ForgeMod
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import java.util.UUID

@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object EventHandler {

    @SubscribeEvent
    fun onBlockBreak(event: BlockEvent.BreakEvent) {
        val player = event.player
        if(!StardustUtils.hasEnchantment(Stardust.WISDOM.get(), player.getItemBySlot(EquipmentSlot.MAINHAND))) return
        val wisdomLevel = StardustUtils.getLevel(Stardust.WISDOM.get(), player.getItemBySlot(EquipmentSlot.MAINHAND))
        event.expToDrop *= 1+wisdomLevel
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if(event.side.isClient) return
        if(event.phase != TickEvent.Phase.END) return
        val player = event.player

        handleDetermination(player)

        registerAttributeModifier(player, Stardust.REACHING.get(),
            EquipmentSlot.MAINHAND,
            ForgeMod.ENTITY_REACH.get(),
            UUID.fromString("0acfdbee-af25-42bb-bfa0-5d9800ad2451"),
            AttributeModifier.Operation.ADDITION,
        ) { level -> 1.0*level }

        registerAttributeModifier(player, Stardust.REACHING.get(),
            EquipmentSlot.MAINHAND,
            ForgeMod.BLOCK_REACH.get(),
            UUID.fromString("b31a85d1-653b-4557-9876-a2f158a0e1aa"),
            AttributeModifier.Operation.ADDITION,
        ) { level -> 1.0*level }

        registerAttributeModifier(player, Stardust.FRENZY.get(),
            EquipmentSlot.MAINHAND,
            Attributes.ATTACK_SPEED,
            UUID.fromString("c4f8833d-33a5-446b-b95d-9e21a240dfd6"),
            AttributeModifier.Operation.ADDITION,
        ) { level -> 0.4*level }

        registerAttributeModifier(player, Stardust.PACING.get(),
            EquipmentSlot.FEET,
            Attributes.MOVEMENT_SPEED,
            UUID.fromString("d1a3f204-7ad8-4f5c-a4f2-67d2e8d75958"),
            AttributeModifier.Operation.ADDITION,
        ) { level -> 0.015*level }

        registerAttributeModifier(player, Stardust.STABILITY.get(),
            EquipmentSlot.FEET,
            Attributes.KNOCKBACK_RESISTANCE,
            UUID.fromString("83774500-fc34-45b2-937a-44773e61ebaf"),
            AttributeModifier.Operation.ADDITION,
        ) { level -> 1.0*(level/Stardust.STABILITY.get().maxLevel) }
    }

    fun handleDetermination(player: Player) {
        if(player.isCrouching && StardustUtils.hasEnchantment(Stardust.DETERMINATION.get(), player.getItemBySlot(EquipmentSlot.LEGS))) {
            val determinationLevel = StardustUtils.getLevel(Stardust.DETERMINATION.get(), player.getItemBySlot(EquipmentSlot.LEGS))

            if(player.tickCount % 20 == 0) {
                player.heal(0.5f*determinationLevel)
            }
        }
    }

    fun registerAttributeModifier(
        player: Player,
        enchantment: Enchantment,
        slot: EquipmentSlot,
        attribute: Attribute,
        uuid: UUID,
        operation: AttributeModifier.Operation,
        valueOfLevel: (Int) -> Double
    ) {
        val attribute = player.getAttribute(attribute) ?: return

        if(!StardustUtils.hasEnchantment(enchantment, player.getItemBySlot(slot))) {
            if(attribute.getModifier(uuid) == null) return
            attribute.removeModifier(uuid)
            return
        }
        val enchantmentLevel = StardustUtils.getLevel(enchantment, player.getItemBySlot(slot))
        val hasModifier = attribute.getModifier(uuid) != null
        val isModifierAmountChanged = attribute.getModifier(uuid)?.amount != valueOfLevel(enchantmentLevel)

        if(hasModifier && isModifierAmountChanged) attribute.removeModifier(uuid)
        if(hasModifier) return

        attribute.addTransientModifier(AttributeModifier(uuid, "Enchantment modifier", valueOfLevel(enchantmentLevel), operation))
    }
}