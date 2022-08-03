package com.example.ex_08.repo;

import com.example.ex_08.model.entity.Statistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatisticalRepo extends JpaRepository<Statistical,Integer> {
}
