package ch.noseryoung.dungeoncrawlerai;

import ch.noseryoung.dungeoncrawlerai.domain.balance.BalanceEntity;
import ch.noseryoung.dungeoncrawlerai.domain.balance.GenerationLogic;
import ch.noseryoung.dungeoncrawlerai.domain.enemy.EnemyEntityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DungeoncrawlerAiApplicationTests {

    @Autowired
    EnemyEntityService entityService;

    private BalanceEntity enemyDead = new BalanceEntity(
            42,
            70,
            0,
            "RoomFloppy",
            "Floppy Diskette",
            0,
            177,
            false,
            2,
            47,
            0,
            1,
            1,
            false,
            0,
            false,
            0
    );

    private BalanceEntity playerDead = new BalanceEntity(
            42,
            70,
            0,
            "RoomFloppy",
            "Floppy Diskette",
            142,
            0,
            true,
            2,
            47,
            6,
            1,
            1,
            false,
            0,
            false,
            0
    );

    private BalanceEntity enemyResistance = new BalanceEntity(
            42,
            70,
            0,
            "RoomGlobi",
            "Globi",
            142,
            0,
            true,
            2,
            47,
            6,
            1,
            1,
            false,
            0,
            false,
            0
    );

    @AfterEach
    public void resetTestObjects(){
        enemyDead = new BalanceEntity(
                42,
                70,
                0,
                "RoomFloppy",
                "Floppy Diskette",
                0,
                177,
                false,
                2,
                47,
                0,
                1,
                1,
                false,
                0,
                false,
                0
        );

        playerDead = new BalanceEntity(
                42,
                70,
                0,
                "RoomFloppy",
                "Floppy Diskette",
                142,
                0,
                true,
                2,
                47,
                6,
                1,
                1,
                false,
                0,
                false,
                0
        );
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testEnemyDeadEntity() {
        GenerationLogic logic = new GenerationLogic(entityService);

        logic.calculateEnemyDeath(enemyDead);
    }

    @Test
    public void testPlayerDeadEntity(){
        GenerationLogic logic = new GenerationLogic(entityService);

        logic.calculatePlayerDeath(playerDead);
    }


    @Test
    public void testEnemyDeadClose(){
        GenerationLogic logic = new GenerationLogic(entityService);

        enemyDead.setLeftoverPlayerHealth(6);
        enemyDead.setPlayersAttackDamage(330);
        logic.calculateEnemyDeath(enemyDead);
    }

    @Test
    public void testPlayerDeadClose(){
        GenerationLogic logic = new GenerationLogic(entityService);

        playerDead.setEnemyDamage(120);
        playerDead.setLeftoverEnemyHealth(6);
        logic.calculatePlayerDeath(enemyDead);
    }

    @Test
    public void testEnemyDeadExtreme(){
        GenerationLogic logic = new GenerationLogic(entityService);

        enemyDead.setLeftoverPlayerHealth(590);
        enemyDead.setPlayersAttackDamage(330);
        logic.calculateEnemyDeath(enemyDead);
    }

    @Test
    public void testPlayerDeadExtreme(){
        GenerationLogic logic = new GenerationLogic(entityService);

        playerDead.setLeftoverEnemyHealth(260);
        logic.calculatePlayerDeath(playerDead);
    }

    @Test
    public void testEnemyDeadOnlyDamage(){
        GenerationLogic logic = new GenerationLogic(entityService);

        enemyDead.setLeftoverPlayerHealth(40);
        enemyDead.setPlayersAttackDamage(50);
        enemyDead.setPlayersAttackDamage(120);
        logic.calculateEnemyDeath(enemyDead);
    }

    @Test
    public void testEnemyResistance(){
        GenerationLogic logic = new GenerationLogic(entityService);

        enemyResistance.setLeftoverPlayerHealth(40);
        enemyResistance.setPlayersAttackDamage(50);
        enemyResistance.setPlayersAttackDamage(120);
        logic.calculatePlayerDeath(enemyResistance);
    }
}
