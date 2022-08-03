package com.example.ex_08.dao;

import com.example.ex_08.dto.QuantityDTO;
import com.example.ex_08.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@AllArgsConstructor
public class CategoryDAO {


    private final JdbcTemplate jdbcTemplate;


//    private final RowMapper<Category> rowMapper = (rs, rowNumber) ->{
//        Category category = new Category();
//        category.setId(rs.getInt(1));
//        category.setCodeCategory(rs.getString(2));
//        category.setName(rs.getString(3));
//        category.setDescription(rs.getString(4));
//        category.setDateCreated(rs.getTimestamp(5));
//        category.setDateEdited(rs.getTimestamp(6));
//        return category;
//    };
//    private final RowMapper<QuantityDTO> getQuantityEachCategory = (rs,rowNumber) ->{
//        QuantityDTO quantityDTO = new QuantityDTO();
//        quantityDTO.setQuantity(rs.getInt(2));
//        quantityDTO.setNameCategory(rs.getString(1));
//        return quantityDTO;
//    };


    public List<Category> findAll() {
        String sql ="select * from category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }


    public void create(Category category) {
        String sql = "INSERT INTO category (codeCategory, name, description, dateCreated, dateEdited)\n" +
                "VALUES (N'?', N'?', N'?', N'?', N'?')";
        int inserted = jdbcTemplate.update(sql,category.getCode(),category.getName(),category.getDescription(),category.getCreatedDate(),category.getModifiedDate());
        if (inserted == 1){
            log.info("New category has been added " + category.getName() );
        }
    }


    public Optional<Category> getById(long id) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", id);
        NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "select * from category where id =:id";
        Category category = jdbcTemplateObject.queryForObject(sql, in,new BeanPropertyRowMapper<>(Category.class));
        return Optional.ofNullable(category);
    }

    public List<QuantityDTO> getQuantityEachCategory(){
        return jdbcTemplate.query("call getSellEachCategory()",new BeanPropertyRowMapper<>(QuantityDTO.class));
    }

    public void update(Category category, long id) {
        String sql = "UPDATE ex03_sql.category\n" +
                "SET code         = ?,\n" +
                "    name         = ?,\n" +
                "    description  = ?,\n" +
                "WHERE id = ?;\n";
        int update = jdbcTemplate.update(sql,category.getCode(),category.getName(),category.getDescription(),id);
        if (update == 1){
            log.info("Category updated " + category.getName());
        }else {
            log.info("Category updated fail");

        }
    }

    //delete with PROCEDURE
    public void delete(long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("deleteCategory");
        SqlParameterSource in = new MapSqlParameterSource().addValue("cateId", id);
        try{
            simpleJdbcCall.execute(in);
            log.info("Delete successfully");
        }catch (Exception exception){
            log.info(exception);
        }
    }
}
