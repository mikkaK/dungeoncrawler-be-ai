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
    private final EnemyEntityService entityService;

    public GenerationLogic(EnemyEntityService entityService) {
        this.entityService = entityService;
    }


    public void calculateEnemyDeath(BalanceEntity entity) {
        Entity enemy = entityService.getEntityByName(entity.getEnemyId()).get();
        log.info("Old enemy Stats: " + enemy);
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
        log.info("Old enemy Stats: " + enemy);
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
        Double newEnemyHealth = changeEnemyHealthUp(entity, enemy);
        Double newEnemyDamage = changeEnemyDamageUp(entity, enemy);
        Double newEnemyResistance = changeEnemyResistanceUp(entity, enemy);


        enemy.setHealth(newEnemyHealth);
        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthAbove50(BalanceEntity entity, Entity enemy) {
        Double newEnemyHealth = changeEnemyHealth(entity, enemy);
        Double newEnemyDamage = changeEnemyDamage(entity, enemy);
        Double newEnemyResistance = changeEnemyResistance(entity, enemy);

        enemy.setHealth(newEnemyHealth);
        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void playerHealthBetween25and50(BalanceEntity entity, Entity enemy) {
        Double newEnemyHealth = changeEnemyHealthUp(entity, enemy);
        Double newEnemyResistance = changeEnemyResistanceUp(entity, enemy);

        enemy.setHealth(newEnemyHealth);
        enemy.setResistance(newEnemyResistance);


        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthBetween25and50(BalanceEntity entity, Entity enemy) {
        Double newEnemyHealth = changeEnemyHealth(entity, enemy);
        Double newEnemyResistance = changeEnemyResistance(entity, enemy);

        enemy.setHealth(newEnemyHealth);
        enemy.setResistance(newEnemyResistance);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void playerHealthBetween10and25(BalanceEntity entity, Entity enemy) {
        Double newEnemyDamage = changeEnemyDamageUp(entity, enemy);
        Double newEnemyResistance = changeEnemyResistanceUp(entity, enemy);

        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthBetween10and25(BalanceEntity entity, Entity enemy) {
        Double newEnemyDamage = changeEnemyDamage(entity, enemy);
        Double newEnemyResistance = changeEnemyResistance(entity, enemy);

        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void playerHealthBetween5and10(BalanceEntity entity, Entity enemy) {
        Double newEnemyDamage = changeEnemyDamageUp(entity, enemy);

        enemy.setDamage(newEnemyDamage);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public void healthBetween5and10(BalanceEntity entity, Entity enemy) {
        Double newEnemyDamage = changeEnemyDamage(entity, enemy);

        enemy.setDamage(newEnemyDamage);

        log.info("New enemy Stats: " + enemy);
        /*
        entityService.updateEntity(enemy);

         */
    }

    public Double changeEnemyHealthUp(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        Double leftoverPlayerHealthPer = (entity.getLeftoverPlayerHealth() / 600) * 100;
        Double newEnemyHealth = Double.valueOf(Math.round(10 * (enemy.getHealth() + (playerBaseDamage / leftoverPlayerHealthPer)))/10);
        log.info("Old enemy Health: " + enemy.getHealth());
        log.info("New enemy Health: " + newEnemyHealth);
        return newEnemyHealth;
    }

    public Double changeEnemyHealth(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        Double newEnemyHealth =  Double.valueOf(Math.round(10 * (enemy.getHealth() - (playerBaseDamage / leftoverEnemyHealthPer)))/10);
        log.info("Old enemy Health: " + enemy.getHealth());
        log.info("New enemy Health: " + newEnemyHealth);
        return newEnemyHealth;
    }

    public Double changeEnemyDamageUp(BalanceEntity entity, Entity enemy) {
        Double playerBaseDefence = playerBaseResistance(entity);
        Double currentEnemyDamage = enemy.getDamage();
        Double leftoverPlayerHealthPer = (entity.getLeftoverPlayerHealth() / 600) * 100;
        Double newEnemyDamage = Double.valueOf(Math.round(10 * (currentEnemyDamage + (playerBaseDefence / leftoverPlayerHealthPer)))/10);
        log.info("Old enemy Damage: " + enemy.getDamage());
        log.info("New enemy Damage: " + newEnemyDamage);
        return newEnemyDamage;
    }

    public Double changeEnemyDamage(BalanceEntity entity, Entity enemy) {
        Double playerBaseDefence = playerBaseResistance(entity);
        Double currentEnemyDamage = enemy.getDamage();
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        Double newEnemyDamage = Double.valueOf(Math.round(10 * (currentEnemyDamage - (playerBaseDefence / leftoverEnemyHealthPer)))/10);
        log.info("Old enemy Damage: " + enemy.getDamage());
        log.info("New enemy Damage: " + newEnemyDamage);
        return newEnemyDamage;
    }

    public Double changeEnemyResistanceUp(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        if (entity.isEnemyHasDefenseMultiplier()) {
            Double newResistance = Double.valueOf(Math.round(10 * (enemy.getResistance() + (playerBaseDamage / entity.getEnemyDefenseMultiplier())))/10);
            if (newResistance < 0){
                newResistance = 0.0;
            }
            log.info("Old enemy resistance: " + enemy.getResistance());
            log.info("New enemy resistance: " + newResistance);
            return newResistance;
        } else if (entity.isEnemyHasBonusDefense()) {
            Double newResistance = Double.valueOf(Math.round(10 * (enemy.getResistance() + (playerBaseDamage / entity.getEnemyBonusDefense())))/10);
            if (newResistance < 0){
                newResistance = 0.0;
            }
            log.info("Old enemy resistance: " + enemy.getResistance());
            log.info("New enemy resistance: " + newResistance);
            return newResistance;
        } else {
            Double newResistance = Double.valueOf(Math.round(10 * (enemy.getResistance() + (entity.getEnemyResistance() / 10)))/10);
            if (newResistance < 0){
                newResistance = 0.0;
            }
            log.info("Old enemy resistance: " + enemy.getResistance());
            log.info("New enemy resistance: " + newResistance);
            return newResistance;
        }
    }

    public Double changeEnemyResistance(BalanceEntity entity, Entity enemy) {
        Double playerBaseDamage = playerBaseDamage(entity);
        if (entity.isEnemyHasDefenseMultiplier()) {
            Double newResistance = Double.valueOf(Math.round(10 * (enemy.getResistance() - (playerBaseDamage / entity.getEnemyDefenseMultiplier())))/10);
            if (newResistance < 0){
                newResistance = 0.0;
            }
            log.info("Old enemy resistance: " + enemy.getResistance());
            log.info("New enemy resistance: " + newResistance);
            return newResistance;
        } else if (entity.isEnemyHasBonusDefense()) {
            Double newResistance = Double.valueOf(Math.round(10 * (enemy.getResistance() - (playerBaseDamage / entity.getEnemyBonusDefense())))/10);
            if (newResistance < 0){
                newResistance = 0.0;
            }
            log.info("Old enemy resistance: " + enemy.getResistance());
            log.info("New enemy resistance: " + newResistance);
            return newResistance;
        } else {
            Double newResistance = Double.valueOf(Math.round(10 * (enemy.getResistance() - (entity.getEnemyResistance() / 10))) / 10);
            if (newResistance < 0){
                newResistance = 0.0;
            }
            log.info("Old enemy resistance: " + enemy.getResistance());
            log.info("New enemy resistance: " + newResistance);
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
