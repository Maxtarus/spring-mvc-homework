package ru.sber.spring_mvc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.spring_mvc.exception.TeamLeaderNotFoundException;
import ru.sber.spring_mvc.model.TeamLeader;
import ru.sber.spring_mvc.repository.TeamLeaderRepository;
import ru.sber.spring_mvc.service.TeamLeaderService;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamLeaderServiceImpl implements TeamLeaderService {
    private final TeamLeaderRepository teamLeaderRepository;

    @Override
    public void create(TeamLeader teamLeader) {
        log.info("Тимлид c id=" + teamLeader.getId() + " успешно создан");
        teamLeaderRepository.create(teamLeader);
    }

    @Override
    public void deleteById(long id) {
        log.info("Информация о тимлиде c id=" + id + " успешно удалена");
        teamLeaderRepository.deleteById(id);
    }

    @Override
    public void update(long id, TeamLeader teamLeader) {
        log.info("Информация о тимлиде c id=" + id + " успешно обновлена");
        teamLeaderRepository.update(id, teamLeader);
    }

    @Override
    public TeamLeader findById(long id) throws TeamLeaderNotFoundException {
        log.info("Получена информация о тимлиде c id=" + id);
        return teamLeaderRepository.findById(id);
    }

    @Override
    public Iterable<TeamLeader> findAll() {
        log.info("Получена информация о всех тимлидах");
        return teamLeaderRepository.findAll();
    }
}
