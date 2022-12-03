/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import org.springframework.ui.Model;
import com.example.demo.dao.PersonDAO;
import com.example.demo.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/people")
public class PeopleController {
    
    private final PersonDAO personDao;
    
    @Autowired
    public PeopleController(PersonDAO personDao){
        this.personDao = personDao;
    }
    
    
    //Получаем людей из DAO и передаем на отображение в представлении
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDao.index());
        return "/people/index";
    }
    
    
    //Получаем человека по id из DAO и передаем на отображение в представление
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDao.show(id));
        return "/people/show";
    }
    
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        
        return "/people/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, 
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){return "/people/new";}
        personDao.save(person);
        return "redirect:/people";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.show(id));
        return "/people/edit";
    }
    
    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, 
            BindingResult bindingResult, 
            @PathVariable int id){
        if(bindingResult.hasErrors()){return "/people/edit";}
        
        personDao.update(id, person);       
        return "redirect:/people";
    }
    
    @PostMapping("/{id}/remove")
    public String delete(@PathVariable int id){
        personDao.delete(id);
        
        return "redirect:/people";
    }
    
}
