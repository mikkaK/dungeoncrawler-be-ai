package ch.noseryoung.dungeoncrawlerai;

import ch.noseryoung.dungeoncrawlerai.domain.balance.BalanceEntity;
import ch.noseryoung.dungeoncrawlerai.domain.balance.GenerationLogic;
import ch.noseryoung.dungeoncrawlerai.domain.enemy.EnemyEntityService;
import ch.noseryoung.dungeoncrawlerai.domain.enemy.Entity;
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

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetEntity() {
        GenerationLogic logic = new GenerationLogic(entityService);

        logic.calculateEnemyDeath(enemyDead);
    }

}
