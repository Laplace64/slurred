@file:Suppress("UnstableApiUsage")

package net.laplace.slurred.commands

import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import net.kyori.adventure.text.Component.text
import net.laplace.slurred.listeners.getDrunkLevel
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class DrunkCommand(
    private val plugin: Plugin
): BasicCommand {
    override fun execute(commandSourceStack: CommandSourceStack, args: Array<out String>) {
        val entity = commandSourceStack.executor ?: return

        if (entity !is Player) return

        val drunk = entity.getDrunkLevel(plugin)
        entity.sendMessage(text("You are $drunk% drunk"))
    }
}