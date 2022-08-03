package com.example.ex_08.controller;

import com.example.ex_08.model.entity.Category;
import com.example.ex_08.service.IBaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController extends BaseController<Category> {

    public CategoryController(IBaseService<Category> baseService) {
        super(baseService);
    }
}
