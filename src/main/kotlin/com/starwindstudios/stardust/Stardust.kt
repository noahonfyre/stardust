package com.starwindstudios.stardust

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Stardust : ModInitializer {
	const val ID: String = "stardust"

	val LOGGER: Logger = LoggerFactory.getLogger(ID)

	override fun onInitialize() {
		LOGGER.info("Hello Fabric world!")
	}
}
