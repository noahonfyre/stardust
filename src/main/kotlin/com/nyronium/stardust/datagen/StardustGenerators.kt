package com.nyronium.stardust.datagen

import com.nyronium.stardust.Stardust
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber(modid = Stardust.ID, bus = EventBusSubscriber.Bus.MOD)
object StardustGenerators {
    @SubscribeEvent
    fun onGatherData(event: GatherDataEvent) {
        val generator = event.generator
        val packOutput = generator.packOutput

        generator.addProvider(event.includeClient(), StardustLanguageProvider(packOutput))
    }
}