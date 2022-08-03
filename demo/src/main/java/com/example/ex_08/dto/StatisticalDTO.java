package com.example.ex_08.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class StatisticalDTO implements Serializable {
    private int inventory_id;
    private long quantity;
    private Date date;

}
