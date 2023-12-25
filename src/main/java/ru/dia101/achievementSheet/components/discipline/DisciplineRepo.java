package ru.dia101.achievementSheet.tables.discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplineRepo extends JpaRepository<Discipline, Long>{

        Optional<Discipline> findByDisciplineName(String disciplineName);

}

