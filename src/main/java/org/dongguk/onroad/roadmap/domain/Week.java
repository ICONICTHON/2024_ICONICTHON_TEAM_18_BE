package org.dongguk.onroad.roadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weeks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Week {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "overall_summary", length = 500, nullable = false)
    private String overallSummary;

    @Column(name = "week_index", nullable = false)
    private Integer weekIndex;

    @Column(name = "is_selected", nullable = false)
    private Boolean isSelected = false;

    /* -------------------------------------------- */
    /* Many To One Mapping ------------------------ */
    /* -------------------------------------------- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Builder
    public Week(String title, String overallSummary, Integer weekIndex, Lecture lecture) {
        this.title = title;
        this.overallSummary = overallSummary;
        this.weekIndex = weekIndex;
        this.lecture = lecture;
    }
}
