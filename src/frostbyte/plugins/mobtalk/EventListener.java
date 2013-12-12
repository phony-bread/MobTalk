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
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventListener implements Listener
{
    private final MobTalk plugin;
    private final HashSet<TalkingMob> set;
    private int slimeCount = 0;
    
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
            if(MobTalk.SUMMONED_THRESH>Math.random())
            {
                Entity entity = event.getEntity();
                String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = plugin.getMessage(MobTalk.slimeSplit);
                        if(chat==null)
                            return;
                        else
                            message += chat;
                    }
                }
                                if(slimeCount<3)
                    slimeCount++;
                else
                {
                    slimeCount = 0;
                    plugin.sendMessage(entity, message);
                }
            }
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
    
    @EventHandler
    public void onCreeperCharge(CreeperPowerEvent event)
    {
        Entity entity = event.getEntity();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = plugin.getMessage(MobTalk.creeperPower);
                                if(chat==null)
                                    return;
                                else
                                    message += chat;
                    }
                }
                plugin.sendMessage(entity, message);
    }
    
    @EventHandler
    public void onPigZap(PigZapEvent event)
    {
        Entity entity = event.getEntity();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(EntityType.PIG_ZOMBIE) && (tm.isBaby() == false)))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = plugin.getMessage(MobTalk.pigZap);
                                if(chat==null)
                                    return;
                                else
                                    message += chat;
                    }
                }
                plugin.sendMessage(entity, message);
    }
    
    @EventHandler
    public void onSheepWoolDye(SheepDyeWoolEvent event)
    {
        Entity entity = event.getEntity();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = plugin.getMessage(MobTalk.sheepWoolDye);
                                if(chat==null)
                                    return;
                                else
                                    message += chat;
                    }
                }
                plugin.sendMessage(entity, message);
    }
    
    @EventHandler
    public void fishCatch(PlayerFishEvent event)
    {
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(EntityType.FISHING_HOOK) && (tm.isBaby() == false)))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = plugin.getMessage(MobTalk.playerFish);
                                if(chat==null)
                                    return;
                                else
                                    message += chat;
                    }
                }
                if(event.getCaught()!=null)
                    plugin.sendMessage(event.getCaught(), message);
    }
    
    @EventHandler
    public void onDoorBreak(EntityBreakDoorEvent event)
    {
        Entity entity = event.getEntity();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = plugin.getMessage(MobTalk.zombieDoorBreak);
                                if(chat==null)
                                    return;
                                else
                                    message += chat;
                    }
                }
                plugin.sendMessage(entity, message);
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        boolean didDo = false;
        Entity entity = event.getEntity();
        Player player = (Player)entity;
        List<Entity> mobList = entity.getNearbyEntities(plugin.getRadius(), plugin.getRadius(), plugin.getRadius());
        for(Entity foundEnt : mobList)
        {
            if(MobTalk.RANDOM_THRESH>Math.random())
            {
                for(TalkingMob tm : set)
                {
                    if(tm.getMobType().equals(foundEnt.getType()) && tm.isBaby() == plugin.isBaby(foundEnt))
                    {
                        String message = "<" + tm.getName() + "&F> ";
                        String chat = tm.getPlayerDeathMessage();
                        if(chat!=null)
                        {
                            chat = chat.replaceAll("%player%", player.getName());
                            message += chat;
                            didDo = !didDo;
                            if (didDo)
                                plugin.sendMessage(foundEnt, message);
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler 
    public void onMobTame(EntityTameEvent event)
    {
        Entity entity = event.getEntity();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getTameMessage();
                            if(chat==null)
                                return;
                            else
                            {
                                chat = chat.replaceAll("%player%", event.getOwner().getName());
                                message += chat;
                            }
                    }
                }
                plugin.sendMessage(entity, message);
    }
    
    @EventHandler
    public void onEntityLeash(PlayerLeashEntityEvent event)
    {
        Entity entity = event.getEntity();
        Player player = (Player)event.getLeashHolder();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getLeashMessage();
                            if(chat==null)
                                return;
                            else
                            {
                                chat = chat.replaceAll("%player%", player.getName());
                                message += chat;
                            }
                    }
                }
                plugin.sendMessage(entity, message);
    }
    
    @EventHandler
    public void onEntityUnleash(EntityUnleashEvent event)
    {
        Entity entity = event.getEntity();
        String message = "";
                for(TalkingMob tm : set)
                {
                    if((tm.getMobType().equals(entity.getType()) && (tm.isBaby() == plugin.isBaby(entity))))
                    {
                        message += "<" + tm.getName() + "&F> ";
                        String chat = tm.getUnleashMessage();
                            if(chat==null)
                                return;
                            else
                                message += chat;
                    }
                }
                plugin.sendMessage(entity, message);
    }
}
