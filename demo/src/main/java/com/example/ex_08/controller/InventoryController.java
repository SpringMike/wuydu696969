package com.example.ex_08.controller;

import com.example.ex_08.model.entity.Inventory;
import com.example.ex_08.service.IBaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController extends BaseController<Inventory>{

    public InventoryController(IBaseService<Inventory> baseService) {
        super(baseService);
    }
}
