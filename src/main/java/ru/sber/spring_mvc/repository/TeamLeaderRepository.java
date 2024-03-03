package ru.sber.spring_mvc.repository;

import org.springframework.stereotype.Repository;
import ru.sber.spring_mvc.exception.TeamLeaderNotFoundException;
import ru.sber.spring_mvc.model.Employee;
import ru.sber.spring_mvc.model.TeamLeader;

import java.util.Optional;

@Repository
public interface TeamLeaderRepository {
    void create(TeamLeader teamLeader);
    void deleteById(long id);
    void update(long id, TeamLeader teamLeader);
    TeamLeader findById(long id) throws TeamLeaderNotFoundException;
    Iterable<TeamLeader> findAll();
}
