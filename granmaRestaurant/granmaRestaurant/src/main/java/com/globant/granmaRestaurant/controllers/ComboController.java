package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.controllers.IControllerEndpoints.IComboPath;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IComboPath.URL_BASE)
public class ComboController {

	@Autowired
	private IComboService comboService;

	@PostMapping
	ResponseEntity<ComboDTO> createCombo(@RequestBody final ComboDTO comboDTO) {
		final ComboDTO createCombo = this.comboService.createCombo(comboDTO);
		return new ResponseEntity<>(createCombo, HttpStatus.CREATED);
	}
	@GetMapping(IComboPath.UUID)
	ResponseEntity<ComboDTO> getCombo(@PathVariable final String uuid) {
		final ComboDTO getComboUuid = this.comboService.getCombo(uuid);
		return new ResponseEntity<>(getComboUuid, HttpStatus.OK);
	}
	@PutMapping(IComboPath.UUID)
	ResponseEntity<Void> updateCombo(@PathVariable final String uuid, @RequestBody final ComboDTO comboDTO) {
		this.comboService.updateCombo(uuid, comboDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping(IComboPath.UUID)
	ResponseEntity<Void> comboDelete(@PathVariable final String uuid) {
		this.comboService.deleteCombo(uuid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
