package com.harsh.jobapp.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {


//    @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!";
    }

//  @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @PostMapping("/hello")
    public String hello(@RequestBody String name) {
        return "Hello " + name + "!";
    }
}
