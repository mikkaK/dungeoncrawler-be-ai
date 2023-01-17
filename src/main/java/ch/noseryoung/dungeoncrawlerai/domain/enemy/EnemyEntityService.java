package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EnemyEntityService {
    List<Entity> getAllEntities();

    Optional<Entity> getEntityByName(String id);

    void updateEntity(Entity entity);
}
