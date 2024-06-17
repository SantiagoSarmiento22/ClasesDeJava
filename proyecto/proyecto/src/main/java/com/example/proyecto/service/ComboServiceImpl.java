package com.example.proyecto.service;

import com.example.proyecto.mapper.ComboMapperImpl;
import com.example.proyecto.model.ComboDTO;
import com.example.proyecto.model.entity.ComboEntity;
import com.example.proyecto.repository.ComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    ComboMapperImpl mapper;
    @Autowired
    ComboRepository repository;

    @Override
    public ComboDTO createCombo(ComboDTO comboDTO) {
        ComboEntity comboEntity = mapper.comboConvertToEntity(comboDTO);
        ComboEntity savedCombo = repository.save(comboEntity);
        return mapper.comboConvertToDTO(savedCombo);

    }
}
