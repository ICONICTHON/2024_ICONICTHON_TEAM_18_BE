package org.dongguk.onroad.loadmap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.onroad.loadmap.domain.type.ESemester;
import org.dongguk.onroad.loadmap.domain.type.EStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "lectures")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    /* -------------------------------------------- */
    /* Default Column ----------------------------- */
    /* -------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------------------------------- */
    /* Information Column ------------------------- */
    /* -------------------------------------------- */
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false)
    private ESemester semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EStatus status = EStatus.PENDING;

    /* -------------------------------------------- */
    /* Timestamp Column --------------------------- */
    /* -------------------------------------------- */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* -------------------------------------------- */
    /* Methods ------------------------------------ */
    /* -------------------------------------------- */
    @Builder
    public Lecture(String title, Integer year, ESemester semester) {
        this.title = title;
        this.year = year;
        this.semester = semester;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(EStatus status) {
        this.status = status;
    }
}

