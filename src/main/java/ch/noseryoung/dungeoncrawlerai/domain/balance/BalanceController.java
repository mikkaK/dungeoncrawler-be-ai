package ch.noseryoung.dungeoncrawlerai.domain.balance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * This is the REST Controller which handles all requests to /balance
 * It consists of a constructor, a endpoint and an exceptionHandler
 */
@RestController
@RequestMapping("/balance")
@CrossOrigin(origins = "*")
@Slf4j
public class BalanceController {

    private final BalanceService balanceService;
    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    /**
     * This endpoint handles the contribution after a death or kill of a player
     * @param entity
     * @return HttpStatus.OK
     */
    @Transactional
    @PostMapping("/contribute")
    public ResponseEntity<Void> contributeData(@RequestBody BalanceEntity entity){
        log.info("Starting contribution");
        balanceService.contributeData(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This ExceptionHandler handles all exceptions which need to be returned to the frontend.
     * @param e
     * @return Https Response with specified error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> IllegalArgumentExceptionHandler(Exception e){
        return new ResponseEntity<>("Please Contact a developer \n message: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
