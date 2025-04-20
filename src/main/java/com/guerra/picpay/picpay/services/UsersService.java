package com.guerra.picpay.picpay.services;

import com.guerra.picpay.picpay.DTO.UserDTO;
import com.guerra.picpay.picpay.models.AccountType;
import com.guerra.picpay.picpay.models.Users;
import com.guerra.picpay.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(Users user,Double amount) throws Exception {
        if(user.getAccountType() != AccountType.COMMON){
                throw new Exception("User don't have the account type to make this transaction");
        }

        if(user.getBalance() < amount){
            throw new Exception("User don't have enough balance to make this transaction");
        }
    }

    public Users findUserById(Long id) throws Exception {
        return userRepository.findUsersById(id).orElseThrow(() ->new Exception("User not found"));
    }

    public void save(Users user) {
        userRepository.save(user);
    }

    public Users createUser(UserDTO data) {
        Users newUser = new Users(data);
//        String passwordEncode = new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(newUser.getPassword());
        this.userRepository.save(newUser);
        return newUser;
    }

    public List<Users> findAllUsers() {
        return this.userRepository.findAll();
    }



}
