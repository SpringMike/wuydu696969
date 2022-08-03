package com.example.ex_08.repo;

import com.example.ex_08.model.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ICategoryRepo extends IBaseRepo<Category,Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "call deleteCategory(?)")
    void deleteById(int id);
}
