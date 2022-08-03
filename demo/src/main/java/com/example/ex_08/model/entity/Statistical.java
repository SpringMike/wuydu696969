package com.example.ex_08.model.entity;


import com.example.ex_08.dto.StatisticalDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedNativeQuery(
        name = "getFeaturedStatistical",
        query = "call getStatistical()",
        resultSetMapping = "FeaturedStatistical"
)
@SqlResultSetMapping(
        name = "FeaturedStatistical",
        classes = {
                @ConstructorResult(
                        targetClass = StatisticalDTO.class,
                        columns = {
                                @ColumnResult(name="inventory_id", type = Integer.class),
                                @ColumnResult(name="quantity", type = Long.class),
                                @ColumnResult(name="date", type = Date.class),
                        }
                )
        }
)
public class Statistical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int inventory_id;
    private long quantity;
    private Timestamp date;
}
