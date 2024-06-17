package com.example.proyecto.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComboDTO {

    private String fantasy_name;
    private String category;
    private String description;
    private String price;
    private String available;

}
