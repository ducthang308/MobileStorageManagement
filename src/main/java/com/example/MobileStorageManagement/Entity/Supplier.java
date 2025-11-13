package com.example.MobileStorageManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID")
    private Integer supplierId;

    @Column(name = "SupplierName", nullable = false)
    private String supplierName;
}
