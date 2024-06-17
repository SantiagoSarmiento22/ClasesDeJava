package com.example.proyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "combo")
public class ComboEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_combo;
    @Column(name = "fantasy_name")
    private String fantasy_name;
    @Column(name = "category")
    private String category;
    @Column (name = "description")
    private String description;
    @Column (name = "price")
    private String price;
    @Column (name = "available")
    private String available;
}
