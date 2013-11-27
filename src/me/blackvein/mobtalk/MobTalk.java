package me.blackvein.mobtalk;

/******************
 * Plugin Name: MobTalk
 * Main Class: MobTalk.java
 * Authors: _FrostByte_, _Blackvein_
 * Version: 0.1.0b
 ******************/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MobTalk extends JavaPlugin
{
    private int RADIUS;
    private String VERSION;
    private final HashSet<TalkingMob> talkingMobs = new HashSet<>();

    
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new EventListener(this, talkingMobs), this);
        VERSION = getDescription().getVersion();
        saveDefaultConfig();
        loadMobs();
        loadConfig();
    }
    
    @Override
    public void onDisable()
    {
        super.onDisable();
    }
    
    public void loadMobs()
    {
        for(TalkingMobType tmt : TalkingMobType.values())
        {
            talkingMobs.add(new TalkingMob(tmt.getMobType(),tmt.isBaby(), tmt));
        }
    }
    
    /**
     * <p>Loads/reloads instance data from the config.</p>
     */
    public void loadConfig()
    {
        RADIUS = getConfig().getInt("maximumDistance");
        for(TalkingMob tm : talkingMobs)
        {
            tm.clearMessages();
            List<String> messages = getConfig().getStringList("talks." + tm.getTalkingMobType().getName() + ".attacked");
            for(String message : messages)
            {
                tm.addAttackedMessage(message);
            }
        }
    }
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("mobtalk"))
        {
            if(args.length<1)
                cs.sendMessage("Need arguments!");
            else
            {
                if(args[0].equalsIgnoreCase("version"))
                {
                    cs.sendMessage(ChatColor.GREEN + "MobTalk v" + VERSION);
                    cs.sendMessage(ChatColor.DARK_GREEN + "By _Blackvein_ and _FrostByte_");
                    cs.sendMessage(ChatColor.DARK_GREEN + "dev.bukkit.org/bukkit-plugins/mobtalk/");
                }
                else if(args[0].equalsIgnoreCase("reload"))
                    loadConfig();
                else
                    cs.sendMessage("Unknown argument! Try reload or version.");
            }
            return true;
        }
        return false;
    }
    
    /**
     * <p>Searches for nearby players to hear mob speech. Uses the RADIUS
     * constant as distance in all directions.</p>
     * 
     * @param ent The mob to search from
     * @return List of found players, or null if none found
     */
    public List<Player> getPlayersNear(Entity ent)
    {
        List<Entity> list = ent.getNearbyEntities(RADIUS, RADIUS, RADIUS);
        List<Player> out = new ArrayList<>();
        for(Entity e : list)
        {
            if(e instanceof Player)
            {
                Player player = (Player) e;
                out.add(player);
            }
        }
        
        return(!out.isEmpty() ? out : null);
    }
    
    /**
     * <p>Returns appropriate MobTalk message from config.</p>
     * 
     * @param ent The mob entity
     * @param type Message type
     * @return Message found, or null if not found
     */
    public String getChatString(Entity ent, String type)
    {
       return null; 
    }
    
    /**
     * <p>Retrieves a TalkingMob to acquire messages from.</p>
     * 
     * @param et Mob EntityType
     * @param baby Whether the type is a baby or not
     * @return TalkingMob found, or null if not found
     */
    public TalkingMob getTalkingMob(EntityType et, boolean baby){
        
        for(TalkingMob t : talkingMobs)
        {
            if(t.getMobType().equals(et) && t.isBaby() == baby)
                return t;
        }
        return null;
    }
}