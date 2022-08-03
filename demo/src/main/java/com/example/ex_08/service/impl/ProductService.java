package com.example.ex_08.service.impl;

import com.example.ex_08.model.entity.Product;
import com.example.ex_08.repo.IBaseRepo;
import com.example.ex_08.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductService extends BaseService<Product> {

    public ProductService(IBaseRepo<Product, Integer> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }
}
