package ru.dia101.achievementSheet.tables.certification;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.ServiceFunctions;
import ru.dia101.achievementSheet.files.StatusCode;
import ru.dia101.achievementSheet.tables.discipline.Discipline;
import ru.dia101.achievementSheet.tables.discipline.DisciplineRepo;
import ru.dia101.achievementSheet.tables.professor.Professor;
import ru.dia101.achievementSheet.tables.professor.ProfessorRepo;
import ru.dia101.achievementSheet.users.User;
import ru.dia101.achievementSheet.users.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {
    private final CertificationRepo certificationRepo;
    private final ServiceFunctions serviceFunctions;
    private final UserRepo userRepo;
    private final DisciplineRepo disciplineRepo;
    private final ProfessorRepo professorRepo;



    public List<Certification> findByUserAndAchievementSheetId(Long id, String semester){
        return certificationRepo.findByUserAndAchievementSheetId(id, semester);
    }
    private CertificationFull isFull(List<Object[]> entitiesArr) {
        Object[] entities = entitiesArr.get(0);
        Certification certification = (Certification) entities[0];
        User user = (User) entities[1];
        Discipline discipline = (Discipline) entities[2];
        Professor professor = (Professor) entities[3];

        return CertificationFull.builder()
                .id(certification.getId())
                .user(user)
                .discipline(discipline)
                .professor(professor)
                .build();
    }
    public boolean notFull(Certification certification) {
       if(!userRepo.existsById(certification.getUserId())){
           System.out.println("!UserId");
           return true;
       }
        if(!disciplineRepo.existsById(certification.getDisciplineId())){
            System.out.println("!DisciplineId");
            return true;
        }
        if(!professorRepo.existsById(certification.getProfessorId())){
            System.out.println("!ProfessorId");
            return true;
        }
       return false;
    }
    public List<Certification> findAllWeb(){
        return certificationRepo.findAll();
    }
    public List<Certification> findAllByUserIdWeb(Long userId){
        return certificationRepo.findAllByUserId(userId);
    }
    public ResponseWithStatus<List<Certification>> findAll(){
        return ResponseWithStatus.create(200, certificationRepo.findAll());
    }

    public ResponseWithStatus<List<Certification>> findAllByUserId(Long userId){
        return ResponseWithStatus.create(200, certificationRepo.findAllByUserId(userId));
    }

    public ResponseWithStatus<List<Certification>> findAllByDisciplineId(Long disciplineId){
        return ResponseWithStatus.create(200, certificationRepo.findAllByDisciplineId(disciplineId));
    }


    public ResponseWithStatus<List<Certification>> findAllByProfessorId(Long professorId){
        return ResponseWithStatus.create(200, certificationRepo.findAllByProfessorId(professorId));
    }
    public ResponseWithStatus<List<Certification>> findAllByAchievementSheetId(Long achievementSheetId){
        return ResponseWithStatus.create(200, certificationRepo.findAllByAchievementSheetId(achievementSheetId));
    }

    public ResponseWithStatus<CertificationFull> findById(Long id){
        return serviceFunctions.findByWithJoin(
                id,
                certificationRepo::findByIdWithJoin,
                this::isFull
        );
    }


    public StatusCode save(Certification certification, HttpServletRequest request){
        return serviceFunctions.saveWithCheckFieldsWithAuth(
                certification,
                this::notFull,
                certificationRepo::save,
                request
        );
    }
    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, certificationRepo::findById, certificationRepo::deleteById, request);
    }

}
