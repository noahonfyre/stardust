package com.nyronium.stardust.data

import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.world.item.ItemStack


class SoulboundData {
    val items: MutableList<ItemStack> = ArrayList()

    fun saveNBTData(nbt: CompoundTag) {
        val listTag = ListTag()
        for (stack in items) {
            listTag.add(stack.save(CompoundTag()))
        }
        nbt.put("soulbound", listTag)
    }

    fun loadNBTData(nbt: CompoundTag) {
        items.clear()
        val listTag = nbt.getList("soulbound", 10)
        for (index in listTag.indices) {
            val stack = ItemStack.of(listTag.getCompound(index))
            items.add(stack)
        }
    }
}