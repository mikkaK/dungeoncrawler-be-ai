package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import java.util.List;
import java.util.Optional;

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
