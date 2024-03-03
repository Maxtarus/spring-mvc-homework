package ru.sber.spring_mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.exception.TeamLeaderNotFoundException;
import ru.sber.spring_mvc.model.Employee;
import ru.sber.spring_mvc.model.TeamLeader;
import ru.sber.spring_mvc.service.EmployeeService;
import ru.sber.spring_mvc.service.TeamLeaderService;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/teamLeaders")
public class TeamLeaderController {
    private final TeamLeaderService teamLeaderService;
    private final EmployeeService employeeService;

    @GetMapping
    public String showAllTeamLeaders(Model model) {
        model.addAttribute("teamLeaders", teamLeaderService.findAll());
        model.addAttribute("teamLeader", new TeamLeader());
        return "team_leader/all_team_leaders";
    }

    @GetMapping("/add")
    public String addTeamLeader(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("teamLeader", new TeamLeader());
        return "team_leader/add_team_leader";
    }

    @PostMapping(value = "/find/{id}", params = "action=edit")
    public String editTeamLeader(@PathVariable("id") long id,
                               @ModelAttribute("teamLeader") TeamLeader teamLeader,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "team_leader/team_leader_info";
        }
        teamLeaderService.update(id, teamLeader);
        return "redirect:/teamLeaders";
    }

    @PostMapping(value = "/find/{id}", params = "action=delete")
    public String deleteTeamLeader(@PathVariable("id") long id) {
        teamLeaderService.deleteById(id);
        return "redirect:/teamLeaders";
    }

    @PostMapping("/add")
    public String addTeamLeader(@Valid TeamLeader teamLeader,
                                @RequestParam(value = "added", required = false) List<Long> addedEmployeesList,
                                BindingResult bindingResult) {

        List<Employee> selectedEmployees = new ArrayList<>();
        for (Long employeeId : addedEmployeesList) {
            try {
                Employee employee = employeeService.findById(employeeId);
                selectedEmployees.add(employee);
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        teamLeader.setEmployees(selectedEmployees);

        if (bindingResult.hasErrors()) {
            return "team_leader/add_team_leader";
        }

        teamLeaderService.create(teamLeader);
        return "redirect:/teamLeaders";
    }

    @GetMapping("/find")
    public String showTeamLeaderInfo(@RequestParam("id") long id, Model model) {
        TeamLeader teamLeader;
        try {
            teamLeader = teamLeaderService.findById(id);
        } catch (TeamLeaderNotFoundException e) {
            model.addAttribute("teamLeaderNotFoundMessage", "Team leader was not found");
            return showAllTeamLeaders(model);
        }

        model.addAttribute("teamLeader", teamLeader);
        return "team_leader/team_leader_info";
    }
}