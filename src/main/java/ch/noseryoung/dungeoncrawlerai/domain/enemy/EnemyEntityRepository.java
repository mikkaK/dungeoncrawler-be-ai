package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * This repository is used to handle the persistence of an enemy object in the database.
 */
public interface EnemyEntityRepository extends MongoRepository<Entity, String> {
    Optional<Entity> findByName(String s);
}
