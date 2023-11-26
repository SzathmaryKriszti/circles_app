package com.example.myfirstapp.controller;

import com.example.myfirstapp.dto.FormData;
import com.example.myfirstapp.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    private FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    public ResponseEntity<Void> saveFormData(@RequestBody FormData data){

        formService.saveData(data);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
