package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.Section;
import org.dongguk.onroad.roadmap.domain.Week;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    public Section createSection(String title, String description, Week week) {
        return Section.builder()
                .title(title)
                .description(description)
                .week(week)
                .build();
    }
}
