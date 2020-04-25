package com.blackpoint.accountmanagerweb.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "operations", callSuper = false)

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 3, max  = 40)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String hashedPassword;
}
