package ch.noseryoung.dungeoncrawlerai.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class BalanceServiceImpl implements BalanceService{

    private final BalanceRepository repository;

    @Autowired
    public BalanceServiceImpl (BalanceRepository repository){
        this.repository = repository;
    }

    @Override
    public Void contributeData(BalanceEntity entity) throws IllegalArgumentException{
        log.info("trying to save entity");
        try {
            repository.save(entity);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
        return null;
    }
}
