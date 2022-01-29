package com.bs.productservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {
    @GetMapping("")
    public String test(){
        log.info("product-service");
        return "product-service update";
    }
}
