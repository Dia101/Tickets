package ru.dia101.achievementSheet.tables.professor;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.ServiceFunctions;
import ru.dia101.achievementSheet.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepo professorRepo;
    private final ServiceFunctions serviceFunctions;

    public List<Professor> findAllWeb(){
        return professorRepo.findAll();
    }
    public ResponseWithStatus<List<Professor>> findAll(){
        return ResponseWithStatus.create(200, professorRepo.findAll());
    }
    public ResponseWithStatus<Professor> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, professorRepo::findById, request);
    }
    public ResponseWithStatus<Professor> findBySerialNumber(String serialNumber, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(serialNumber, professorRepo::findByFullName, request);
    }
    public StatusCode save(Professor professor, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                professor,
                professor.getFullName(),
                professorRepo::findByFullName,
                professorRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, professorRepo::findById, professorRepo::deleteById, request);
    }


}