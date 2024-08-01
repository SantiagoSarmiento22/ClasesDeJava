package com.globant.granmaRestaurant.services;

import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import com.globant.granmaRestaurant.mapper.ComboMapperImpl;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.model.entity.redis.ComboAttachment;
import com.globant.granmaRestaurant.repositories.ComboRepository;
import com.globant.granmaRestaurant.repositories.redis.RedisComboRepository;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import com.globant.granmaRestaurant.services.validators.ComboValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ComboServiceImpl implements IComboService {

	@Autowired
	private ComboMapperImpl mapper;
	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private RedisComboRepository comboRepositoryRedis;
	@Autowired
	private ComboValidator comboValidator;

	@Override
	public ComboDTO createCombo(final ComboDTO comboDTO) {
		this.comboValidator.validateComboData(comboDTO);
		this.comboRepository.findByFantasyName(comboDTO.getFantasyName()).ifPresent(existingCombo -> {
			throw new CustomException(ExceptionCode.COMBO_ALREADY_EXISTS, LocalDateTime.now(), HttpStatus.CONFLICT,
					"Combo con nombre de fantasía: " + comboDTO.getFantasyName() + " ya existe.");
		});
		final ComboEntity comboEntity = this.mapper.comboConvertToEntity(comboDTO);
		final ComboEntity savedCombo = this.comboRepository.save(comboEntity);

		final ComboAttachment comboAttachment = new ComboAttachment(savedCombo.getUuid(), comboDTO.getBase64Image());
		this.comboRepositoryRedis.save(comboAttachment);
		return this.mapper.comboConvertToDTO(savedCombo);
	}

	@Override
	public ComboDTO getCombo(final String uuid) {
		final Optional<ComboEntity> comboPetition = this.comboRepository.findByUuid(uuid);
		final ComboDTO comboDTO = comboPetition.map(this.mapper::comboConvertToDTO)
				.orElseThrow(() -> new CustomException(ExceptionCode.COMBO_NOT_FOUND, LocalDateTime.now(),
						HttpStatus.NOT_FOUND, "Combo con UUID: " + uuid + " no encontrado."));
		comboDTO.setBase64Image(this.comboRepositoryRedis.findById(uuid).map(ComboAttachment::getBase64Image).orElse(null));
		return comboDTO;
	}

	@Override
	@Transactional
	public void updateCombo(final String uuid, final ComboDTO comboDTO) {
		this.comboValidator.validateComboData(comboDTO);
		final Optional<ComboEntity> existingComboOpt = this.comboRepository.findByUuid(uuid);
		if (existingComboOpt.isPresent()) {
			final ComboEntity existingCombo = existingComboOpt.get();
			existingCombo.setFantasyName(comboDTO.getFantasyName());
			existingCombo.setCategory(comboDTO.getCategory());
			existingCombo.setDescription(comboDTO.getDescription());
			existingCombo.setPrice(comboDTO.getPrice());
			existingCombo.setAvailable(comboDTO.getAvailable());
			existingCombo.setActive(comboDTO.getActive());
			this.comboRepository.save(existingCombo);
		} else {
			throw new CustomException(ExceptionCode.COMBO_NOT_FOUND, LocalDateTime.now(), HttpStatus.NOT_FOUND,
					"Combo con UUID: " + uuid + " no encontrado.");
		}
	}

	@Override
	@Transactional
	public void deleteCombo(final String uuid) {
		final Optional<ComboEntity> existingCombo = this.comboRepository.findByUuid(uuid);
		if (existingCombo.isPresent()) {
			this.comboRepository.deleteByUuid(uuid);
		} else {
			throw new CustomException(ExceptionCode.COMBO_NOT_FOUND, LocalDateTime.now(), HttpStatus.NOT_FOUND,
					"Combo con UUID: " + uuid + " no encontrado.");
		}
	}
}