package ru.yandex.praktikum.stellarburgers.pojo;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRegistrationPojo {
    private String email;
    private String password;
    private String name;
}
