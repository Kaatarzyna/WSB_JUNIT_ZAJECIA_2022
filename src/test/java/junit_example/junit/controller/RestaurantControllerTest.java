package junit_example.junit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit_example.junit.exceptions.RestaurantNotFoundException;
import junit_example.junit.model.Restaurant;
import junit_example.junit.model.RestaurantDto;
import junit_example.junit.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RestaurantService restaurantService;


    @Test
    void get_whenRestaurantFound_thenReturnRestaurant() throws Exception {
        // arrange
        Restaurant restaurant = new Restaurant(1L, "Nazwa", "Ulica", "Miasto");

        Mockito.when(restaurantService.get(1L))
                .thenReturn(restaurant);

        String expectedJson = objectMapper.writeValueAsString(restaurant);

        // act
        mockMvc.perform(get("/restaurants/1"))
                // assert
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void get_whenRestaurantNotFound_thenReturn404() throws Exception {
        // arrange
        Mockito.when(restaurantService.get(1L))
                .thenThrow(new RestaurantNotFoundException());

        // act
        mockMvc.perform(get("/restaurants/1"))
                // assert
                .andExpect(status().isNotFound());
    }

    @Test
    void create() throws Exception {
        // arrange
        RestaurantDto dto = new RestaurantDto("Nazwa", "Ulica", "Miasto");
        Restaurant restaurant = new Restaurant(1L, "Nazwa", "Ulica", "Miasto");

        Mockito.when(restaurantService.create(dto))
                .thenReturn(restaurant);

        // act
        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    void create_withBlank_throwsException() throws Exception {
        // arrange
        RestaurantDto dto = new RestaurantDto("", "Ulica", "Miasto");
        Restaurant restaurant = new Restaurant(1L, "Nazwa", "Ulica", "Miasto");

        Mockito.when(restaurantService.create(dto))
                .thenReturn(restaurant);

        // act
        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                //assert
                .andExpect(status().isBadRequest());
    }
}