package ru.dia101.achievementSheet.tables.achievementSheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchievementSheetRepo extends JpaRepository<AchievementSheet, Long>{

        Optional<AchievementSheet> findBySemester(String semester);

}

