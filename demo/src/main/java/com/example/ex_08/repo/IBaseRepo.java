package com.example.ex_08.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public interface IBaseRepo<T,Id extends Serializable> extends JpaRepository<T,Id> {

    T findByCode(String code);

}
