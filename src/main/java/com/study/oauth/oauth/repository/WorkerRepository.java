package com.study.oauth.oauth.repository;

import com.study.oauth.oauth.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Worker> findAll();
}
