package junit_example.junit.controller;

import junit_example.junit.model.Restaurant;
import junit_example.junit.model.RestaurantDto;
import junit_example.junit.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    final RestaurantService restaurantService;

    @GetMapping("{id}")
    Restaurant get(@PathVariable Long id) {
        return restaurantService.get(id);
    }

    @PostMapping
    Restaurant create(@Valid @RequestBody RestaurantDto dto) {
        return restaurantService.create(dto);
    }




}
