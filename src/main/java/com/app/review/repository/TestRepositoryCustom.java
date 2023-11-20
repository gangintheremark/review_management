package com.app.review.repository;

import com.app.review.model.TestEntity;

import java.util.List;

public interface TestRepositoryCustom {
    public List<TestEntity> findAllByNameByQuerydsl(String name);
}
