package com.nyronium.stardust.data

import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

class SoulboundDataProvider : ICapabilitySerializable<CompoundTag> {
    private val soulboundData: SoulboundData = SoulboundData()
    private val optional: LazyOptional<SoulboundData> = LazyOptional.of { soulboundData }

    companion object {
        val SOULBOUND_CAPABILITY: Capability<SoulboundData> = CapabilityManager.get(object : CapabilityToken<SoulboundData>() {})
    }

    override fun serializeNBT(): CompoundTag {
        val nbt = CompoundTag()
        soulboundData.saveNBTData(nbt)
        return nbt
    }

    override fun deserializeNBT(nbt: CompoundTag) {
        soulboundData.loadNBTData(nbt)
    }

    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == SOULBOUND_CAPABILITY) optional.cast() else LazyOptional.empty()
    }
}