@file:Suppress("UnstableApiUsage")

package net.laplace.slurred.commands

import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import net.laplace.slurred.listeners.setDrunkLevel
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class SetDrunk(
    private val plugin: Plugin
): BasicCommand {
    override fun execute(commandSourceStack: CommandSourceStack, args: Array<out String>) {
        val entity = commandSourceStack.executor ?: return

        if (entity !is Player) return

        args[0].toIntOrNull()?.let { level ->
            entity.setDrunkLevel(plugin, level)
        }
    }
}