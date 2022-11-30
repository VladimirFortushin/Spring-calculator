/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class BasicController {
    
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "a", required = true) double a, 
            @RequestParam(value = "b", required = true) double b,
            @RequestParam(value = "action", required = true) String action,
            Model model){
        
        double answer;
        String action1;
        
        switch(action){
            case "addition" -> answer = a+b;
            case "subtraction" -> answer = a-b;
            case "multiplication" -> answer = a*b;
            case "division" -> answer = a/b;
            default -> answer = 0.0;
        };
        
        switch(action){
            case "addition" -> action1 = "+";
            case "subtraction" -> action1 = "-";
            case "multiplication" -> action1 = "*";
            case "division" -> action1 = "/";
            default -> action1 = "unknown action";
        };
        
        String result = String.format("Operation: %s %s %s = %s", a, action1, b, answer);
        System.out.println(result);
        model.addAttribute("answer",result);
     //   System.out.printf("Name: %s, lastname: %s\n", name, lastName);
   //     ModelAndView  model = new ModelAndView();
    //    model.setViewName("/first/hello");
     //   model.getModel().put("username", "Hello!");
        return "/first/hello";
    }
    
    @GetMapping("/goodbye")
    public ModelAndView sayGoodbye(){
        ModelAndView  model = new ModelAndView();
        model.setViewName("/first/goodbye");
        model.getModel().put("username", "Goodbye!");
        return model;
    }
    
    
}
