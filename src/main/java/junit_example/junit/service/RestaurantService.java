package junit_example.junit.service;

import junit_example.junit.exceptions.RestaurantNotFoundException;
import junit_example.junit.model.Restaurant;
import junit_example.junit.model.RestaurantDto;
import junit_example.junit.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant get(Long id) {
        return restaurantRepository
                .get(id)
                .orElseThrow(RestaurantNotFoundException::new);
    }

    public Restaurant create(RestaurantDto dto) {
        return restaurantRepository.create(dto);
    }

}
