package me.blackvein.mobtalk;

import java.util.List;
import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListener implements Listener
{
    private final MobTalk plugin;
    
    private final Random gen = new Random();
    
    public EventListener(MobTalk instance)
    {
        plugin = instance;
        
    }
    
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        Entity entity = event.getEntity();
        List<Player> list = (plugin.getPlayersNear(entity));
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
