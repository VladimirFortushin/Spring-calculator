/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.models.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    List<Person> people;
    
    {
        people = new ArrayList<>();
        
        people.add(new Person(++PEOPLE_COUNT, "Alex", 20, "alex@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob",22, "bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Carl",25, "carl@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Dean",18, "dean@gmail.com"));
        
    }
    
    public List<Person> index(){
        return people;
        
    }
    
    public Person show(int id){
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);   
                
    }
    
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
       
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        
        people.removeIf(p -> p.getId() == id);
        
    }
    
    
}
