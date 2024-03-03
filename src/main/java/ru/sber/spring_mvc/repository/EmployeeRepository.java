package ru.sber.spring_mvc.repository;

import org.springframework.stereotype.Repository;
import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.model.Employee;

@Repository
public interface EmployeeRepository {
    void create(Employee employee);
    void deleteById(long id);
    void update(long id, Employee employee);
    Employee findById(long id) throws EmployeeNotFoundException;
    Iterable<Employee> findAll();
}
