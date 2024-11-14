package org.dongguk.onroad.roadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    /* -------------------------------------------- */
    /* One To Many Attribute ---------------------- */
    /* -------------------------------------------- */
    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    @Builder
    public Week(String title, String overallSummary, Integer weekIndex, Lecture lecture) {
        this.title = title;
        this.overallSummary = overallSummary;
        this.weekIndex = weekIndex;
        this.lecture = lecture;
    }

    public void updateIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }
}
