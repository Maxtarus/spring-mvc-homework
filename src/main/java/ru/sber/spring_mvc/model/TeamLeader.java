package ru.sber.spring_mvc.model;

import lombok.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamLeader {
    private long id;
    @NotBlank(message = "Имя не должно быть пустым!")
    private String firstName;
    @NotBlank(message = "Фамилия не должна быть пустой!")
    private String lastName;
    @NotBlank(message = "Отдел не должен быть пустым!")
    private String department;
    private final Position position = Position.TEAM_LEADER;
    private List<Employee> employees;
}
