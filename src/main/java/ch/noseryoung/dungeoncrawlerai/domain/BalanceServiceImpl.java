package ch.noseryoung.dungeoncrawlerai.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService{

    private final BalanceRepository repository;

    @Autowired
    public BalanceServiceImpl (BalanceRepository repository){
        this.repository = repository;
    }

    @Override
    public Void contributeData(BalanceEntity entity) throws IllegalArgumentException{
        try {
            repository.save(entity);
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException(e.getMessage());
        }
        return null;
    }
}
