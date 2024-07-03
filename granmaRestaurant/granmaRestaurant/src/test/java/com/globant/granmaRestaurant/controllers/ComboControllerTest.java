package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ComboControllerTest {

	@Mock
	private IComboService comboService;

	@InjectMocks
	private ComboController comboController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateCombo() {
		// Given
		final ComboDTO comboDTO = new ComboDTO();
		comboDTO.setFantasyName("Combo Pollito");
		comboDTO.setDescription("Broaster con papas");
		comboDTO.setPrice(18.99);
		comboDTO.setAvailable(true);
		comboDTO.setActive(true);

		final ComboDTO createdComboDTO = new ComboDTO();
		createdComboDTO.setFantasyName("Combo Hamburguesa");
		createdComboDTO.setDescription("Delicioso combo con hamburguesa y papas fritas");
		createdComboDTO.setPrice(15.94);
		createdComboDTO.setAvailable(true);
		createdComboDTO.setActive(true);
		when(this.comboService.createCombo(any(ComboDTO.class))).thenReturn(createdComboDTO);
		// When
		final ResponseEntity<ComboDTO> response = this.comboController.createCombo(comboDTO);
		// Then
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(createdComboDTO, response.getBody());
	}

	@Test
	public void testGetCombo() {
		// Given
		final String comboIdTest = "1";
		final ComboDTO createdComboDTO = new ComboDTO();
		createdComboDTO.setFantasyName("Combo Hamburguesa");
		createdComboDTO.setDescription("Delicioso combo con hamburguesa y papas fritas");
		createdComboDTO.setPrice(15.94);
		createdComboDTO.setAvailable(true);
		createdComboDTO.setActive(true);
		when(this.comboService.getCombo(comboIdTest)).thenReturn(createdComboDTO);
		// When
		final ResponseEntity<ComboDTO> combo = this.comboController.getCombo(comboIdTest);
		// Then
		assertEquals(HttpStatus.OK, combo.getStatusCode());
		assertEquals(createdComboDTO, combo.getBody());

	}

}

// Fast
// Independent
// Repeatable
// Self-Validating
// Timely