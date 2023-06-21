package com.study.oauth.oauth.service;


import com.study.oauth.oauth.domain.Worker;
import com.study.oauth.oauth.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkerService {

    private final WorkerRepository workerRepository;

    public List<Worker> getWorkersByDate(LocalDateTime start, LocalDateTime end) {
        return workerRepository.findAllByCreatedAtBetween(start, end);
    }

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }


}
