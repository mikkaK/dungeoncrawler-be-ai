package ch.noseryoung.dungeoncrawlerai.domain.balance;

import org.springframework.stereotype.Service;

@Service
public interface BalanceService{
    Void contributeData(BalanceEntity entity);
}
//todo update Class diagram