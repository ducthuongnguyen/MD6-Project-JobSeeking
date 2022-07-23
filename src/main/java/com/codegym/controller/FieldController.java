package com.codegym.controller;

import com.codegym.model.entity.Field;
import com.codegym.service.field.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fields")
@CrossOrigin("*")
public class FieldController {
    @Autowired
    private IFieldService fieldService;

    @GetMapping()
    public ResponseEntity<Iterable<Field>> findAllField() {
        Iterable<Field> fields = fieldService.findAll();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }
}
