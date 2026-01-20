package com.nyronium.stardust.core.config

import com.nyronium.stardust.Stardust
import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.fml.config.ModConfig

object ConfigHandler {
    private val BUILDER = ForgeConfigSpec.Builder()

    var disabledEnchantments: ForgeConfigSpec.ConfigValue<MutableList<out String>> = BUILDER
        .comment(
            " A list of disabled enchantments by their enchantment ID (e.g. stardust:tenacity)"
        )
        .defineList(
            "disabled_enchantments",
            mutableListOf<String>()
        ) { obj -> obj is String }


    val SPEC: ForgeConfigSpec = BUILDER.build()

    fun register() {
        Stardust.CONTEXT.registerConfig(ModConfig.Type.COMMON, SPEC)
    }
}