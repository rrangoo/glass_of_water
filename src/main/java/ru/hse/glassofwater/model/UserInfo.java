package ru.hse.glassofwater.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String code;
}