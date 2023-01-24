package ch.noseryoung.dungeoncrawlerai.domain.balance;

import org.springframework.stereotype.Service;

/**
 * The service interface is used as a middleman,
 * between every service-method call and the actual service Implementation (@BalanceServiceImpl)
 */
public interface BalanceService{
    Void contributeData(BalanceEntity entity);
}
//todo update Class diagram