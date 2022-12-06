package ch.noseryoung.dungeoncrawlerai.domain;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface BalanceService {
    Void contributeData(BalanceEntity entity);
}
