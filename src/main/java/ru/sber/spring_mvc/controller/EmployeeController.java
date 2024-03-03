package ru.sber.spring_mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.model.Employee;
import ru.sber.spring_mvc.service.EmployeeService;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String showAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("employee", new Employee());
        return "employee/all_employees";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add_employee";
    }

    @PostMapping(value = "/find/{id}", params = "action=edit")
    public String editEmployee(@PathVariable("id") long id,
                               @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/employee_info";
        }
        employeeService.update(id, employee);
        return "redirect:/employees";
    }

    @PostMapping(value = "/find/{id}", params = "action=delete")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/add_employee";
        }
        employeeService.create(employee);
        return "redirect:/employees";
    }

    @GetMapping("/find")
    public String showEmployeeInfo(@RequestParam("id") long id, Model model) {
        Employee employee;
        try {
            employee = employeeService.findById(id);
        } catch (EmployeeNotFoundException e) {
            model.addAttribute("employeeNotFoundMessage","Employee was not found");
            return showAllEmployees(model);
        }

        model.addAttribute("employee", employee);
        return "employee/employee_info";
    }
}
