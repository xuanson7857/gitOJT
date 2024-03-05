package com.example.demo03.controller;

import com.example.demo03.service.ProductService.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/product")
    public String home (Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }


    @GetMapping("/export")
    public void exportExcel (HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product.xls";

        response.setHeader(headerKey, headerValue);
        productService.generateExcel(response);
    }
    @PostMapping("/uploadProductFile")
    public String upload(@RequestParam("productFile")MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")){
            try {
                file.transferTo(new File("/Users/son/Desktop/OJT/Demo03/src/main/resources/uploads/" + fileName));
                productService.saveCustomerData(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/product";
    }


}
