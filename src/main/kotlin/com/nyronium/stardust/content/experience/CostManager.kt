package com.nyronium.stardust.content.experience

import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.EnchantmentMenu

// Some code taken from https://github.com/Tfarcenim/BalancedEnchanting
object CostManager {
    val selectedLocal: ThreadLocal<Int> = ThreadLocal.withInitial { 0 }

    private fun getRequiredExperienceToNextLevel(level: Int): Int {
        return if (level >= 30) {
            112 + (level - 30) * 9
        } else {
            if (level >= 15) 37 + (level - 15) * 5 else 7 + level * 2
        }
    }

    fun levelToTotalExperience(level: Int): Int {
        var sum = 0
        for (i in 0 until level) {
            sum += getRequiredExperienceToNextLevel(i)
        }
        return sum
    }

    fun handleEnchantXp(player: Player, cost: Int) {
        val menu = player.containerMenu

        if (menu is EnchantmentMenu) {
            val levelsRequired: Int = menu.costs[selectedLocal.get()]

            player.giveExperiencePoints(
                levelToTotalExperience(levelsRequired)-levelToTotalExperience(levelsRequired + cost)
            )
        }
    }
}