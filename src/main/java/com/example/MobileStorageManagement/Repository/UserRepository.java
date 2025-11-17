package com.example.MobileStorageManagement.Repository;

import com.example.MobileStorageManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findBySdt (String Sdt);
//    List<User> findByIdQuyen_RoleNameIn(List<String> roleName);
    boolean existsBySdt(String sdt);
}
