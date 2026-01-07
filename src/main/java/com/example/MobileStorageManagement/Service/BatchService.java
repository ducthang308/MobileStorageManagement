package com.example.MobileStorageManagement.Service;

import com.example.MobileStorageManagement.DTO.BatchRequest;
import com.example.MobileStorageManagement.DTO.BatchResponse;
import com.example.MobileStorageManagement.DTO.ProductDTO;
import com.example.MobileStorageManagement.Entity.Batch;
import com.example.MobileStorageManagement.Entity.Product;
import com.example.MobileStorageManagement.Repository.BatchRepository;
import com.example.MobileStorageManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductRepository productRepository;

    /* =========================
       GET
       ========================= */

    public List<BatchResponse> getAll() {
        return batchRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BatchResponse getById(Long id) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lô hàng"));
        return toResponse(batch);
    }

    /* =========================
       CREATE
       ========================= */

    public BatchResponse create(BatchRequest batchRequest) {
        Batch batch = new Batch();

        batch.setQuantity(batchRequest.getQuantity());
        batch.setPriceIn(batchRequest.getPriceIn());
        batch.setExpiry(batchRequest.getExpiry());
        batch.setProductionDate(batchRequest.getProductionDate());

        if (batchRequest.getProductID() != null) {
            Product product = productRepository.findById(batchRequest.getProductID())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
            batch.setProduct(product);
        }

        return toResponse(batchRepository.save(batch));
    }

    /* =========================
       UPDATE
       ========================= */

    public BatchResponse update(Long id, BatchRequest batchRequest) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lô hàng"));

        if (batchRequest.getQuantity() != null)
            batch.setQuantity(batchRequest.getQuantity());

        if (batchRequest.getPriceIn() != null)
            batch.setPriceIn(batchRequest.getPriceIn());

        if (batchRequest.getExpiry() != null)
            batch.setExpiry(batchRequest.getExpiry());

        if (batchRequest.getProductionDate() != null)
            batch.setProductionDate(batchRequest.getProductionDate());

        if (batchRequest.getProductID() != null) {
            Product product = productRepository.findById(batchRequest.getProductID())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
            batch.setProduct(product);
        }

        return toResponse(batchRepository.save(batch));
    }

    /* =========================
       DELETE
       ========================= */

    public void delete(Long id) {
        batchRepository.deleteById(id);
    }

    /* =========================
       MAPPING
       ========================= */

    private BatchResponse toResponse(Batch batch) {

        BatchResponse res = new BatchResponse();
        res.setBatchID(batch.getBatchID());
        res.setProductionDate(batch.getProductionDate());
        res.setQuantity(batch.getQuantity());
        res.setPriceIn(batch.getPriceIn());
        res.setExpiry(batch.getExpiry());

        if (batch.getProduct() != null) {
            res.setProduct(mapProduct(batch.getProduct()));
        }

        return res;
    }

    private ProductDTO mapProduct(Product product) {
        if (product == null) return null;

        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .description(product.getDescription())
                .brandId(
                        product.getBrand() != null
                                ? product.getBrand().getBrandId()
                                : null
                )
                .categoryId(
                        product.getCategory() != null
                                ? product.getCategory().getCategoryId()
                                : null
                )
                .build();
    }
}
