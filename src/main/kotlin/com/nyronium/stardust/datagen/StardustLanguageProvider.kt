package com.nyronium.stardust.datagen

import com.nyronium.stardust.Stardust
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider
import java.util.Locale.getDefault

class StardustLanguageProvider(packOutput: PackOutput) : LanguageProvider(packOutput, Stardust.ID, "en_us") {
    override fun addTranslations() {
        Stardust.REGISTRY.entries.forEach { item -> add("enchantment.${Stardust.ID}.${item.id.path}", formatTranslation(item.id.path)) }
    }

    private fun formatTranslation(id: String): String {
        return id.split("_").joinToString(" ") { it -> it.replaceFirstChar { it.titlecase(getDefault()) } }
    }
}