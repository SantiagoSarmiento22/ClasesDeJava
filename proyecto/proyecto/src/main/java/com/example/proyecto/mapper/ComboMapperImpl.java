package com.example.proyecto.mapper;

import com.example.proyecto.model.ComboDTO;
import com.example.proyecto.model.entity.ComboEntity;
import com.example.proyecto.repository.ComboMapper;
import org.springframework.stereotype.Component;

@Component
public class ComboMapperImpl implements ComboMapper {

    @Override
    public ComboDTO comboConvertToDTO(ComboEntity comboEntity){
        return new ComboDTO(comboEntity.getFantasy_name(), comboEntity.getCategory(), comboEntity.getDescription(), comboEntity.getPrice(), comboEntity.getAvailable());
    }

    @Override
    public ComboEntity comboConvertToEntity(ComboDTO comboDTO) {
        ComboEntity comboEntity = new ComboEntity();
        comboEntity.setFantasy_name(comboDTO.getFantasy_name());
        comboEntity.setCategory(comboDTO.getCategory());
        comboEntity.setDescription(comboDTO.getDescription());
        comboEntity.setPrice(comboDTO.getPrice());
        comboEntity.setAvailable(comboDTO.getAvailable());
        return comboEntity;
    }

    }


