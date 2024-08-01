package com.globant.granmaRestaurant.model.DTO;

import com.globant.granmaRestaurant.model.enums.Category;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ComboDTO {


    private String uuid;
    private String fantasyName;
    private Category category;
    private String description;
    private Double price;
    private Boolean available;
    private Boolean active;
    @With
    private String base64Image;


}
