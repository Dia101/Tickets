package ru.dia101.achievementSheet.tables.certification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepo extends JpaRepository<Certification, Long> {

    List<Certification> findAllByUserId(Long userId);
    List<Certification> findAllByDisciplineId(Long disciplineId);
    List<Certification> findAllByProfessorId(Long professorId);
    List<Certification> findAllByAchievementSheetId(Long achievementSheetId);

    @Query("" +
            "SELECT s, u, d, p, a " +
            "FROM Certification s " +
            "JOIN User u on s.userId = u.id " +
            "JOIN Discipline d on s.disciplineId = d.id " +
            "JOIN Professor p on s.professorId = p.id " +
            "JOIN AchievementSheet a on s.achievementSheetId = a.id " +
            "WHERE s.id = :id"
    )
    List<Object[]> findByIdWithJoin(@Param("id") Long id);

    @Query("" +
            "SELECT s, a " +
            "FROM Certification s " +
            "JOIN AchievementSheet a on s.achievementSheetId = a.id " +
            "WHERE s.userId = :id " +
            "AND a.semester LIKE :semester || '%%'"
    )
    List<Certification> findByUserAndAchievementSheetId(@Param("id") Long id, @Param("semester") String semester);

}
