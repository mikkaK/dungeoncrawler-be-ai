package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EnemyEntityRepository extends MongoRepository<Entity, String> {
    Optional<Entity> findByName(String s);
}
