package ru.dia101.achievementSheet.tables.professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Long>{

    Optional<Professor> findByFullName(String serialNumber);

}

