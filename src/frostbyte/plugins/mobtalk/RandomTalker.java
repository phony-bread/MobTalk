package frostbyte.plugins.mobtalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class RandomTalker extends BukkitRunnable
{
    private final MobTalk plugin;
    private final HashSet<TalkingMob> set;
    
    public RandomTalker(MobTalk plugin, HashSet set)
    {
        this.plugin = plugin;
        this.set = set;
    }
    
    @Override
    public void run()
    {
        List<World> worlds = plugin.getServer().getWorlds();
        List<Entity> mobList = new ArrayList<>();
        for(World w : worlds)
        {
            List<Entity> entList = w.getEntities();
            for(Entity e : entList)
            {
                if(!(e instanceof Player))
                {
                    for(TalkingMob tm : set)
                    {
                        if(tm.getTalkingMobType().getMobType().equals(e.getType()) && plugin.isBaby(e) == tm.isBaby())
                            mobList.add(e);
                    }
                }
            }
        }
        for(Entity foundEnt : mobList)
        {
            if(MobTalk.RANDOM_THRESH>Math.random())
            {
                for(TalkingMob tm : set)
                {
                    if(tm.getMobType().equals(foundEnt.getType()) && tm.isBaby() == plugin.isBaby(foundEnt))
                    {
                        String message = "<" + tm.getTalkingMobType().getName() + "> ";
                        String chat = tm.getRandomMessage();
                        if(chat!=null)
                        {
                            message += chat;
                            plugin.sendMessage(foundEnt, message);
                        }
                    }
                }
            }
        }
    }
}
