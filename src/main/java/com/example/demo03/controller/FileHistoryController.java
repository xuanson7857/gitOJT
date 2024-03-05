package com.example.demo03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileHistoryController {
    @RequestMapping("/file-history")
    public String home () {
        return "fileHistory/index";
    }
}
