package com.example.proyecto.repository;

import com.example.proyecto.model.entity.ComboEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<ComboRepository, String> {


}
