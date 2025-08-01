package com.nyronium.stardust.datagen

import com.nyronium.stardust.Stardust
import net.minecraft.core.HolderLookup
import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import java.util.concurrent.CompletableFuture

@Mod.EventBusSubscriber(modid = Stardust.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object StardustGenerators {
    @SubscribeEvent
    fun onGatherData(event: GatherDataEvent) {
        val generator: DataGenerator = event.generator
        val packOutput: PackOutput = generator.packOutput
        val existingFileHelper: ExistingFileHelper = event.existingFileHelper
        val lookupProvider: CompletableFuture<HolderLookup.Provider> = event.lookupProvider

        generator.addProvider(event.includeServer(), StardustGlobalLootModifiersProvider(packOutput))
    }
}