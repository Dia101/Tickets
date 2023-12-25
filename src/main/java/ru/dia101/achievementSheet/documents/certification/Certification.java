package ru.dia101.achievementSheet.tables.certification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "sertifications")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor


public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "discipline_id")
    private Long disciplineId;
    @Column(name = "professor_id")
    private Long professorId;
    @Column(name = "achievementSheet_id")
    private Long achievementSheetId;
    private Date eventDate;
    private String grade;

    public Long getUserId() {
        return userId;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public Long getAchievementSheetId() {
        return achievementSheetId;
    }

    public Long getId() {
        return id;
    }

    public Date getEventDate() {
        return eventDate;
    }


    public String getGrade() {
        return grade;
    }


}


/*

 */