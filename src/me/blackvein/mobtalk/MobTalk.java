package me.blackvein.mobtalk;

/******************
 * Plugin Name: MobTalk
 * Main Class: MobTalk.java
 * Authors: _FrostByte_, _Blackvein_
 * Version: 0.1.0b
 ******************/

import java.io.File;
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
    static double BORN_THRESH;
    static double RANDOM_THRESH;
    static double INTERACT_THRESH;
    static double ATTACK_THRESH;
    static double ATTACKED_THRESH;
    static double KILLED_THRESH;
    static double SUMMONED_THRESH;
    static double BREED_THRESH;
    static double BABYATTACKED_THRESH;
    static int RANDOM_TICKS;
    
    private final HashSet<TalkingMob> talkingMobs = new HashSet<>();
    File configFile = new File(this.getDataFolder() + "/config.yml");
    
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new EventListener(this, talkingMobs), this);
        VERSION = getDescription().getVersion();
        loadMobs();
        saveDefaultConfig();
        loadConfig();
    }
    
    @Override
    public void onDisable()
    {
        super.onDisable();
    }
    
    /**
     * <p>Creates a new TalkingMob for every TalkingMobType and adds it to talkingMobs</p>
     */
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
        RANDOM_TICKS = getConfig().getInt("randomTicks");
        BORN_THRESH = getConfig().getDouble("threshold.born");
        RANDOM_THRESH = getConfig().getDouble("threshold.random");
        INTERACT_THRESH = getConfig().getDouble("threshold.interact");
        ATTACK_THRESH = getConfig().getDouble("threshold.attack");
        ATTACKED_THRESH = getConfig().getDouble("threshold.attacked");
        KILLED_THRESH = getConfig().getDouble("threshold.killed");
        SUMMONED_THRESH = getConfig().getDouble("threshold.summoned");
        BREED_THRESH = getConfig().getDouble("threshold.breed");
        BABYATTACKED_THRESH = getConfig().getDouble("thershold.babyattacked");
        
        for(TalkingMob tm : talkingMobs)
        {
            tm.clearMessages();
            String name = tm.getTalkingMobType().getName();
            List<String> attacked = getConfig().getStringList("talks." + name + ".attacked");
            for(String message : attacked)
            {
                if(getConfig().isSet("talks." + name + ".attacked"))
                    tm.addAttackedMessage(message);
            }
            List<String> killed = getConfig().getStringList("talks." + name + ".killed");
            for(String message : killed)
            {
                if(getConfig().isSet("talks." + name + ".killed"))
                    tm.addDeathMessage(message);
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