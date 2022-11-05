package junit_example.junit.service;

import junit_example.junit.exceptions.RestaurantNotFoundException;
import junit_example.junit.model.Restaurant;
import junit_example.junit.model.RestaurantDto;
import junit_example.junit.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Test
    void get_whenExists_thenReturnsRestaurant() {
        // arrange
        Restaurant expected = new Restaurant(1L, "Nazwa", "Ulica", "Miasto");

        Mockito.when(restaurantRepository.get(1L))
                .thenReturn(Optional.of(expected));

        // act
        Restaurant actual = restaurantService.get(1L);

        // assert
        assertEquals(actual, expected);
    }

    @Test
    void get_whenNotExist_thenThrowsRestaurantNotFoundException() {
        // arrange
        Mockito.when(restaurantRepository.get(1L))
                .thenReturn(Optional.empty());

        // act
        Executable executable = () -> restaurantService.get(1L);

        // assert
        assertThrows(RestaurantNotFoundException.class, executable);
    }

    @Test
    void create() {
        //arrange
        RestaurantDto dto = new RestaurantDto("Nazwa", "Ulica", "Miasto");
        Restaurant restaurant = new Restaurant(1L, "Nazwa", "Ulica", "Miasto");

        Mockito.when(restaurantRepository.create(dto))
                .thenReturn(restaurant);

        //act
        Restaurant result = restaurantService.create(dto);

        //assert
        verify(restaurantRepository, times(1)).create(dto);
        assertEquals(restaurant, result);
    }
}