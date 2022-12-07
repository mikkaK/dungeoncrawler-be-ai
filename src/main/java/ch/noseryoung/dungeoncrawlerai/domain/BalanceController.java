package ch.noseryoung.dungeoncrawlerai.domain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
@CrossOrigin(origins = "*")
@Slf4j
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }
    @Transactional
    @PostMapping("/contribute")
    public ResponseEntity<Void> contributeData(@RequestBody BalanceEntity entity){
        balanceService.contributeData(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> IllegalArgumentExceptionHandler(Exception e){
        return new ResponseEntity<>("Please Contact a developer \n message: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
