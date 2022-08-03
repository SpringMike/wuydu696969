package com.example.ex_08.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "category")
public class Category extends BaseEntity {

    @NotEmpty(message = "Name can not be null")
    @Size(max = 20,message = "Name can not be more then 20 character")
    private String name;

    @Size(max = 45,message = "Description can not be more then 45 character")
    private String description;



}
