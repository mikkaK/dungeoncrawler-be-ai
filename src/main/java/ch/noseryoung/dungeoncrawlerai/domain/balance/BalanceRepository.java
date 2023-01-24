package ch.noseryoung.dungeoncrawlerai.domain.balance;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface handles the persisting into our database,
 * this is achieved by extending the MongoRepository Class.
 */
public interface BalanceRepository extends MongoRepository<BalanceEntity, String> {

}
