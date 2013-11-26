package me.blackvein.mobtalk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListener implements Listener
{
    private final MobTalk plugin;
    private final int radius;
    private final Random gen = new Random();
    
    public EventListener(MobTalk instance)
    {
        plugin = instance;
        radius = plugin.getConfig().getInt("maximumDistance");
    }
    public List<Player> getPlayersNear(Entity ent)
    {
        List<Entity> list = ent.getNearbyEntities(radius, radius, radius);
        List<Player> out = new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i) instanceof Player)
            {
                Player player = (Player) list.get(i);
                out.add(player);
            }
        }
        return out;
    }
    
    public String getChatString(Entity ent, Event event)
    {
       return null; 
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        Entity entity = event.getEntity();
        List<Player> list = (getPlayersNear(entity));
        for(int i=0;i<list.size();i++)
        {
            if (entity.getType().equals(EntityType.PIG))
            {
                Player target = (Player) list.get(i);
                target.sendMessage(plugin.getConfig().getStringList("talks.BabyPig.random").get(0));
                target.sendMessage(plugin.getConfig().getStringList("talks.BabyPig.random").get(1));
                target.sendMessage(plugin.getConfig().getStringList("talks.BabyPig.random").get(2));
            }
        }
    }
}
