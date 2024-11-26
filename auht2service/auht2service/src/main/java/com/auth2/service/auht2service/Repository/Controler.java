package com.auth2.service.auht2service.Repository;

import jakarta.ws.rs.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controler {
    @GetMapping("testhello")
    public String getHello(){
        return "this hello";
    }
}
