package com.guerra.picpay.picpay.repositories;

import com.guerra.picpay.picpay.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByDocument(String document);

    Optional<Users> findUsersById(Long id);

//    UserDetails findByDocument(String document);
}
