package net.axay.hglaborlobby.eventmanager.joinserver

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.event.listen
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent

object JoinMessage {

    fun joinMessage(player: Player): String { 
        return "${KColors.CHARTREUSE}→ ${KColors.POWDERBLUE}${player.name}"

    }

    fun enable() {

        listen<PlayerJoinEvent> {
            // disable original join message
            it.joinMessage = null
        }

    }

}
