package frostbyte.plugins.mobtalk;

import java.util.HashSet;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

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
        if(plugin.isBaby(entity))
        {
            if(MobTalk.BABYATTACKED_THRESH>Math.random())
            {
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == true)))
                        {
                        List<Entity> entlist = entity.getNearbyEntities(30, 30, 30);
                        for(Entity e : entlist)
                        {
                            if(e.getType().equals(entity.getType()) && !plugin.isBaby(e))
                            {
                                for(TalkingMob tm2 : set)
                                {
                                    if((tm2.getMobType().equals(e.getType()) && (tm2.isBaby() == false)))
                                    {
                                        String message = "<" + tm2.getName() + "&F> ";
                                        String chat = tm2.getBabyAttackedMessage();
                                        if(chat==null)
                                            return;
                                        else
                                        {
                                            message += chat;
                                            plugin.sendMessage(e, message);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(MobTalk.ATTACKED_THRESH>Math.random())
        {
            String message = "";
            for(TalkingMob tm : set)
            {
                if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                {
                    message += "<" + tm.getName() + "&F> ";
                    String chat = tm.getAttackedMessage();
                    if(chat==null)
                        return;
                    else
                        message += tm.getAttackedMessage();
                }
            }
            plugin.sendMessage(entity, message);
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
                if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                {
                    message += "<" + tm.getName() + "&F> ";
                    String chat = tm.getDeathMessage();
                    if(chat==null)
                        return;
                    else
                        message += tm.getDeathMessage();
                }
            }
            plugin.sendMessage(entity, message);
        }
    }
    
    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Player)
            return;
        if(MobTalk.ATTACK_THRESH>Math.random())
        {
            Entity entity = event.getEntity();
            String message = "";
            if(entity instanceof LivingEntity)
            {
                LivingEntity entlive = (LivingEntity)entity;
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entlive.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getAttackMessage();
                        if(chat==null)
                            return;
                        else
                            message += tm.getAttackMessage();
                    }
                }
                plugin.sendMessage(event.getDamager(), message);
            }
        }
    }
    
    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event)
    {
        if(MobTalk.ATTACK_THRESH>Math.random())
        {
            if((event.getEntityType().equals(EntityType.CREEPER)))
            {
                String message = "";
                for(TalkingMob tm : set)
                {
                    if(tm.getTalkingMobType().getMobType().equals(EntityType.CREEPER))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getAttackMessage();
                        if(chat==null)
                            return;
                        else
                            message += tm.getAttackMessage();
                    }
                }
                plugin.sendMessage(event.getEntity(), message);
            }
        }
    }
    
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event)
    {
        if(MobTalk.INTERACT_THRESH>Math.random())
        {
            Entity entity = event.getRightClicked();
            String message = "";
            for(TalkingMob tm : set)
            {
                if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                {
                    message += "<" + tm.getName() + "&F> ";
                    String chat = tm.getInteractMessage();
                    if(chat==null)
                        return;
                    else
                        message += tm.getInteractMessage();
                }
            }
            plugin.sendMessage(entity, message);
        }
    }
    
    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event)
    {
        if(event.getSpawnReason().equals(SpawnReason.BREEDING))
        {
            Entity entity = event.getEntity();
            String message = "";
            if(MobTalk.BREED_THRESH>Math.random())
            {
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == false)))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getBreedMessage();
                        if(chat==null)
                            return;
                        else
                            message += tm.getBreedMessage();
                    }
                }
                plugin.sendMessage(entity, message);
            }
            if(MobTalk.BORN_THRESH>Math.random())
            {
                message = "";
                for(TalkingMob tmb : set)
                {
                    if((tmb.getMobType().equals(entity.getType()) && (tmb.isBaby() == true)))
                    {
                        message += "<" + tmb.getName() + "&F> ";
                        String chat = tmb.getBornMessage();
                        if(chat==null)
                            return;
                        else
                        {
                            message += chat;
                            plugin.sendMessage(entity, message);
                        }
                    }
                }
            }
        }
        else if((event.getSpawnReason().equals(SpawnReason.BUILD_IRONGOLEM))||
                (event.getSpawnReason().equals(SpawnReason.BUILD_SNOWMAN))||
                (event.getSpawnReason().equals(SpawnReason.EGG))||
                (event.getSpawnReason().equals(SpawnReason.BUILD_WITHER))||
                (event.getSpawnReason().equals(SpawnReason.SPAWNER_EGG)))
        {
            if(MobTalk.SUMMONED_THRESH>Math.random())
            {
                Entity entity = event.getEntity();
                String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getSummonMessage();
                        if(chat==null)
                            return;
                        else
                            message += tm.getSummonMessage();
                    }
                }
                plugin.sendMessage(entity, message);
            }
        }
        else if(event.getSpawnReason().equals(SpawnReason.SLIME_SPLIT))
        {
        }
        else
        {
            if(MobTalk.SUMMONED_THRESH>Math.random())
            {
                Entity entity = event.getEntity();
                String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getSummonMessage();
                                if(chat==null)
                                    return;
                                else
                                    message += tm.getSummonMessage();
                    }
                }
                plugin.sendMessage(entity, message);
            }
        }
    }  
}
