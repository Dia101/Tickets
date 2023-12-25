package ru.dia101.achievementSheet.tables.certification;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.achievementSheet.authentication.routes.components.AuthService;
import ru.dia101.achievementSheet.files.ControllerFunctions;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.StatusCode;
import ru.dia101.achievementSheet.tables.achievementSheet.AchievementSheetService;
import ru.dia101.achievementSheet.tables.discipline.DisciplineService;
import ru.dia101.achievementSheet.tables.professor.ProfessorService;
import ru.dia101.achievementSheet.users.UserService;

import java.util.List;

@RestController
@RequestMapping("/certifications")
@RequiredArgsConstructor
public class CertificationController{
    private final ControllerFunctions controllerFunctions;
    private final CertificationService certificationService;
    private final UserService userService;
    private final ProfessorService professorService;
    private final DisciplineService disciplineService;
    private final AchievementSheetService achievementSheetService;
    private final AuthService authService;



    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Certification>>> findAll(){
        return ResponseEntity.ok(certificationService.findAll());
    }

    @GetMapping(params = "userId")
    public ResponseEntity<ResponseWithStatus<List<Certification>>> findAllByUserId(@Param("userId") Long userId){
        return ResponseEntity.ok(certificationService.findAllByUserId(userId));
    }

    @GetMapping(params = "disciplineId")
    public ResponseEntity<ResponseWithStatus<List<Certification>>> findAllByDisciplineId(@Param("disciplineId") Long disciplineId){
        return ResponseEntity.ok(certificationService.findAllByDisciplineId(disciplineId));
    }

    @GetMapping(params = "professorId")
    public ResponseEntity<ResponseWithStatus<List<Certification>>> findAllByProfessorId(@Param("professorId") Long professorId){
        return ResponseEntity.ok(certificationService.findAllByProfessorId(professorId));
    }

    @GetMapping(params = "achievementSheetId")
    public ResponseEntity<ResponseWithStatus<List<Certification>>> findAllByAchievementSheetId(@Param("achievementSheetId") Long achievementSheetId){
        return ResponseEntity.ok(certificationService.findAllByAchievementSheetId(achievementSheetId));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<CertificationFull>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, certificationService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Certification certification, HttpServletRequest request){
        return controllerFunctions.statusCode(certification, certificationService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, certificationService::deleteById, request);
    }


}
