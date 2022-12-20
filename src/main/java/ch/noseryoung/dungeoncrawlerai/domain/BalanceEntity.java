package ch.noseryoung.dungeoncrawlerai.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "playerData")
public class BalanceEntity {
    @Id
    private String id;

    @Field
    private double playersAttackDamage;

    @Field
    private double playersDefense;

    @Field
    private int stage;

    @Field
    private String roomId;

    @Field
    private String enemyId;

    @Field
    private double leftoverEnemyHealth;

    @Field
    private double leftoverPlayerHealth;

    @Field
    private boolean playerIsDead;

    @Field
    private int itemListLength;

    @Field
    private double enemyDamage;

    @Field
    private double enemyResistance;
    @Field
    private int defenseItemAmount;
    @Field
    private int attackItemAmount;
    @Field
    private boolean enemyHasDefenseMultiplier;
    @Field
    private double enemyDefenseMultiplier;
    @Field
    private boolean enemyHasBonusDefense;
    @Field
    private double enemyBonusDefense;

    public BalanceEntity(double playersAttackDamage, double playersDefense, int stage, String roomId, String enemyId, double leftoverEnemyHealth, double leftoverPlayerHealth, boolean playerIsDead, int itemListLength, double enemyDamage, double enemyResistance, int defenseItemAmount, int attackItemAmount, boolean enemyHasDefenseMultiplier, double enemyDefenseMultiplier, boolean enemyHasBonusDefense, double enemyBonusDefense) {
        this.playersAttackDamage = playersAttackDamage;
        this.playersDefense = playersDefense;
        this.stage = stage;
        this.roomId = roomId;
        this.enemyId = enemyId;
        this.leftoverEnemyHealth = leftoverEnemyHealth;
        this.leftoverPlayerHealth = leftoverPlayerHealth;
        this.playerIsDead = playerIsDead;
        this.itemListLength = itemListLength;
        this.enemyDamage = enemyDamage;
        this.enemyResistance = enemyResistance;
        this.defenseItemAmount = defenseItemAmount;
        this.attackItemAmount = attackItemAmount;
        this.enemyHasDefenseMultiplier = enemyHasDefenseMultiplier;
        this.enemyDefenseMultiplier = enemyDefenseMultiplier;
        this.enemyHasBonusDefense = enemyHasBonusDefense;
        this.enemyBonusDefense = enemyBonusDefense;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPlayersAttackDamage() {
        return playersAttackDamage;
    }

    public void setPlayersAttackDamage(double playersAttackDamage) {
        this.playersAttackDamage = playersAttackDamage;
    }

    public double getPlayersDefense() {
        return playersDefense;
    }

    public void setPlayersDefense(double playersDefense) {
        this.playersDefense = playersDefense;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(String enemyId) {
        this.enemyId = enemyId;
    }

    public double getLeftoverEnemyHealth() {
        return leftoverEnemyHealth;
    }

    public void setLeftoverEnemyHealth(double leftoverEnemyHealth) {
        this.leftoverEnemyHealth = leftoverEnemyHealth;
    }

    public double getLeftoverPlayerHealth() {
        return leftoverPlayerHealth;
    }

    public void setLeftoverPlayerHealth(double leftoverPlayerHealth) {
        this.leftoverPlayerHealth = leftoverPlayerHealth;
    }

    public boolean isPlayerIsDead() {
        return playerIsDead;
    }

    public void setPlayerIsDead(boolean playerIsDead) {
        this.playerIsDead = playerIsDead;
    }
}
