package com.example.pwdManager.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pwdManager.Model.SystemDetails;

@Repository
public interface SystemDetailsRepository extends JpaRepository<SystemDetails, Long> {

    Optional<SystemDetails> findByIpAddress(String ipAddress);

}
