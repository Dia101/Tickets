package ru.dia101.achievementSheet.tables.certification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.dia101.achievementSheet.tables.achievementSheet.AchievementSheet;
import ru.dia101.achievementSheet.tables.discipline.Discipline;
import ru.dia101.achievementSheet.tables.professor.Professor;
import ru.dia101.achievementSheet.users.User;

import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CertificationFull {
    private Long id;
    private User user;
    private Discipline discipline;
    private Professor professor;
    private AchievementSheet achievementSheet;
    private Date eventDate;
    private String grade;
}
