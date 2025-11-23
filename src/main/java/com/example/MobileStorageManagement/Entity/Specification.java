package com.example.MobileStorageManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Specification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SpecID")
    private Integer specId;

    @Column(name = "Screen")
    private String screen;

    @Column(name = "CPU")
    private String cpu;

    @Column(name = "RAM")
    private String ram;

    @Column(name = "Storage")
    private String storage;

    @Column(name = "Camera")
    private String camera;

    @Column(name = "Battery")
    private String battery;

    @Column(name = "OS")
    private String os;
}
