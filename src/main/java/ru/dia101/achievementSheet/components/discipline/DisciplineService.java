package ru.dia101.achievementSheet.tables.discipline;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.ServiceFunctions;
import ru.dia101.achievementSheet.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {
    private final DisciplineRepo disciplineRepo;
    private final ServiceFunctions serviceFunctions;

    public List<Discipline> findAllWeb(){
        return disciplineRepo.findAll();
    }
    public ResponseWithStatus<List<Discipline>> findAll(){
        return ResponseWithStatus.create(200, disciplineRepo.findAll());
    }
    public ResponseWithStatus<Discipline> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, disciplineRepo::findById, request);
    }
    public ResponseWithStatus<Discipline> findByName(String disciplineName, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(disciplineName, disciplineRepo::findByDisciplineName, request);
    }
    public StatusCode save(Discipline discipline, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                discipline,
                discipline.getDisciplineName(),
                disciplineRepo::findByDisciplineName,
                disciplineRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, disciplineRepo::findById, disciplineRepo::deleteById, request);
    }


}