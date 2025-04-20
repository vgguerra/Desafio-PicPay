package com.guerra.picpay.picpay.models;

import com.guerra.picpay.picpay.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "document",unique = true)
    private String document;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private Double balance;

    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType accountType;

    public Users(UserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.password = data.password();
        this.balance = data.balance();
        this.accountType = data.accountType();
        this.document = data.document();
    }
}
