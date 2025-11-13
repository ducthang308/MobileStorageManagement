package com.example.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDTO {
    private Integer brandId;
    private String name;
    private String country;
    private String description;
}