package ru.nvg.printservice.qdsl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EnumPath;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.QJob;

public class StatisticFilterBuilder  {

    private final QJob POST = QJob.job;

    public Predicate build(StatisticFilter filter) {
        return new OptionalBooleanBuilder(POST.isNotNull())
                .notEmptyAnd(POST.user.name::equalsIgnoreCase, filter.getUser())
                .notEmptyAnd(POST.device.name::equalsIgnoreCase, filter.getDevice())
                .notNullAnd(POST.type::eq, filter.getType())
                .notNullAnd(POST.time::after, filter.getTimeFrom())
                .notNullAnd(POST.time::before, filter.getTimeTo())
                .build();
    }
}