package com.weatherbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity (name = "usersDataTable")
public class User {
    @Id
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private String usersCity;
    private Timestamp registeredAt;
}