package ru.sber.spring_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long id;
    @NotBlank(message = "Имя не должно быть пустым!")
    private String firstName;
    @NotBlank(message = "Фамилия не должна быть пустой!")
    private String lastName;
    private Position position;
    @Min(value = 16_844, message = "Зарплата сотрудника не должна быть меньше прожиточного минимума!")
    @Max(value = 300_000, message = "Зарплата сотрудника не должна быть больше 300 тыс. рублей!")
    private int salary;
    @NotBlank(message = "Email не должен быть пустым!")
    @Email(message = "Недопустимое значение email-а!")
    private String email;
    @Pattern(regexp = "^((8|\\+7|)[\\- ]?)?\\(?\\d{3,5}\\)?[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d(([\\- ]?\\d)?[\\- ]?\\d)?$",
            message = "Недопустимое значение номера телефона!")
    private String phoneNumber;
}
