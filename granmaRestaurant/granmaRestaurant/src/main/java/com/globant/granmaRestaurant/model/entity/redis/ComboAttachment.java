package com.globant.granmaRestaurant.model.entity.redis;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RedisHash("ComboAttachment")
public class ComboAttachment implements Serializable {
    @Id
    private String id;
    private String base64Image;

}



