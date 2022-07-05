package com.darisamor.logosShop.controller;

import com.darisamor.logosShop.domain.TestDTO;
import com.darisamor.logosShop.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @PostMapping
//    @ApiOperation(
//            value = "some test post endpoint",
//            notes = "try new api operations options",
//            response = String.class
//    )
    public ResponseEntity<String> testPost(
//            @ApiParam(
//                    value = "body = testdto",
//                    required = true
//            )
            @RequestBody TestDTO dto){
        log.error("Test error");
        return ResponseEntity.ok(dto.toString());
    }
}