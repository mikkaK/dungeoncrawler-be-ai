package ch.noseryoung.dungeoncrawlerai.domain.balance;

import ch.noseryoung.dungeoncrawlerai.domain.enemy.EnemyEntityService;
import ch.noseryoung.dungeoncrawlerai.domain.enemy.Entity;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerationLogic {

    @Autowired
    private EnemyEntityService entityService;


    public void calculateEnemyDeath(BalanceEntity entity){
    }

    public void calculatePlayerDeath(BalanceEntity entity){
        Entity enemy = entityService.getEntityByName(entity.getEnemyId()).get();
        if (entity.getLeftoverEnemyHealth() > enemy.getHealth() / 2.0){ //health bigger than 50%
            healthAbove50(entity, enemy);
        }

        else if(entity.getLeftoverEnemyHealth() > enemy.getHealth() / 4.0 && entity.getLeftoverEnemyHealth() < enemy.getHealth() / 2.0){
            healthBetween25and50(entity, enemy);
        }

        else if(entity.getLeftoverEnemyHealth() > enemy.getHealth() / 10.0 && entity.getLeftoverEnemyHealth() < enemy.getHealth() / 4.0){
            healthBetween10and25(entity, enemy);
        }

        else if (entity.getLeftoverEnemyHealth() > enemy.getHealth() / 20.0 && entity.getLeftoverEnemyHealth() < enemy.getHealth() / 10.0){
            healthBetween5and10(entity, enemy);
        }
    }

    public void healthAbove50(BalanceEntity entity, Entity enemy){
        int newEnemyHealth = changeEnemyHealth(entity, enemy);
        int newEnemyDamage = changeEnemyDamage(entity, enemy);
        int newEnemyResistance = changeEnemyResistance(entity, enemy);

        /*
        enemy.setHealth(newEnemyHealth);
        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

         */

        entityService.updateEntity(enemy);
    }

    public void healthBetween25and50(BalanceEntity entity, Entity enemy){
        int newEnemyHealth = changeEnemyHealth(entity, enemy);
        int newEnemyResistance = changeEnemyResistance(entity, enemy);

        /*
        enemy.setHealth(newEnemyHealth);
        enemy.setResistance(newEnemyResistance);

         */

        entityService.updateEntity(enemy);
    }

    public void healthBetween10and25(BalanceEntity entity, Entity enemy){
        int newEnemyDamage = changeEnemyDamage(entity, enemy);
        int newEnemyResistance = changeEnemyResistance(entity, enemy);

        /*
        enemy.setDamage(newEnemyDamage);
        enemy.setResistance(newEnemyResistance);

         */

        entityService.updateEntity(enemy);
    }

    public void healthBetween5and10(BalanceEntity entity, Entity enemy){
        int newEnemyDamage = changeEnemyDamage(entity, enemy);

        enemy.setDamage(newEnemyDamage);

        entityService.updateEntity(enemy);
    }

    public int changeEnemyHealth(BalanceEntity entity, Entity enemy){
        Double playerBaseDamage = playerBaseDamage(entity);
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        int newEnemyHealth = (int) (enemy.getHealth() - (playerBaseDamage / leftoverEnemyHealthPer));
        System.out.println("Old enemy Health: " + enemy.getHealth());
        System.out.println("New enemy Health: " + newEnemyHealth);
        return newEnemyHealth;
    }

    public int changeEnemyDamage(BalanceEntity entity, Entity enemy){
        Double playerBaseDefence = playerBaseResistance(entity);
        int currentEnemyDamage = enemy.getDamage();
        Double leftoverEnemyHealthPer = (entity.getLeftoverEnemyHealth() / enemy.getHealth()) * 100;
        int newEnemyDamage = (int) (currentEnemyDamage - (playerBaseDefence / leftoverEnemyHealthPer));
        System.out.println("Old enemy Damage: " + enemy.getDamage());
        System.out.println("New enemy Damage: " + newEnemyDamage);
        return newEnemyDamage;
    }

    public int changeEnemyResistance(BalanceEntity entity, Entity enemy){
        Double playerBaseDamage = playerBaseDamage(entity);
        if (entity.isEnemyHasDefenseMultiplier()){
            int newResistance = (int) (enemy.getResistance() - (playerBaseDamage / entity.getEnemyDefenseMultiplier()));
            System.out.println("Old enemy resistance: " + enemy.getResistance());
            System.out.println("New enemy resistance: " + newResistance);
            return newResistance;
        }
        else if (entity.isEnemyHasBonusDefense()){
            int newResistance =  (int) (enemy.getResistance() - (playerBaseDamage / entity.getEnemyBonusDefense()));
            System.out.println("Old enemy resistance: " + enemy.getResistance());
            System.out.println("New enemy resistance: " + newResistance);
            return newResistance;
        }
        else {
            int newResistance = (int) (enemy.getResistance() - (entity.getEnemyResistance() / 10));
            System.out.println("Old enemy resistance: " + enemy.getResistance());
            System.out.println("New enemy resistance: " + newResistance);
            return newResistance;
        }
    }

    public Double playerBaseDamage(BalanceEntity entity){
        int attackItemAmount = entity.getAttackItemAmount();
        Double playerDamage = entity.getPlayersAttackDamage();
        return (playerDamage / attackItemAmount) / 1.2;
    }

    public Double playerBaseResistance(BalanceEntity entity){
        int defenceItemAmount = entity.getDefenseItemAmount();
        Double playerResistance = entity.getPlayersDefense();
        return (playerResistance / defenceItemAmount) / 3.1;
    }
}
