package me.blackvein.mobtalk;

import java.util.HashSet;
import java.util.List;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListener implements Listener
{
    private final MobTalk plugin;
    private final HashSet<TalkingMob> set;
    
    public EventListener(MobTalk instance, HashSet<TalkingMob> set)
    {
        plugin = instance;
        this.set = set;
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
        List<Player> list = (plugin.getPlayersNear(ent));
        if(list==null) //If there are no players in range, do nothing
            return;
        for(Player target : list)
        {
            if(target.hasPermission("mobtalk.hear"))
                target.sendMessage(message);
        }
    }
    
    /**
     * </p>Checks if an entity is a baby.</p>
     * 
     * @param ent The entity to check
     * @return Whether or not the entity is a baby
     */
    public boolean isBaby(Entity ent)
    {
        if(ent instanceof Ageable)
        {
            Ageable age = (Ageable)ent;
            return !age.isAdult();
        }
        else
            return false;
    }
    
    @EventHandler
    public void onEntityAttacked(EntityDamageByEntityEvent event)
    {
        if(MobTalk.ATTACKED_THRESH>Math.random())
        {
            Entity entity = event.getEntity();
            String message = "";
            for(TalkingMob tm : set)
            {
                if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == isBaby(entity))))
                {
                    message += "<" + tm.getTalkingMobType().getName() + "> ";
                    String chat = tm.getAttackedMessage();
                    if(chat==null)
                        return;
                    else
                        message += tm.getAttackedMessage();
                }
            }
            sendMessage(entity, message);
        }
    }
    
    @EventHandler
    public void onEntityKilled(EntityDeathEvent event)
    {
        if(MobTalk.KILLED_THRESH>Math.random())
        {
            Entity entity = event.getEntity();
            String message = "";
            for(TalkingMob tm : set)
            {
                if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == isBaby(entity))))
                {
                    message += "<" + tm.getTalkingMobType().getName() + "> ";
                    String chat = tm.getDeathMessage();
                    if(chat==null)
                        return;
                    else
                        message += tm.getDeathMessage();
                }
            }
            sendMessage(entity, message);
        }
    }
}
