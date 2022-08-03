package com.example.ex_08.model.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity{

    @NotNull(message = "Name can not be null")
    @Size(max = 45,message = "Name can not be more then 45 character")
    private String name;

    @Size(max = 45,message = "Desc can not be more then 45 character")
    private String description;

    @Size(max = 200,message = "Url can not be more then 200 character")
    private String url;

    @NotNull(message = "price can not be null")
    @Min(value = 1,message = "Price can not be less then 1")
    private BigDecimal price;

    @NotNull(message = "Quantity can not be null")
    @Min(value = 1,message = "Quantity can not be less then 1")
    private int quantity;

    @Min(value = 0,message = "Sold can not be less than 0")
    private Integer sold;


    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "inventory_id")
    private int inventoryId;

}
