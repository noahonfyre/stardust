package com.nyronium.stardust.event

import com.nyronium.stardust.Stardust
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent


@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
class ModBusEventListener {
    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        if(ModList.get().isLoaded("enchdesc")) {
            println("enchdesc detected")
        }
    }
}