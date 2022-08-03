package com.example.ex_08.repo;

import com.example.ex_08.model.entity.Inventory;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryRepo extends IBaseRepo<Inventory,Integer> {

}
