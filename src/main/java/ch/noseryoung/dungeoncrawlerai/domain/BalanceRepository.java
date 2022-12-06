package ch.noseryoung.dungeoncrawlerai.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BalanceRepository extends MongoRepository<BalanceEntity, String> {

}
