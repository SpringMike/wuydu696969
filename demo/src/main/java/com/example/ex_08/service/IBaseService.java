package com.example.ex_08.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IBaseService<T> {

    Page<T> findAll(Pageable pageable);

    List<T> findAll();

    T create(T t, BindingResult bindingResult);

    T findById(int id);

    T update(T t,BindingResult bindingResult);

    void delete(int id);
}
