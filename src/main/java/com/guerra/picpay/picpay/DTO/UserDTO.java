package com.guerra.picpay.picpay.DTO;

import com.guerra.picpay.picpay.models.AccountType;

public record UserDTO(String firstName, String lastName, String email, String password, Double balance, AccountType accountType, String document) {
}
