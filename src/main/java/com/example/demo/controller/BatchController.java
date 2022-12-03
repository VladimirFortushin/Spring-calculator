/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    private final PersonDAO personDAO;
    
    @Autowired
    public BatchController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    
    
    @GetMapping
    public String index(){
        
        return "/batch/index";
    }
    
    
    @GetMapping("/without")
    public String withoutBatch(){
        personDAO.testMultipleUpdate();
        
        return "redirect:/people";
    }
    
    @GetMapping("/with")
    public String withBatch(){
        personDAO.testBatchUpdate();
        
        return "redirect:/people";
    }
    
}
