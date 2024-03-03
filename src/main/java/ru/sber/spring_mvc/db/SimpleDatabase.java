package ru.sber.spring_mvc.db;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.spring_mvc.model.Employee;
import ru.sber.spring_mvc.model.Position;
import ru.sber.spring_mvc.model.TeamLeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SimpleDatabase {
    @Getter
    private static Map<Long, Employee> employees = new HashMap<>();
    @Getter
    private static Map<Long, TeamLeader> teamLeaders = new HashMap<>();
    private static long employeeId = 1L;
    private static long teamLeaderId = 1L;

    static {
        Employee employee1 =
                new Employee(employeeId++,
                        "Данила", "Руди",
                        Position.JAVA_DEVELOPER,
                        75_000, "rudi_will_do_it@yandex.ru", "89156783421");
        Employee employee2 =
                new Employee(employeeId++,
                        "Арина", "Бабаян",
                        Position.SYSTEM_ANALYST,
                        85_000, "arinochka@mail.ru", "89105898352");
        Employee employee3 =
                new Employee(employeeId++,
                        "Никита", "Лютиков",
                        Position.DEVOPS_ENGINEER,
                        67_000, "lyutynikitos@gmail.com", "89576452309");
        Employee employee4 =
                new Employee(employeeId++,
                        "Миша", "Кузнецов",
                        Position.JAVA_DEVELOPER,
                        120_000, "mishus@tinkoff.ru", "89106037225");
        Employee employee5 =
                new Employee(employeeId++,
                        "Миша", "Проскуряков",
                        Position.REACT_DEVELOPER,
                        115_000, "proskuryakov_michail@mail.ru", "89034782618");

        employees.put(employee1.getId(), employee1);
        employees.put(employee2.getId(), employee2);
        employees.put(employee3.getId(), employee3);
        employees.put(employee4.getId(), employee4);
        employees.put(employee5.getId(), employee5);

        List<Employee> firstTeamEmployees = new ArrayList<>();
        firstTeamEmployees.add(employee1);
        firstTeamEmployees.add(employee3);
        firstTeamEmployees.add(employee4);
        TeamLeader teamLeader1 =
                new TeamLeader(teamLeaderId++,
                        "Артём", "Шишкин",
                        "Отдел Backend-разработки", firstTeamEmployees);

        List<Employee> secondTeamEmployees = new ArrayList<>();
        secondTeamEmployees.add(employee2);
        secondTeamEmployees.add(employee5);
        TeamLeader teamLeader2 =
                new TeamLeader(teamLeaderId++,
                        "Олег", "Антипов",
                        "Отдел Frontend-разработки", secondTeamEmployees);

        teamLeaders.put(teamLeader1.getId(), teamLeader1);
        teamLeaders.put(teamLeader2.getId(), teamLeader2);
    }
}
