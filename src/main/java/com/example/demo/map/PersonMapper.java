package com.example.demo.map;

import com.example.demo.people.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class PersonMapper<P> implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
                rs.getInt("person_id"),
                rs.getString("name"),
                rs.getInt("year_of_birth")
        );
    }
}
