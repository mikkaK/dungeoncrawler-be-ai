package ch.noseryoung.dungeoncrawlerai.domain.balance;

import ch.noseryoung.dungeoncrawlerai.domain.enemy.EnemyEntityService;
import ch.noseryoung.dungeoncrawlerai.domain.enemy.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generation Logic is an extra class to handle the logic of balancing an enemy,
 * as this logic is to big for the @BalanceserviceImpl.
 */
@Slf4j
public class GenerationLogic {

    @Autowired
    private EnemyEntityService entityService;


    public void calculateEnemyDeath(BalanceEntity entity) {
        Entity enemy = entityService.getEntityByName(entity.getEnemyId()).get();
        log.debug("Old enemy Stats: " + enemy);
        if (entity.getLeftoverPlayerHealth() > 300) { //health bigger than 50%
            playerHealthAbove50(entity, enemy);
        } else if (entity.getLeftoverPlayerHealth() > 150 && entity.getLeftoverPlayerHealth() < 300) {
            playerHealthBetween25and50(entity, enemy);
        } else if (entity.getLeftoverPlayerHealth() > 60 && entity.getLeftoverPlayerHealth() < 150) {
            playerHealthBetween10and25(entity, enemy);
        } else if (entity.getLeftoverPlayerHealth() > 30 && entity.getLeftoverPlayerHealth() < 60) {
            playerHealthBetween5and10(entity, enemy);
        }
    }

    public void calculatePlayerDeath(BalanceEntity entity) {
        Entity enemy = entityService.getEntityByName(entity.getEnemyId()).get();
        log.debug("Old enemy Stats: " + enemy);
        if (entity.getLeftoverEnemyHealth() > enemy.getHealth() / 2.0) { //health bigger than 50%
            healthAbove50(entity, enemy);
        } else if (entity.getLeftoverEnemyHealth() > enemy.getHealth() / 4.0 && entity.getLeftoverEnemyHealth() < enemy.getHealth() / 2.0) {
            healthBetween25and50(entity, enemy);
        } else if (entity.getLeftoverEnemyHealth() > enemy.getHealth() / 10.0 && entity.getLeftoverEnemyHealth() < enemy.getHealth() / 4.0) {
            healthBetween10and25(entity, enemy);
        } else if (entity.getLeftoverEnemyHealth() > enemy.getHealth() / 20.0 && entity.getLeftoverEnemyHealth() < enemy.getHealth() / 10.0) {
            healthBetween5and10(entity, enemy);
        }
    }

    public void playerHealthAbove50(BalanceEntity entity, Entity enemy) {
        int newEnemyHealth = changeEnemyHealthUp(entity, enemy);
        int newEnemyDamage = changeEnemyDamageUp(entity, enemy);
        int newEnemyResistance = changeEnemyResistanceUp(entity, enemy);


        enemy.setHealth(newEnemyHealth);
        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthAbove50(BalanceEntity entity, Entity enemy) {
        int newEnemyHealth = changeEnemyHealth(entity, enemy);
        int newEnemyDamage = changeEnemyDamage(entity, enemy);
        int newEnemyResistance = changeEnemyResistance(entity, enemy);

        enemy.setHealth(newEnemyHealth);
        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void playerHealthBetween25and50(BalanceEntity entity, Entity enemy) {
        int newEnemyHealth = changeEnemyHealthUp(entity, enemy);
        int newEnemyResistance = changeEnemyResistanceUp(entity, enemy);

        enemy.setHealth(newEnemyHealth);
        enemy.setResistance(newEnemyResistance);


        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthBetween25and50(BalanceEntity entity, Entity enemy) {
        int newEnemyHealth = changeEnemyHealth(entity, enemy);
        int newEnemyResistance = changeEnemyResistance(entity, enemy);

        enemy.setHealth(newEnemyHealth);
        enemy.setResistance(newEnemyResistance);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void playerHealthBetween10and25(BalanceEntity entity, Entity enemy) {
        int newEnemyDamage = changeEnemyDamageUp(entity, enemy);
        int newEnemyResistance = changeEnemyResistanceUp(entity, enemy);

        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthBetween10and25(BalanceEntity entity, Entity enemy) {
        int newEnemyDamage = changeEnemyDamage(entity, enemy);
        int newEnemyResistance = changeEnemyResistance(entity, enemy);

        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void playerHealthBetween5and10(BalanceEntity entity, Entity enemy) {
        int newEnemyDamage = changeEnemyDamageUp(entity, enemy);

        enemy.setDamage(newEnemyDamage);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthBetween5and10(BalanceEntity entity, Entity enemy) {
        int newEnemyDamage = changeEnemyDamage(entity, enemy);

        enemy.setDamage(newEnemyDamage);

        log.debug("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public int changeEnemyHealthUp(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        int newEnemyHealth = (int) (enemy.getHealth() + (playerBaseDamage / leftoverEnemyHealthPer));
        log.debug("Old enemy Health: " + enemy.getHealth());
        log.debug("New enemy Health: " + newEnemyHealth);
        return newEnemyHealth;
    }

    public int changeEnemyHealth(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        int newEnemyHealth = (int) (enemy.getHealth() - (playerBaseDamage / leftoverEnemyHealthPer));
        log.debug("Old enemy Health: " + enemy.getHealth());
        log.debug("New enemy Health: " + newEnemyHealth);
        return newEnemyHealth;
    }

    public int changeEnemyDamageUp(BalanceEntity entity, Entity enemy) {
        Double playerBaseDefence = playerBaseResistance(entity);
        int currentEnemyDamage = enemy.getDamage();
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        int newEnemyDamage = (int) (currentEnemyDamage + (playerBaseDefence / leftoverEnemyHealthPer));
        log.debug("Old enemy Damage: " + enemy.getDamage());
        log.debug("New enemy Damage: " + newEnemyDamage);
        return newEnemyDamage;
    }

    public int changeEnemyDamage(BalanceEntity entity, Entity enemy) {
        Double playerBaseDefence = playerBaseResistance(entity);
        int currentEnemyDamage = enemy.getDamage();
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        int newEnemyDamage = (int) (currentEnemyDamage - (playerBaseDefence / leftoverEnemyHealthPer));
        log.debug("Old enemy Damage: " + enemy.getDamage());
        log.debug("New enemy Damage: " + newEnemyDamage);
        return newEnemyDamage;
    }

    public int changeEnemyResistanceUp(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        if (entity.isEnemyHasDefenseMultiplier()) {
            int newResistance = (int) (enemy.getResistance() + (playerBaseDamage / entity.getEnemyDefenseMultiplier()));
            log.debug("Old enemy resistance: " + enemy.getResistance());
            log.debug("New enemy resistance: " + newResistance);
            return newResistance;
        } else if (entity.isEnemyHasBonusDefense()) {
            int newResistance = (int) (enemy.getResistance() + (playerBaseDamage / entity.getEnemyBonusDefense()));
            log.debug("Old enemy resistance: " + enemy.getResistance());
            log.debug("New enemy resistance: " + newResistance);
            return newResistance;
        } else {
            int newResistance = (int) (enemy.getResistance() + (entity.getEnemyResistance() / 10));
            log.debug("Old enemy resistance: " + enemy.getResistance());
            log.debug("New enemy resistance: " + newResistance);
            return newResistance;
        }
    }

    public int changeEnemyResistance(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        if (entity.isEnemyHasDefenseMultiplier()) {
            int newResistance = (int) (enemy.getResistance() - (playerBaseDamage / entity.getEnemyDefenseMultiplier()));
            log.debug("Old enemy resistance: " + enemy.getResistance());
            log.debug("New enemy resistance: " + newResistance);
            return newResistance;
        } else if (entity.isEnemyHasBonusDefense()) {
            int newResistance = (int) (enemy.getResistance() - (playerBaseDamage / entity.getEnemyBonusDefense()));
            log.debug("Old enemy resistance: " + enemy.getResistance());
            log.debug("New enemy resistance: " + newResistance);
            return newResistance;
        } else {
            int newResistance = (int) (enemy.getResistance() - (entity.getEnemyResistance() / 10));
            log.debug("Old enemy resistance: " + enemy.getResistance());
            log.debug("New enemy resistance: " + newResistance);
            return newResistance;
        }
    }

    public Double playerBaseDamage(BalanceEntity entity) {
        int attackItemAmount = entity.getAttackItemAmount();
        Double playerDamage = entity.getPlayersAttackDamage();
        return (playerDamage / attackItemAmount) / 1.2;
    }

    public Double playerBaseResistance(BalanceEntity entity) {
        int defenceItemAmount = entity.getDefenseItemAmount();
        Double playerResistance = entity.getPlayersDefense();
        return (playerResistance / defenceItemAmount) / 3.1;
    }
}
