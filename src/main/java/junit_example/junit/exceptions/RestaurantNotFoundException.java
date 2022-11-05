package junit_example.junit.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException() {
        super("Nie odnaleziono restauracji");
    }
}
