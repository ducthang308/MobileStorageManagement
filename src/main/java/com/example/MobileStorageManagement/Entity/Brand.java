package com.example.MobileStorageManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandID")
    private Integer brandId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Country")
    private String country;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
}
