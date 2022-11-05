package junit_example.junit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantDto {

    @NotBlank
    String name;

    String street;

    String city;

}
