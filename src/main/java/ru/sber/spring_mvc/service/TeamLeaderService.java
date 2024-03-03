package ru.sber.spring_mvc.service;

import ru.sber.spring_mvc.exception.TeamLeaderNotFoundException;
import ru.sber.spring_mvc.model.TeamLeader;

import java.util.Optional;

public interface TeamLeaderService {
    void create(TeamLeader teamLeader);
    void deleteById(long id);
    void update(long id, TeamLeader teamLeader);
    TeamLeader findById(long id) throws TeamLeaderNotFoundException;
    Iterable<TeamLeader> findAll();
}
