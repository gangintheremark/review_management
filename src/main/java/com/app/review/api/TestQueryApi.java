package com.app.review.api;

import com.app.review.model.TestEntity;
import com.app.review.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestQueryApi {
    private final TestService testService;
    @GetMapping("/test/query")
    public List<TestEntity> queryJpa() {
        return testService.findAllByName("gang");
    }

    @GetMapping("/test/query/querydsl")
    public List<TestEntity> queryQuerydsl() {
        return testService.findAllByNameByQuerydsl("gang");
    }
}
