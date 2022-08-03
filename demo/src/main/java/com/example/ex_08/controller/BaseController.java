package com.example.ex_08.controller;

import com.example.ex_08.model.entity.BaseEntity;
import com.example.ex_08.service.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
public abstract class BaseController<T extends BaseEntity> {

    private final IBaseService<T> baseService;
    @GetMapping
    public Page<T> getPagination(@RequestParam(value = "pageNumber",required = true) int pageNumber,
                                          @RequestParam(value = "pageSize",required = true) int pageSize,
                                          @RequestParam(value = "sortBy",required = false) String sortBy,
                                          @RequestParam(value = "sortDir",required = false) String sortDir){
        if (sortDir!= null){
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            return baseService.findAll(PageRequest.of(pageNumber-1,pageSize,sort));
        }
        return baseService.findAll(PageRequest.of(pageNumber-1,pageSize));
    }


    @GetMapping("/findAll")
    public List<T> list() {
        return baseService.findAll();
    }

    @PostMapping
    public T create(@RequestBody @Valid T request, BindingResult bindingResult) {
        return baseService.create(request,bindingResult);
    }

    @PutMapping
    public T update(@RequestBody @Valid T entity,BindingResult bindingResult) {
        return baseService.update(entity,bindingResult);
    }

    @DeleteMapping
    public void delete(@PathVariable(value = "id") Integer id) {
        baseService.delete(id);
    }

}
