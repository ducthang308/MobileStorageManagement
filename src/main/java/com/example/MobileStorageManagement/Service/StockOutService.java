package com.example.MobileStorageManagement.Service;

import com.example.MobileStorageManagement.DTO.StockInResponse;
import com.example.MobileStorageManagement.DTO.StockOutRequest;
import com.example.MobileStorageManagement.DTO.StockOutResponse;
import com.example.MobileStorageManagement.Entity.Batch;
import com.example.MobileStorageManagement.Entity.StockIn;
import com.example.MobileStorageManagement.Entity.StockOut;
import com.example.MobileStorageManagement.Entity.User;
import com.example.MobileStorageManagement.Repository.BatchRepository;
import com.example.MobileStorageManagement.Repository.StockOutRepository;
import com.example.MobileStorageManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StockOutService {
    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public Optional<StockOut> getById(Long id) {
        return stockOutRepository.findById(id);
    }


    public StockOut create(StockOutRequest stockOutRequest) throws IOException {
        StockOut dto = new StockOut();
        dto.setQuantity(stockOutRequest.getQuantity());
        dto.setDate(stockOutRequest.getDate());
        dto.setNote(stockOutRequest.getNote());

        // tìm user dựa vào giải token lấy mail hoặc sdt rồi truy ngược lại vào db
        String identity = SecurityContextHolder.getContext().getAuthentication().getName();
        User user;
        if (identity.contains("@")) {
            // nếu login bằng mail
            user = userRepository.findByEmail(identity)
                    .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại (email)!"));
        } else {
            // nếu login bằng sdt
            user = userRepository.findBySdt(identity)
                    .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại (sdt)!"));
        }
        dto.setUser(user);

        if (stockOutRequest.getBatchID() != null) {
            Batch batch = batchRepository.findById(stockOutRequest.getBatchID())
                    .orElseThrow(() -> new RuntimeException("Lô hàng không tồn tại"));
            dto.setBatch(batch);
        }

        return (stockOutRepository.save(dto));
    }

    public StockOut update(Long id, StockOutRequest stockOutRequest) throws IOException {
        StockOut dto = stockOutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu xuất"));

        if (stockOutRequest.getQuantity() != null) {
            dto.setQuantity(stockOutRequest.getQuantity());
        }

        if (stockOutRequest.getDate() != null) {
            dto.setDate(stockOutRequest.getDate());
        }

        if (stockOutRequest.getNote() != null) {
            dto.setNote(stockOutRequest.getNote());
        }

        if (stockOutRequest.getBatchID() != null) {
            Batch batch = batchRepository.findById(stockOutRequest.getBatchID())
                    .orElseThrow(() -> new RuntimeException("Lô hàng không tồn tại"));
            dto.setBatch(batch);
        }

        String sdt = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findBySdt(sdt)
                .orElseThrow(() -> new RuntimeException("User not found"));
        dto.setUser(user);

        return (stockOutRepository.save(dto));
    }


    public void delete(Long id) {
        stockOutRepository.deleteById(id);
    }

    public List<StockOut> getAll() {
        return stockOutRepository.findAll();
    }

    public StockOutResponse toResponse(StockOut stock) {

        StockOutResponse dto = new StockOutResponse();
        dto.setStockOutId(stock.getStockOutID());
        dto.setQuantity(stock.getQuantity());
        dto.setDate(stock.getDate());
        dto.setNote(stock.getNote());

        if (stock.getBatch() != null) {
            dto.setBatchID(stock.getBatch().getBatchID());
        }

        if (stock.getUser() != null) {
            dto.setUserID(stock.getUser().getUserId());
        }

        return dto;
    }
}
