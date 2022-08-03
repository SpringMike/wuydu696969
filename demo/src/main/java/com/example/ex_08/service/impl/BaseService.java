package com.example.ex_08.service.impl;

import com.example.ex_08.model.entity.BaseEntity;
import com.example.ex_08.repo.IBaseRepo;
import com.example.ex_08.service.IBaseService;
import com.example.ex_08.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> implements IBaseService<T> {

    private final IBaseRepo<T, Integer> baseRepo;

    private final Utils utils;


    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepo.findAll(pageable);
    }

    @Override
    public List<T> findAll() {
        return baseRepo.findAll();
    }

    @Override
    public T create(T request, BindingResult bindingResult) {
        var t = baseRepo.findByCode(request.getCode());
        if (t != null) {
            log.info("code already there");
            throw new IllegalArgumentException("code has already exits");
        } else {
            if (bindingResult.hasErrors()) {
                throw utils.invalidInputException(bindingResult);
            } else {
                t = baseRepo.save(request);
            }
        }
        return t;
    }

    @Override
    public T findById(int id) {
        return baseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
    }

    @Override
    public T update(T request, BindingResult bindingResult) {
        var t = baseRepo.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException(("id not found: " + request.getId())));
        BindingResult result = utils.getListResult(bindingResult,request);
        if (result.hasErrors()) {
            throw utils.invalidInputException(result);
        } else {
            request.setCreatedDate(t.getCreatedDate());
            request.setCode(t.getCode());
            return baseRepo.save(request);
        }
    }

    @Override
    public void delete(int id) {
        baseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id)));
        baseRepo.deleteById(id);
    }
}
