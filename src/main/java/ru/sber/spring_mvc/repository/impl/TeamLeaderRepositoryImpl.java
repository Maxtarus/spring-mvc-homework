package ru.sber.spring_mvc.repository.impl;

import org.springframework.stereotype.Component;
import ru.sber.spring_mvc.db.SimpleDatabase;
import ru.sber.spring_mvc.exception.EmployeeNotFoundException;
import ru.sber.spring_mvc.exception.TeamLeaderNotFoundException;
import ru.sber.spring_mvc.model.TeamLeader;
import ru.sber.spring_mvc.repository.TeamLeaderRepository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Component
public class TeamLeaderRepositoryImpl implements TeamLeaderRepository {
    private Map<Long, TeamLeader> teamLeaders;

    @PostConstruct
    private void initEmployees() {
        teamLeaders = SimpleDatabase.getTeamLeaders();
    }

    @Override
    public void create(TeamLeader teamLeader) {
        teamLeader.setId(teamLeaders.size() + 1);
        teamLeaders.put(teamLeader.getId(), teamLeader);
    }

    @Override
    public void deleteById(long id) {
        teamLeaders.remove(id);
    }

    @Override
    public void update(long id, TeamLeader newTeamLeader) {
        TeamLeader teamLeader = teamLeaders.get(id);

        if (teamLeader != null) {
            teamLeader.setFirstName(newTeamLeader.getFirstName());
            teamLeader.setLastName(newTeamLeader.getLastName());
            teamLeader.setDepartment(newTeamLeader.getDepartment());
            teamLeader.setEmployees(newTeamLeader.getEmployees());
        }
    }

    @Override
    public TeamLeader findById(long id) throws TeamLeaderNotFoundException {
        Optional<TeamLeader> teamLeader = teamLeaders
                .entrySet()
                .stream()
                .filter(e -> e.getKey() == id)
                .map(Map.Entry::getValue)
                .findFirst();

        if (teamLeader.isPresent()) {
            return teamLeader.get();
        } else {
            throw new TeamLeaderNotFoundException();
        }
    }

    @Override
    public Iterable<TeamLeader> findAll() {
        return teamLeaders.values();
    }
}
