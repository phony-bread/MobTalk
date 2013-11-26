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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MobTalk extends JavaPlugin
{
    private int RADIUS;
    private final HashSet<TalkingMob> talkingMobs = new HashSet<>();
    
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        RADIUS = config.getInt("maximumDistance");
    }
    
    @Override
    public void onDisable()
    {
        super.onDisable();
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
     * @param child Whether the type is a child or not
     * @return TalkingMob found, or null if not found
     */
    public TalkingMob getTalkingMob(EntityType et, boolean child){
        
        for(TalkingMob t : talkingMobs){
            
            if(t.getMobType().equals(et) && t.isChild() == child)
                return t;
            
        }
        
        return null;
        
    }
    
    public void LEWIS_READ_ME(){
        
        //THIS IS HOW YOU DO THE TALKINGMOB CLASS
        TalkingMob tm = new TalkingMob(EntityType.COW, false); //false cause not child
        tm.addRandomMessage("lol im a cow");
        tm.addAttackedMessage("OWWWWWWW YOU HIT MEEEE");
        talkingMobs.add(tm);
        
    }
    
    
}