package frostbyte.plugins.mobtalk;

import org.bukkit.entity.EntityType;

public enum TalkingMobType 
{
    //Baby Mobs
    BABY_CHICKEN(EntityType.CHICKEN, true, "Chick"),
    BABY_COW(EntityType.COW, true, "Calf"),
    BABY_OCELOT(EntityType.OCELOT, true, "Kitten"),
    BABY_PIG(EntityType.PIG, true, "Piglet"),
    BABY_SHEEP(EntityType.SHEEP, true, "Lamb"),
    BABY_VILLAGER(EntityType.VILLAGER, true, "Kid"),
    BABY_MOOSHROOM(EntityType.MUSHROOM_COW, true, "Mooshroom Calf"),
    BABY_HORSE(EntityType.HORSE, true, "Foal"),
    BABY_DOG(EntityType.WOLF, true, "Puppy"),
    BABY_ZOMBIE(EntityType.ZOMBIE, true, "Zombie Kid"),
    
    //Passive Mobs
    CHICKEN(EntityType.CHICKEN, false, "Chicken"),
    OCELOT(EntityType.OCELOT, false, "Ocelot"),
    PIG(EntityType.PIG, false, "Pig"),
    SHEEP(EntityType.SHEEP, false, "Sheep"),
    HORSE(EntityType.HORSE, false, "Horse"),
    SQUID(EntityType.SQUID, false, "Squid"),
    BAT(EntityType.BAT, false, "Bat"),
    VILLAGER(EntityType.VILLAGER, false, "Villager"),
    MOOSHROOM(EntityType.MUSHROOM_COW, false, "Mooshroom"),
    COW(EntityType.COW, false, "Cow"),
    
    //Neutral Mobs
    SPIDER(EntityType.SPIDER, false, "Spider"),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, false, "Cave Spider"),
    ENDERMAN(EntityType.ENDERMAN, false, "Enderman"),
    ZOMBIE_PIGMAN(EntityType.PIG_ZOMBIE, false, "Zombie Pigman"),
    DOG(EntityType.WOLF, false, "Wolf"),
    
    //Hostile Mobs
    CREEPER(EntityType.CREEPER, false, "Creeper"),
    GHAST(EntityType.GHAST, false, "Ghast"),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, false, "Magma Cube"),
    SILVERFISH(EntityType.SILVERFISH, false, "Silverfish"),
    SKELETON(EntityType.SKELETON, false, "Skeleton"),
    SLIME(EntityType.SLIME, false, "Slime"),
    WITCH(EntityType.WITCH, false, "Witch"),
    ZOMBIE(EntityType.ZOMBIE, false, "Zombie"),
    //WAITING FOR BUKKIT IMPLEMENTATION: WITHER_SKELETON(EntityType., false, "Wither Skeleton"),
    //WAITING FOR BUKKIT IMPLEMENTATION: SPIDER_JOCKEY(EntityType. false, "Spider Jockey")
    BLAZE(EntityType.BLAZE, false, "Blaze"),
    
    //Utility Mobs
    IRON_GOLEM(EntityType.IRON_GOLEM, false, "Iron Golem"),
    SNOW_GOLEM(EntityType.SNOWMAN, false, "Snow Golem"),
    
    //Boss Mobs
    ENDER_DRAGON(EntityType.ENDER_DRAGON, false, "Ender Dragon"),
    WITHER(EntityType.WITHER, false, "Wither"),
    
    //Unimplemented Mobs
    GIANT(EntityType.GIANT, false, "Giant");
    
    private final EntityType mobType;
    private final boolean baby;
    private final String canonName;
    
    TalkingMobType(EntityType et, boolean baby, String name)
    {
        this.mobType = et;
        this.baby = baby;
        this.canonName = name;
    }   

    public EntityType getMobType() 
    {
        return mobType;
    }

    public boolean isBaby() 
    {
        return baby;
    }
    
    public String getName()
    {
        return canonName;
    }
}
