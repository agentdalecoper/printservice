package ru.nvg.printservice.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.QJob;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, QuerydslPredicateExecutor<Job>, QuerydslBinderCustomizer<QJob> {
    @Override
    default void customize(QuerydslBindings bindings, QJob root) {
    }
}