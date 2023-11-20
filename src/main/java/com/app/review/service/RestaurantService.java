package com.app.review.service;

import com.app.review.api.request.CreateAndEditRestaurantRequest;
import com.app.review.model.MenuEntity;
import com.app.review.model.RestaurantEntity;
import com.app.review.repository.MenuRepository;
import com.app.review.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public RestaurantEntity createRestaurant(CreateAndEditRestaurantRequest request) {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build();
        restaurantRepository.save(restaurant);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updateAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

        return restaurant;
    }

    @Transactional
    public void editRestaurant(Long restaurantId, CreateAndEditRestaurantRequest request) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("없는 레스토랑입니다."));
        restaurant.changeNameAndAddress(request.getName(), restaurant.getAddress());
        restaurantRepository.save(restaurant);

        List<MenuEntity> menus =  menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantId)
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updateAt(ZonedDateTime.now())
                    .build();
            menuRepository.save(menuEntity);
        });

    }

    public void deleteRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("없는 레스토랑입니다."));
        restaurantRepository.delete(restaurant);

        List<MenuEntity> menues = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menues);
    }

}
