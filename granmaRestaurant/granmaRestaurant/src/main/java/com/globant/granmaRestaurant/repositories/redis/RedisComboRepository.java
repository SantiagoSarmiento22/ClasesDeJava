package com.globant.granmaRestaurant.repositories.redis;

import com.globant.granmaRestaurant.model.entity.redis.ComboAttachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisComboRepository extends CrudRepository<ComboAttachment, String> {}
