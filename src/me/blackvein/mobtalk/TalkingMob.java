package me.blackvein.mobtalk;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.entity.EntityType;

public class TalkingMob
{
    
    private static final Random random = new Random();
    
    private final EntityType mob;
    private final boolean baby;
    private final TalkingMobType tmt;
    
    private final ArrayList<String> randomMessages = new ArrayList<>();
    private final ArrayList<String> spawnMessages = new ArrayList<>();
    private final ArrayList<String> targetMessages = new ArrayList<>();
    private final ArrayList<String> attackMessages = new ArrayList<>();
    private final ArrayList<String> attackedMessages = new ArrayList<>();
    private final ArrayList<String> deathMessages = new ArrayList<>();
    private final ArrayList<String> interactMessages = new ArrayList<>();
    
    private final ArrayList<String> bornMessages = new ArrayList<>();
    private final ArrayList<String> breedMessages = new ArrayList<>();
    
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
        if(randomMessages.isEmpty())
            return null;
        else
            return randomMessages.get(random.nextInt(randomMessages.size()));
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
        if(spawnMessages.isEmpty())
            return null;
        else
            return spawnMessages.get(random.nextInt(spawnMessages.size()));
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
        if(targetMessages.isEmpty())
            return null;
        else
            return targetMessages.get(random.nextInt(targetMessages.size()));
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
        if(attackMessages.isEmpty())
            return null;
        else
            return attackMessages.get(random.nextInt(attackMessages.size()));
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
        if(attackedMessages.isEmpty())
            return null;
        else
            return attackedMessages.get(random.nextInt(attackedMessages.size()));
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
        if(deathMessages.isEmpty())
            return null;
        else
            return deathMessages.get(random.nextInt(deathMessages.size()));
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
        if(interactMessages.isEmpty())
            return null;
        else
            return interactMessages.get(random.nextInt(interactMessages.size()));
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
        if(bornMessages.isEmpty())
            return null;
        else
            return bornMessages.get(random.nextInt(bornMessages.size()));
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
        if(breedMessages.isEmpty())
            return null;
        else
            return breedMessages.get(random.nextInt(breedMessages.size()));
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
    
}
