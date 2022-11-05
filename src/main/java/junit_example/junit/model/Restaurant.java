package junit_example.junit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {

    Long id;
    String name;
    String street;
    String city;

    public Restaurant(Long id, RestaurantDto dto) {
        this.id = id;
        this.name = dto.name;
        this.street = dto.street;
        this.city = dto.city;
    }
}
