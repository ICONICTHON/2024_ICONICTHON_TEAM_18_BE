package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.Section;
import org.dongguk.onroad.roadmap.domain.Subtopic;
import org.springframework.stereotype.Service;

@Service
public class SubtopicService {

    public Subtopic createSubtopic(String title, String detail, Section section) {
        return Subtopic.builder()
                .title(title)
                .detail(detail)
                .section(section)
                .build();
    }
}
