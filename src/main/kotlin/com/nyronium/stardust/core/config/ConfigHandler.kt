package com.nyronium.stardust.core.config

import com.nyronium.stardust.Stardust
import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.fml.config.ModConfig

object ConfigHandler {
    private val BUILDER = ForgeConfigSpec.Builder()

    var disabledEnchantments: ForgeConfigSpec.ConfigValue<MutableList<out String>> = BUILDER
        .comment(" A list of disabled enchantments by their enchantment ID. (e.g. stardust:tenacity)")
        .defineList(
            "disabled_enchantments",
            mutableListOf<String>()
        ) { obj -> obj is String && obj.startsWith("stardust:")}

    var anvilCap: ForgeConfigSpec.IntValue = BUILDER
        .comment(" The maximum amount of levels that the player will lose during anvil transactions. (default: 60, set to 0 to remove limit)")
        .defineInRange("anvil_cap", 90, 0, Int.MAX_VALUE-1)

    var disablePriorWorkPenalty: ForgeConfigSpec.BooleanValue = BUILDER
        .comment(" Toggles the prior work penalty mechanic, which makes an item more expensive to combine in an anvil.")
        .define("disable_prior_work_penalty", false)


    val SPEC: ForgeConfigSpec = BUILDER.build()

    fun register() {
        Stardust.CONTEXT.registerConfig(ModConfig.Type.COMMON, SPEC)
    }
}