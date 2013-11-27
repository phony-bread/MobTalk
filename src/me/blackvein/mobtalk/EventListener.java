package me.blackvein.mobtalk;

import java.util.HashSet;
import java.util.List;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EventListener implements Listener
{
    private final MobTalk plugin;
    private final HashSet<TalkingMob> set;
    
    public EventListener(MobTalk instance, HashSet<TalkingMob> set)
    {
        plugin = instance;
        this.set = set;
    }

    @EventHandler
    public void onEntityAttacked(EntityDamageByEntityEvent event)
    {
        Entity entity = event.getEntity();
        boolean isBaby;
        if(entity instanceof Ageable)
        {
            Ageable age = (Ageable)entity;
            isBaby = !age.isAdult();
        }
        else
            isBaby = false;
        List<Player> list = (plugin.getPlayersNear(entity));
        String message = "";
        for(TalkingMob tm : set)
        {
            if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == isBaby)))
            {
                message += "<" + tm.getTalkingMobType().getName() + "> ";
                message += tm.getAttackedMessage();
            }
        }
        for(Player target : list)
        {
            if(target.hasPermission("mobtalk.hear"))
                target.sendMessage(message);
        }
    }
}
