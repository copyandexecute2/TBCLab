package net.axay.hglaborlobby.main

import net.axay.hglaborlobby.damager.Damager
import net.axay.hglaborlobby.chat.ChatFormatter
import net.axay.hglaborlobby.damager.DamageCommand
import net.axay.hglaborlobby.eventmanager.joinserver.JoinMessage
import net.axay.hglaborlobby.eventmanager.joinserver.OnJoinManager
import net.axay.hglaborlobby.eventmanager.leaveserver.KickMessageListener
import net.axay.hglaborlobby.eventmanager.leaveserver.OnLeaveManager
import net.axay.hglaborlobby.functionality.LobbyItems
import net.axay.hglaborlobby.data.database.holder.PlayerSettingsHolder
import net.axay.hglaborlobby.database.DatabaseManager
import net.axay.hglaborlobby.functionality.SoupHealing
import net.axay.hglaborlobby.gui.guis.*
import net.axay.hglaborlobby.pads.ElytraLauncher
import net.axay.hglaborlobby.protection.ServerProtection
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.extensions.bukkit.info
import net.axay.kspigot.extensions.bukkit.register
import net.axay.kspigot.extensions.bukkit.success
import net.axay.kspigot.extensions.console
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.main.KSpigot
import net.axay.kspigot.sound.sound
import org.bukkit.Sound

class InternalMainClass : KSpigot() {

    companion object {
        lateinit var INSTANCE: InternalMainClass; private set
    }

    override fun load() {

        INSTANCE = this

        console.info("Loading Lobby plugin...")

    }

    override fun startup() {

        ServerProtection.enable()

        PlayerSettingsHolder.enable()

        SoupHealing.enable()
        JoinMessage.enable()
        OnJoinManager.enable()
        LobbyItems.enable()
        OnLeaveManager.enable()
        KickMessageListener.enable()
        ChatFormatter.enable()
        Damager.enable()
        ElytraLauncher.enable()

        AdminGUI.register("admingui")
        DamageCommand.register("damage")


        // Main GUI
        MainGUI.enable()
        WarpGUI.enable()
        PlayerVisiblityGUI.enable()
        //PrivacySettingsGUI.enable()

        broadcast("${KColors.MEDIUMSPRINGGREEN}-> ENABLED PLUGIN")
        onlinePlayers.forEach { it.sound(Sound.BLOCK_BEACON_ACTIVATE) }

        console.success("Lobby plugin enabled.")

    }

    override fun shutdown() {

        console.info("Shutting down Lobby plugin...")

        DatabaseManager.mongoDB.close()

        broadcast("${KColors.TOMATO}-> DISABLING PLUGIN ${KColors.DARKGRAY}(maybe a reload)")
        onlinePlayers.forEach { it.sound(Sound.BLOCK_BEACON_DEACTIVATE) }

        console.success("Shut down Lobby plugin.")

    }

}

val Manager by lazy { InternalMainClass.INSTANCE }