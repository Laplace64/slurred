@file:Suppress("UnstableApiUsage")

package net.laplace.slurred

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import net.laplace.slurred.commands.DrunkCommand
import net.laplace.slurred.commands.SetDrunk
import net.laplace.slurred.listeners.*
import org.bukkit.plugin.java.JavaPlugin

class Slurred : JavaPlugin() {
    override fun onEnable() {
        lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val commands = event.registrar()

            commands.register("drunk", DrunkCommand(this))
            commands.register("setdrunk", SetDrunk(this))
        }

        server.pluginManager.registerEvents(AlcoholListener(this), this)
        server.pluginManager.registerEvents(ChatListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
