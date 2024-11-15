package vision.grown.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExControllerAdvice {

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<ErrorResult> notEnoughMoneyExHandle(NotEnoughStockException e){
        log.info("NotEnoughMoneyException occurs! : {}",e);
        ErrorResult errorResult = new ErrorResult("NotEnoughMoney-EX",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResult> memberNotFoundExHandle(MemberNotFoundException e){
        log.info("MemberNotFoundException occurs! : {}",e);
        ErrorResult errorResult = new ErrorResult("MemberNotFound-EX",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
}
