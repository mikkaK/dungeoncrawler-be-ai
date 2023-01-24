package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import java.util.List;
import java.util.Optional;

/**
 * This service is used as a middleman between the service-method calls and the actual implementation.
 */
public interface EnemyEntityService {
    List<Entity> getAllEntities();

    Optional<Entity> getEntityByName(String id);

    void updateEntity(Entity entity);
}
