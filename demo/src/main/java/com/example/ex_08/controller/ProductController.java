package com.example.ex_08.controller;

import com.example.ex_08.model.entity.Product;
import com.example.ex_08.service.IBaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController<Product> {

    public ProductController(IBaseService<Product> baseService) {
        super(baseService);
    }
}
