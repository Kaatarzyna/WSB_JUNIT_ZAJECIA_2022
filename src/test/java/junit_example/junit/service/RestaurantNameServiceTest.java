package junit_example.junit.service;

import junit_example.junit.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantNameServiceTest {

    @InjectMocks
    RestaurantNameService restaurantNameService;

    Restaurant restaurant;

    @BeforeEach
    void setup() {
        restaurant = new Restaurant(1L, "Nazwa", "Ulica", "Miasto");
    }

    @Test
    void getReversedName() {
        // arrange

        // act
        String result = restaurantNameService.getReversedName(restaurant);

        // assert
        assertEquals("awzaN", result, "Nazwa restauracji nie została poprawnie odwrócona!!!!!111oneonoen");

    }

    @Test
    void getFormattedAddress() {
        // arrange

        // act
        String result = restaurantNameService.getFormattedAddress(restaurant);

        // assert
        assertEquals("Nazwa - Ulica, Miasto", result);
    }

    public static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new Restaurant(1L, "Moja", "Wiśniowa 8", "Sopot"), "ajoM"),
                Arguments.of(new Restaurant(1L, "Twoja", "Malinowa 8", "Gdynia"), "ajowT"),
                Arguments.of(new Restaurant(1L, "Nasza", "Truskawkowa 5", "Gdańsk"), "azsaN")
        );
    }


    @ParameterizedTest
    @MethodSource("provider")
    void getReversedName_parametrized(Restaurant restaurant, String expected) {
        // arrange

        // act
        String result = restaurantNameService.getReversedName(restaurant);

        // assert
        assertEquals(expected, result, "Nazwa restauracji nie została poprawnie odwrócona!!!!!111oneonoen");
    }

    @Disabled
    @DisplayName("BARDZO WAŻNY TEST")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 8})
    void isOdd(int number) {
        assertEquals(1, number % 2);
    }

}