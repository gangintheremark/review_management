package com.app.review.api;

import com.app.review.api.request.CreateAndEditRestaurantRequest;
import com.app.review.api.request.CreateAndEditRestaurantRequestMenu;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantAPI {
    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "This is getRestaurants";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(@PathVariable Long restaurantId) {
        return "This is getRestaurant, " + restaurantId;
    }

    @PostMapping("/restaurant")
    public String createRestaurant(@RequestBody CreateAndEditRestaurantRequest request) {
        return "This is createRestaurant. name=" + request.getName() + " address=" + request.getAddress() + " menus=" + request.getMenus().get(0).getName() + request.getMenus().get(0).getPrice();
    }

    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(@PathVariable Long restaurantId, @RequestBody CreateAndEditRestaurantRequest request) {
        return "This is editRestaurant, " + restaurantId + " name=" + request.getName() + " address=" + request.getAddress() + " menus=" + request.getMenus().get(0).getName() + request.getMenus().get(0).getPrice();
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(@PathVariable Long restaurantId) {
        return "This is deleteRestaurant, " + restaurantId;
    }
}
