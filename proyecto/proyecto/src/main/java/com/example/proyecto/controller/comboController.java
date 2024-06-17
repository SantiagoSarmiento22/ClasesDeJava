package com.example.proyecto.controller;

import com.example.proyecto.model.ComboDTO;
import com.example.proyecto.repository.ComboRepository;
import com.example.proyecto.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combo")
public class comboController {

    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboRepository comboRepository;

    @PostMapping("/createCombo")
    ResponseEntity<ComboDTO> createCombo (@RequestBody ComboDTO comboDTO){
        ComboDTO createCombo = comboService.createCombo(comboDTO);
        return new ResponseEntity<>(createCombo, HttpStatus.CREATED);
    }
}
