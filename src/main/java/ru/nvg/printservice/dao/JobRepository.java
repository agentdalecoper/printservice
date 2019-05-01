package ru.nvg.printservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.nvg.printservice.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long>,
        QuerydslPredicateExecutor<Job> {
}
