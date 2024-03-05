package com.example.demo03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController {
    @RequestMapping("/store")
    public String home () {
        return "store/index";
    }
}
