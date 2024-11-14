package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.Week;
import org.springframework.stereotype.Service;

@Service
public class WeekService {

    public Week createWeek(String title, String overallSummary, int weekIndex, Lecture lecture) {
        return Week.builder()
                .title(title)
                .overallSummary(overallSummary)
                .weekIndex(weekIndex)
                .lecture(lecture)
                .build();
    }
}
