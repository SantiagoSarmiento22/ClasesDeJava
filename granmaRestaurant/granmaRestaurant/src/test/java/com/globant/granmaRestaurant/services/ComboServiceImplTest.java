package com.globant.granmaRestaurant.services;

import com.globant.granmaRestaurant.controllers.ComboController;
import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.mapper.ComboMapperImpl;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.repositories.ComboRepository;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import com.globant.granmaRestaurant.services.validators.ComboValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ComboServiceImplTest {

	@Mock
	private ComboMapperImpl mapper;

	@Mock
	private ComboRepository comboRepository;

	@Mock
	private ComboValidator comboValidator;

	@InjectMocks
	private ComboServiceImpl service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void deleteComboTest(){
		//Given
		final String comboIdTest = "1";
		when(this.comboRepository.findByUuid(comboIdTest)).thenReturn(Optional.empty());
		//When
		assertThrows(CustomException.class, () -> this.service.deleteCombo(comboIdTest));
	}

	@Test
	public void deleteComboTestWhenRecordExists(){
		//Given
		final String comboIdTest = "1";
		when(this.comboRepository.findByUuid(comboIdTest)).thenReturn(Optional.of(new ComboEntity()));
		//When
		this.service.deleteCombo(comboIdTest);
		//Then
		verify(this.comboRepository).deleteByUuid(comboIdTest);
	}



	@Test
	public void updateComboWhenRecordExists(){
		//Given
		final String comboIdTest = "1";
		final ComboDTO comboDTO = new ComboDTO();
		comboDTO.setFantasyName("Combo Pollito");
		comboDTO.setDescription("Broaster con papas");
		comboDTO.setPrice(18.99);
		comboDTO.setAvailable(true);
		comboDTO.setActive(true);
		when(this.comboRepository.findByUuid(comboIdTest)).thenReturn(Optional.of(new ComboEntity()));
		//When
		this.service.updateCombo(comboIdTest, comboDTO);
		//Then
		verify(this.comboRepository).save(any(ComboEntity.class));
	}


	@Test
	public void getComboWhenRecordExists(){
		//Given
		final String comboIdTest = "1";
		final ComboEntity comboEntity = new ComboEntity();
		comboEntity.setFantasyName("Combo Elementos");
		comboEntity.setDescription("Broaster con papas extra");
		comboEntity.setPrice(18.990);
		comboEntity.setAvailable(true);
		comboEntity.setActive(true);
		when(this.comboRepository.findByUuid(comboIdTest)).thenReturn(Optional.of(comboEntity));
		when(this.mapper.comboConvertToDTO(comboEntity)).thenCallRealMethod();
		//When
		final ComboDTO combo = this.service.getCombo(comboIdTest);
		//Then
		assertNotNull(combo);
		assertEquals(comboEntity.getFantasyName(), combo.getFantasyName());
	}


}
