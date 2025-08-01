package com.nyronium.stardust

import com.nyronium.stardust.core.EnchantmentRegistry
import com.nyronium.stardust.core.LootModifiersRegistry
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(Stardust.ID)
object Stardust {
    const val ID = "stardust"

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Infusing a touch of stardust...")
        EnchantmentRegistry.REGISTRY.register(MOD_BUS)
        LootModifiersRegistry.REGISTRY.register(MOD_BUS)
    }
}