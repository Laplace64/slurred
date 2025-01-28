package net.laplace.slurred.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin
import kotlin.math.max
import kotlin.math.min

class AlcoholListener(
    private val plugin: Plugin
): Listener {
    @EventHandler
    fun onAlcoholDrink(event: PlayerInteractEvent) {
        val item = event.item ?: return

        if (item.type == Material.IRON_HORSE_ARMOR) {
            val player = event.player
            player.playSound(player.location, "entity.generic.drink", 1.0f, 1.0f)

            val drunk = player.getDrunkLevel(plugin)
            player.setDrunkLevel(plugin, drunk + 10)
        }
    }
}

fun Player.getDrunkLevel(plugin: Plugin): Int {
    val key = NamespacedKey(plugin, "drunk")
    return persistentDataContainer.get(key, PersistentDataType.INTEGER) ?: 0
}

fun Player.setDrunkLevel(plugin: Plugin, level: Int) {
    val key = NamespacedKey(plugin, "drunk")
    persistentDataContainer.set(key, PersistentDataType.INTEGER, max(0, min(100, level)))
}
