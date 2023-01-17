package ch.noseryoung.dungeoncrawlerai.domain.balance;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BalanceRepository extends MongoRepository<BalanceEntity, String> {

}
