package com.freshstratstore.freshstartstore.repesitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshstratstore.freshstartstore.doa.entities.Users;

@Repository
/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findbyEmail(String email);
}