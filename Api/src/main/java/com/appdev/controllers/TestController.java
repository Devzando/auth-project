package com.appdev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TestController {
    public ResponseEntity ping(){
        return ResponseEntity.ok().build();
    }
}
