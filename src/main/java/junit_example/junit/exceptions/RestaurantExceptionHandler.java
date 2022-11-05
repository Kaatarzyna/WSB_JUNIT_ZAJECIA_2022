package junit_example.junit.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    ResponseEntity<Void> handleRestaurantNotFoundException() {
        return ResponseEntity.notFound().build();
    }


}
