package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is the actual implementation of the EnemyEntity Service.
 * It handles the logic between a controller and repository.
 */
@Service
public class EnemyEntityServiceImpl implements EnemyEntityService{

    private final EnemyEntityRepository entityRepository;

    public EnemyEntityServiceImpl(EnemyEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public List<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    @Override
    public Optional<Entity> getEntityByName(String name) {
        return entityRepository.findByName(name);
    }

    @Override
    public void updateEntity(Entity entity) {
        entityRepository.save(entity);
    }
}
