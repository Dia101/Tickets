package ru.dia101.achievementSheet.tables.professor;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.achievementSheet.files.ControllerFunctions;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Professor>>> findAll(){
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Professor>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, professorService::findById, request);
    }

    @GetMapping(params = "fullName")
    public ResponseEntity<ResponseWithStatus<Professor>> findByFullName(@RequestParam String fullName, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(fullName, professorService::findBySerialNumber, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Professor professor, HttpServletRequest request){
        return controllerFunctions.statusCode(professor, professorService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, professorService::deleteById, request);
    }

}
