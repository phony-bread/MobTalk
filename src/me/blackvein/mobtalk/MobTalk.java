package me.blackvein.mobtalk;

/******************
 * Plugin Name: MobTalk
 * Main Class: MobTalk.java
 * Authors: _FrostByte_, _Blackvein_
 * Version: 0.1.0b
 ******************/

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MobTalk extends JavaPlugin
{
    @Override
    public void onEnable() //Called when the plugin is enabled
    {
        ConsoleCommandSender console = getServer().getConsoleSender();
        getServer().getPluginManager().registerEvents(new EventListener(), this); //Register the event listener
    }
    
    @Override
    public void onDisable() //Called when the plugin is enabled
    {
        super.onDisable(); //Default shutdown behaviour(for now)
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) //Called when a command specific to this plugin is called
    { 
        if(cmd.getName().equalsIgnoreCase("test"))
        {
            cs.getServer().broadcastMessage("Test Complete");
            return true;
        }
        return false;
    }
}