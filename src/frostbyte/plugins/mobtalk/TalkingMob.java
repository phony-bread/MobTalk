package frostbyte.plugins.mobtalk;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.entity.EntityType;

public class TalkingMob
{
    
    private static final Random random = new Random();
    
    private final EntityType mob;
    private final boolean baby;
    private final TalkingMobType tmt;
    
    private String randomPrev = "";
    private String spawnPrev = "";
    private String targetPrev = "";
    private String attackPrev = "";
    private String attackedPrev = "";
    private String deathPrev = "";
    private String interactPrev = "";
    private String summonPrev = "";
    private String playerDeathPrev = "";
    private String bornPrev = "";
    private String breedPrev = "";
    private String babyAttackedPrev = "";
    private String tamePrev = "";
    private String leashPrev = "";
    private String unleashPrev = "";
    
    private final ArrayList<String> randomMessages = new ArrayList<>();
    private final ArrayList<String> spawnMessages = new ArrayList<>();
    private final ArrayList<String> targetMessages = new ArrayList<>();
    private final ArrayList<String> attackMessages = new ArrayList<>();
    private final ArrayList<String> attackedMessages = new ArrayList<>();
    private final ArrayList<String> deathMessages = new ArrayList<>();
    private final ArrayList<String> interactMessages = new ArrayList<>();
    private final ArrayList<String> summonMessages = new ArrayList<>();
    private final ArrayList<String> fireMessages = new ArrayList<>();
    private final ArrayList<String> playerDeathMessages = new ArrayList<>();
    
    private final ArrayList<String> bornMessages = new ArrayList<>();
    private final ArrayList<String> breedMessages = new ArrayList<>();
    private final ArrayList<String> babyAttackedMessages = new ArrayList<>();
    private final ArrayList<String> tameMessages = new ArrayList<>();
    private final ArrayList<String> leashMessages = new ArrayList<>();
    private final ArrayList<String> unleashMessages = new ArrayList<>();
    
    
    private String name;
    
    public TalkingMob(EntityType mob, boolean baby, TalkingMobType tmt)
    {
        this.mob = mob;
        this.baby = baby;
        this.tmt = tmt;
    }
    
    /**
     * <p>Resets all of the message ArrayLists for re-population.</p>
     */
    public void clearMessages()
    {
        randomMessages.clear();
        spawnMessages.clear();
        targetMessages.clear();
        attackMessages.clear();
        attackedMessages.clear();
        deathMessages.clear();
        interactMessages.clear();
        bornMessages.clear();
        breedMessages.clear();
        summonMessages.clear();
        babyAttackedMessages.clear();
    }
    
    /**
     * <p>Returns the MobType of the TalkingMob.</p>
     * 
     * @return the MobType
     */
    public EntityType getMobType()
    {
        return mob;
    }
    
    /**
     * <p>Returns whether the TalkingMob is a child or not.</p>
     * 
     * @return true if child, false if not.
     */
    public boolean isBaby()
    {
        return baby;
    }
    
    /**
     * <p> Accessor for the TalkingMobType of this TalkingMob
     * @return TalkingMobType
     */
    public TalkingMobType getTalkingMobType()
    {
        return tmt;
    }
    
    /**
     * <p>Returns a random "random" message. This message is to be used
     * when a mob is idling.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getRandomMessage()
    {
        randomPrev = getMessage(randomPrev, randomMessages);
        return randomPrev;
    }
    
    /**
     * <p>Adds a "random" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addRandomMessage(String message)
    {
        randomMessages.add(message);
    }
    
    /**
     * <p>Returns a random "spawn" message. This message is to be used
     * when a mob spawns.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getSpawnMessage()
    {
        spawnPrev = getMessage(spawnPrev, spawnMessages);
        return spawnPrev;
    }
    
    /**
     * <p>Adds a "spawn" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addSpawnMessage(String message)
    {
        spawnMessages.add(message);
    }
    
    /**
     * <p>Returns a random "target" message. This message is to be used
     * when a mob gains a new target.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getTargetMessage()
    {
        targetPrev = getMessage(targetPrev, targetMessages);
        return targetPrev;
    }
    
    /**
     * <p>Adds a "target" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addTargetMessage(String message)
    {
        targetMessages.add(message);
    }
    
    /**
     * <p>Returns a random "attack" message. This message is to be used
     * when a mob attacks a target.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getAttackMessage()
    {
        attackPrev = getMessage(attackPrev, attackMessages);
        return attackPrev;
    }
    
    /**
     * <p>Adds an "attack" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addAttackMessage(String message)
    {
        attackMessages.add(message);
    }
    
    /**
     * <p>Returns a random "attacked" message. This message is to be used
     * when a mob is attacked.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getAttackedMessage()
    {
        attackedPrev = getMessage(attackedPrev, attackedMessages);
        return attackedPrev;
    }
    
    /**
     * <p>Adds an "attacked" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addAttackedMessage(String message)
    {
        attackedMessages.add(message);
    }
    
    /**
     * <p>Returns a random "death" message. This message is to be used
     * when a mob dies.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getDeathMessage()
    {
        deathPrev = getMessage(deathPrev, deathMessages);
        return deathPrev;
    }
    
    /**
     * <p>Adds a "death" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addDeathMessage(String message)
    {
        deathMessages.add(message);
    }
    
    /**
     * <p>Returns a random "interact" message. This message is to be used
     * when a mob is interacted with (right-clicked on).</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getInteractMessage()
    {
        interactPrev = getMessage(interactPrev, interactMessages);
        return interactPrev;
    }
    
    /**
     * <p>Adds an "interact" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addInteractMessage(String message)
    {
        interactMessages.add(message);
    }
    
    /**
     * <p>Returns a random "born" message. This message is to be used
     * when a baby mob is born.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getBornMessage()
    {
        bornPrev = getMessage(bornPrev, bornMessages);
        return bornPrev;
    }
    
    /**
     * <p>Adds a "born" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addBornMessage(String message)
    {
        bornMessages.add(message);
    }
    
    /**
     * <p>Returns a random "random" message. This message is to be used
     * when a mob breeds with another.</p>
     * 
     * @return Random message, or null if none are available.
     */
    public String getBreedMessage()
    {
        breedPrev = getMessage(breedPrev, breedMessages);
        return breedPrev;
    }
    
    /**
     * <p>Adds a "breed" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addBreedMessage(String message)
    {
        breedMessages.add(message);
    }
    
    /**
     * <p>Returns a random "summoned" message. This message is to be used
     * when a mob is summoned.</p>
     * 
     * @return Summon message, or null if none are available.
     */
    public String getSummonMessage()
    {
        summonPrev = getMessage(summonPrev, summonMessages);
        return summonPrev;
    }
    
    /**
     * <p>Adds a "summon" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addSummonMessage(String message)
    {
        summonMessages.add(message);
    }
    
    /**
     * <p>Returns a random "baby attacked" message. This message is used when a
     *  mob's child is attacked.</p>
     * 
     * @return Baby attacked message, or null if none are available.
     */
    public String getBabyAttackedMessage()
    {
        babyAttackedPrev = getMessage(babyAttackedPrev, babyAttackedMessages);
        return babyAttackedPrev;
    }
    
    /**
     * <p>Adds a "baby attacked" message to the TalkingMob.</p>
     * 
     * @param message The message to add
     */
    public void addBabyAttackedMessage(String message)
    {
        babyAttackedMessages.add(message);
    }
    
    public String getLeashMessage()
    {
        leashPrev = getMessage(leashPrev, leashMessages);
        return leashPrev;
    }
    
    public void addLeashMessage(String message)
    {
        leashMessages.add(message);
    }
    
    public String getUnleashMessage()
    {
        unleashPrev = getMessage(unleashPrev, unleashMessages);
        return unleashPrev;
    }
    
    public void addUnleashMessage(String message)
    {
        unleashMessages.add(message);
    }
    
    public String getTameMessage()
    {
        tamePrev = getMessage(tamePrev, tameMessages);
        return tamePrev;
    }
    
    public void addTameMessage(String message)
    {
        tameMessages.add(message);
    }
    
    public String getPlayerDeathMessage()
    {
        playerDeathPrev = getMessage(deathPrev, deathMessages);
        return playerDeathPrev;
    }
    
    public void addPlayerDeathMessage(String message)
    {
        playerDeathMessages.add(message);
    }
    
    public void setName(String newname)
    {
        name = newname;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getMessage(String prev, ArrayList<String> list)
    {
        String temp;
        if(list.isEmpty())
            return null;
        else
        {
            temp = list.get(random.nextInt(list.size()));
            while(temp.equals(prev))
                temp = list.get(random.nextInt(list.size()));
            return temp;
        }
    }
}
