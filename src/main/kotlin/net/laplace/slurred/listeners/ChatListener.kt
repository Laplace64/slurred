package net.laplace.slurred.listeners

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.TextComponent
import net.laplace.slurred.parser.Colors
import net.laplace.slurred.parser.Parser
import net.laplace.slurred.parser.State
import net.laplace.slurred.transformation.*
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class ChatListener(
    private val plugin: Plugin
): Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onChat(event: AsyncChatEvent) {
        val player = event.player
        val message = event.message()

        if (message !is TextComponent) return

        val factor = lerp(0.0, 3.0, player.getDrunkLevel(plugin) / 100.0)

        val transformations = listOf(
            Typo(chance=0.05 * factor),
            Slurring(chance=0.3 * factor),
            Repetition(chance=0.1 * factor),
            Pauses(chance=0.2 * factor),
            SwapCase(chance=0.1 * factor),
        )

        val parser = Parser(
            Colors.DARK_PURPLE.hex,
            Colors.WHITE.hex
        )

        val states = parser.parse(
            message.content()
        )

        val component = text().append(
            // cheeky lil trick to not do components
            parser.parse("&6*** &4[&7Grade-12&4] &fFirstName LastName ").map(State::toComponent)
        ).append(
            states.map {
                if (it.inMessage && player.getDrunkLevel(plugin) > 0) {
                    val drunkText = transformations.transform(it.text)

                    State.build {
                        text = drunkText
                        inMessage = true
                        color = it.color
                        italic = it.italic
                        bold = it.bold
                        obfuscated = it.obfuscated
                        underlined = it.underlined
                        strikethrough = it.strikethrough
                    }
                } else {
                    it
                }
            }.map(State::toComponent)
        ).build()

//        states.forEach { state ->
//            component.append(
//                state.toComponent().build()
//            )
//        }


        plugin.server.sendMessage(
            component
        )



        event.isCancelled = true
    }

    private fun lerp(a: Double, b: Double, t: Double): Double {
        return a + t * (b - a)
    }
}

