//package com.unibuc.ro.repository;
//
//import com.unibuc.ro.domain.model.Client;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface ClientRepository extends JpaRepository<Client, Long> {
//    Optional<Boolean> existsByEmailOrPhoneNumber(String email, String phoneNumber);
//
//    Optional<Client> findByEmail(String email);
//
//    void deleteByEmail(String email);
//
//    Optional<Boolean> existsByPhoneNumber(String phoneNumber);
//}