package frostbyte.plugins.mobtalk;

/******************
 * Plugin Name: MobTalk
 * Main Class: MobTalk.java
 * Author: _FrostByte_
 * Version: 1.0b
 ******************/

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class MobTalk extends JavaPlugin
{
    private int RADIUS;
    private int taskNum;
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
    static BukkitScheduler scheduler;
    
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
        taskNum = scheduler.scheduleSyncRepeatingTask(this, new RandomTalker(this, talkingMobs), RANDOM_TICKS, RANDOM_TICKS);
    }
    
    @Override
    public void onDisable()
    {
        scheduler.cancelTask(taskNum);
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
        scheduler = this.getServer().getScheduler();
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
        BABYATTACKED_THRESH = getConfig().getDouble("threshold.babyattacked");
        
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
            
            List<String> attacking = getConfig().getStringList("talks." + name + ".attacking");
            for(String message : attacking)
            {
                if(getConfig().isSet("talks." + name + ".attacking"))
                    tm.addAttackMessage(message);
            }
            
            List<String> interact = getConfig().getStringList("talks." + name + ".interact");
            for(String message : interact)
            {
                if(getConfig().isSet("talks." + name + ".interact"))
                    tm.addInteractMessage(message);
            }
            
            List<String> random = getConfig().getStringList("talks." + name + ".random");
            for(String message : random)
            {
                if(getConfig().isSet("talks." + name + ".random"))
                    tm.addRandomMessage(message);
            }
            
            List<String> born = getConfig().getStringList("talks." + name + ".born");
            for(String message : born)
            {
                if(getConfig().isSet("talks." + name + ".born"))
                    tm.addBornMessage(message);
            }
            
            List<String> breed = getConfig().getStringList("talks." + name + ".breed");
            for(String message : breed)
            {
                if(getConfig().isSet("talks." + name + ".breed"))
                    tm.addBreedMessage(message);
            }
            
            List<String> summon = getConfig().getStringList("talks." + name + ".summoned");
            for(String message : summon)
            {
                if(getConfig().isSet("talks." + name + ".summoned"))
                    tm.addSummonMessage(message);
            }
            
            List<String> spawn = getConfig().getStringList("talks." + name + ".spawn");
            for(String message : spawn)
            {
                if(getConfig().isSet("talks." + name + ".spawn"))
                    tm.addSpawnMessage(message);
            }
            
            List<String> babyAttacked = getConfig().getStringList("talks." + name + ".babyattacked");
            for(String message : babyAttacked)
            {
                if(getConfig().isSet("talks." + name + ".babyattacked"))
                    tm.addBabyAttackedMessage(message);
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
                    cs.sendMessage(ChatColor.GREEN + "MobTalk v" + ChatColor.WHITE + VERSION);
                    cs.sendMessage(ChatColor.DARK_GREEN + "By " + ChatColor.LIGHT_PURPLE + "_FrostByte_");
                    cs.sendMessage(ChatColor.DARK_GREEN + "dev.bukkit.org/bukkit-plugins/mobtalk/");
                }
                else if(args[0].equalsIgnoreCase("reload"))
                {
                    loadConfig();
                    cs.sendMessage(ChatColor.AQUA + "MobTalk config reloaded!");
                }
                else
                    cs.sendMessage(ChatColor.RED + "Unknown argument! Try reload or version.");
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

    /**
     * </p>Checks if an entity is a baby.</p>
     *
     * @param ent The entity to check
     * @return Whether or not the entity is a baby
     */
    public boolean isBaby(Entity ent) 
    {
        if (ent instanceof Ageable) 
        {
            Ageable age = (Ageable) ent;
            return !age.isAdult();
        } 
        else
            return false;
    }
    
    /**
     * <p>Checks if any players exist within range,
     *  whether they have permission, 
     *  then sends the message to them.</p>
     * 
     * @param ent The entity the check around
     * @param message The message to send
     */
    public void sendMessage(Entity ent, String message)
    {
        List<Player> list = (getPlayersNear(ent));
        message = message.replace('&', ChatColor.COLOR_CHAR);
        if(list==null) //If there are no players in range, do nothing
            return;
        for(Player target : list)
        {
            String customMessage = message.replaceAll("%player%", target.getName());
            if(target.hasPermission("mobtalk.hear"))
                target.sendMessage(customMessage);
        }
    }
}