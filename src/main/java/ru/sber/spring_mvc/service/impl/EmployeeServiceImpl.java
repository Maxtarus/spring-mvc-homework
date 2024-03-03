package ru.sber.spring_mvc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.model.Employee;
import ru.sber.spring_mvc.repository.EmployeeRepository;
import ru.sber.spring_mvc.service.EmployeeService;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void create(Employee employee) {
        log.info("Сотрудник c id=" + employee.getId() + " успешно создан");
        employeeRepository.create(employee);
    }

    @Override
    public void deleteById(long id) {
        log.info("Информация о сотруднике c id=" + id + " успешно удалена");
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(long id, Employee employee) {
        log.info("Информация о сотруднике c id=" + id + " успешно обновлена");
        employeeRepository.update(id, employee);
    }

    @Override
    public Employee findById(long id) throws EmployeeNotFoundException {
        log.info("Получена информация о сотруднике c id=" + id);
        return employeeRepository.findById(id);
    }

    @Override
    public Iterable<Employee> findAll() {
        log.info("Получена информация о всех сотрудниках");
        return employeeRepository.findAll();
    }
}
