package com.example.MobileStorageManagement.DTO;

import lombok.Data;

@Data
public class CartDetailDTO {
    private Integer cartDetailId;
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
}
