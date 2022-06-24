package com.darisamor.logosShop.controller;

import com.darisamor.logosShop.dto.TestDTO;
import com.darisamor.logosShop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getById(@PathVariable Long id){
//        return new ResponseEntity<TestEntity>(service.getEntity(id), HttpStatus.OK);
        return ResponseEntity.ok(service.getEntity(id));
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/byName")
    public ResponseEntity<TestDTO> getAll(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(service.findByName(name));
    }
}