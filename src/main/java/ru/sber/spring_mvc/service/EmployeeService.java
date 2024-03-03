package ru.sber.spring_mvc.service;

import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.model.Employee;

public interface EmployeeService {
    void create(Employee employee);
    void deleteById(long id);
    void update(long id, Employee employee);
    Employee findById(long id) throws EmployeeNotFoundException;
    Iterable<Employee> findAll();
}
