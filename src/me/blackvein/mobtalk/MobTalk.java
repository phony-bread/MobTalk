package me.blackvein.mobtalk;

/******************
 * Plugin Name: MobTalk
 * Main Class: MobTalk.java
 * Authors: _FrostByte_, _Blackvein_
 * Version: 0.1.0b
 ******************/

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MobTalk extends JavaPlugin
{
    @Override
    public void onEnable() //Called when the plugin is enabled
    {
        ConsoleCommandSender console = getServer().getConsoleSender();
        getServer().getPluginManager().registerEvents(new EventListener(this), this); //Register the event listener
        saveDefaultConfig();
        FileConfiguration config = getConfig();
    }
    
    @Override
    public void onDisable() //Called when the plugin is disabled
    {
        super.onDisable(); //Default shutdown behaviour
    }
}