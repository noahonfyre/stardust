package com.nyronium.stardust.core

import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber
object TaskScheduler {
    private val tasks = mutableMapOf<() -> Unit, Int>()

    fun schedule(delayTicks: Int, task: () -> Unit) {
        tasks[task] = delayTicks
    }

    @SubscribeEvent
    fun onServerTick(event: TickEvent.ServerTickEvent) {
        if (event.phase != TickEvent.Phase.END) return

        for(task in tasks) {
            val (task, ticksLeft) = task
            if(ticksLeft == 0) {
                task()
                tasks.remove(task)
            } else {
                tasks[task] = ticksLeft-1
            }
        }
    }
}