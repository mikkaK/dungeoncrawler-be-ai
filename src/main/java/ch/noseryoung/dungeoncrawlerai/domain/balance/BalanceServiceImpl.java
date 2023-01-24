package ch.noseryoung.dungeoncrawlerai.domain.balance;

import ch.noseryoung.dungeoncrawlerai.domain.enemy.EnemyEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This Class is used to handle logic which happens between the controller and the repository.
 */
@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository repository;

    private final EnemyEntityService entityService;

    @Autowired
    public BalanceServiceImpl(BalanceRepository repository, EnemyEntityService entityService){
        this.repository = repository;
        this.entityService = entityService;
    }

    /**
     * This is the initial logic method of a balancing process, it then calls the Generation logic Class.
     * @param entity
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Void contributeData(BalanceEntity entity) throws IllegalArgumentException{
        log.info("trying to save entity");
        try {
            GenerationLogic logic = new GenerationLogic(entityService);
            repository.save(entity);
            if (entity.isPlayerIsDead()){
                logic.calculatePlayerDeath(entity);
            }
            else if (!entity.isPlayerIsDead()){
                logic.calculateEnemyDeath(entity);
            }
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
        return null;
    }
}
