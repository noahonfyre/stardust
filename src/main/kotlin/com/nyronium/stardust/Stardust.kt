package com.nyronium.stardust

import com.nyronium.stardust.core.config.ConfigHandler
import com.nyronium.stardust.core.registry.EnchantmentRegistry
import com.nyronium.stardust.core.registry.LootModifiersRegistry
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(Stardust.ID)
object Stardust {
    const val ID = "stardust"
    val CONTEXT: ModLoadingContext = ModLoadingContext.get()
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Infusing a touch of stardust...")
        ConfigHandler.register()

        EnchantmentRegistry.REGISTRY.register(MOD_BUS)
        LootModifiersRegistry.REGISTRY.register(MOD_BUS)
    }
}