package com.example.proyecto.repository;

import com.example.proyecto.model.ComboDTO;
import com.example.proyecto.model.entity.ComboEntity;

public interface ComboMapper {
    ComboDTO comboConvertToDTO(ComboEntity comboEntity);

    ComboEntity comboConvertToEntity(ComboDTO comboDTO);
}
