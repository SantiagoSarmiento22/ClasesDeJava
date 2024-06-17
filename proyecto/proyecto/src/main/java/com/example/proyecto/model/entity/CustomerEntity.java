package com.example.proyecto.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_customer;
    @Column (name = "document")
    private String document;
    @Column(name = "name")
    private String name;
    @Column (name = "email")
    private String email;
    @Column (name = "phone")
    private String phone;
    @Column (name = "delivery_address")
    private String delivery_address;
}