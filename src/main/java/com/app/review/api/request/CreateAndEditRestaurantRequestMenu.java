package com.app.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CreateAndEditRestaurantRequestMenu {
    private final String name;
    private final Integer price;
}
