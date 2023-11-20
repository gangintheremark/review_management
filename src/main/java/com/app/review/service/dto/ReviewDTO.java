package com.app.review.service.dto;

import com.app.review.model.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Double avgScore;
    private List<ReviewEntity> reviews;
    private ReviewDTOPage page;

    @Getter
    @Builder
    public static class ReviewDTOPage {
        private Integer offset;
        private Integer limit;
    }
}
