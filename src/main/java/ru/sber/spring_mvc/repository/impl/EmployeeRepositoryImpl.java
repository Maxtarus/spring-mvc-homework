package ru.sber.spring_mvc.repository.impl;

import org.springframework.stereotype.Component;
import ru.sber.spring_mvc.db.SimpleDatabase;
import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.model.Employee;
import ru.sber.spring_mvc.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private Map<Long, Employee> employees;

    @PostConstruct
    private void initEmployees() {
        employees = SimpleDatabase.getEmployees();
    }

    @Override
    public void create(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.put(employee.getId(), employee);
    }

    @Override
    public void deleteById(long id) {
        employees.remove(id);
    }

    @Override
    public void update(long id, Employee newEmployee) {
        Employee employee = employees.get(id);

        if (employee != null) {
            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setPosition(newEmployee.getPosition());
            employee.setSalary(newEmployee.getSalary());
            employee.setEmail(newEmployee.getEmail());
            employee.setPhoneNumber(newEmployee.getPhoneNumber());
        }
    }

    @Override
    public Employee findById(long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employees
                .entrySet()
                .stream()
                .filter(e -> e.getKey() == id)
                .map(Map.Entry::getValue)
                .findFirst();

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Iterable<Employee> findAll() {
        return employees.values();
    }

//    @Override
//    public List<Employee> findAllByTeamLeaderId(long teamLeaderId) {
//        return SimpleDatabase.getTeamLeaders()
//                .values()
//                .stream()
//                .filter(teamLeader -> teamLeader.getId() == teamLeaderId)
//                .map(TeamLeader::getEmployees)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//    }
}
