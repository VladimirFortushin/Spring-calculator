/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.models.Person;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class PersonDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    public List<Person> index(){
        
        return jdbcTemplate.query("SELECT * FROM information_schema.mytable", 
                new BeanPropertyRowMapper<>(Person.class));
    }
    
    public Person show(int id){
         return jdbcTemplate.query("SELECT * FROM information_schema.mytable WHERE id=?", 
                 new Object[]{id}, 
                 new BeanPropertyRowMapper<>(Person.class))
         .stream().findAny().orElse(null);       
    }
    
    public void save(Person person){
        jdbcTemplate.update("INSERT INTO information_schema.mytable VALUES (1, ?, ?, ?)", 
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE information_schema.mytable SET name=?, age=?, email=? WHERE id=?", 
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(),id);        
    }

    public void delete(int id) {
       jdbcTemplate.update("DELETE FROM information_schema.mytable WHERE id=?",id);        
    
    }

    public void testMultipleUpdate() {
        List<Person> people = create1000People();
        
        long before = System.currentTimeMillis();
        
        for(Person person : people){
            jdbcTemplate.update("INSERT INTO information_schema.mytable VALUES (?, ?, ?, ?)", 
                    person.getId(),person.getName(), person.getAge(), person.getEmail());
        }
        
        long after = System.currentTimeMillis();
        
        System.out.println("Time: " + (after - before));
        
    }
    
    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
            people.add(new Person(i, "name" + i, 10+i, "email" + i + "@gmail.com"));
        }
        
        return people;
    }

    public void testBatchUpdate() {
        List<Person> people = create1000People();
        
        long before = System.currentTimeMillis();
        
        for(Person person : people){
            jdbcTemplate.batchUpdate("INSERT INTO information_schema.mytable VALUES (?, ?, ?, ?)", 
                    new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, people.get(i).getId());    
                    ps.setString(2, people.get(i).getName());
                    ps.setInt(3, people.get(i).getAge()); 
                    ps.setString(4, people.get(i).getEmail());
                }

                @Override
                public int getBatchSize() {
                   return people.size();
                }
            });
        }
        
        long after = System.currentTimeMillis();
        
        System.out.println("Time (Batch): " + (after - before));
    }

    
    
    
}
